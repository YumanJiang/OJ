package com.oj.judge.core;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.oj.judge.model.Language;
import com.oj.judge.model.Submission;


/**
 * Program executor, used to execute the compiled program.
 * 
 * @author Tiger
 */

public class Runner {
	
	public class Result {
        public String output;
        public long runtime;
        public boolean isTimeout = false;
        

        public Result(String output, long runtime, boolean isTimeout) {
            this.output = output;
            this.runtime = runtime;
            this.isTimeout = isTimeout;
        }
    }


	private static final Logger LOGGER = LogManager.getLogger(Runner.class);

	/**
	 * get the running result
	 * 
	 * @param submission - submission result
	 * @param workDirectory - compile output directory
	 * @param baseFileName - filename to be executed
	 * @param inputFilePath - input file
	 * @param outputFilePath - output file
	 * @return running result
	 */
	public Result getRuntimeResult(Submission submission, String workDirectory,
		String baseFileName, String inputFilePath, String outputFilePath) {
		String commandLine = getCommandLine(submission, workDirectory, baseFileName);
		int timeLimit = getTimeLimit(submission);
		int memoryLimit = getMemoryLimit(submission);
		Result result = null;
		String runtimeResultSlug = "SE";
		int usedTime = 0;
		int usedMemory = 0;
		
		try {
			LOGGER.info(String.format("[Submission #%d] Start running with command %s (TimeLimit=%d, MemoryLimit=%s)",
								new Object[] { submission.getSubmissionId(), commandLine, timeLimit, memoryLimit }));
			File inputFile = new File(inputFilePath);
			File workDir = new File(workDirectory);
			Result runtimeResult = execute(commandLine, workDir, baseFileName,inputFile, timeLimit, memoryLimit);
			
			//int exitCode = (Integer) runtimeResult.get("exitCode");
			//usedTime = (Integer) runtimeResult.get("usedTime");
			//usedMemory = (Integer) runtimeResult.get("usedMemory");
			//runtimeResultSlug = getRuntimeResultSlug(exitCode, timeLimit, usedTime, memoryLimit, usedMemory);
		} catch ( Exception ex ) {
			LOGGER.catching(ex);
		}

		//result.put("runtimeResult", runtimeResultSlug);
		//result.put("usedTime", usedTime);
		//result.put("usedMemory", usedMemory);
		return result;
	}
	
	/**
	 * get the running command.
	 * @param submission - submission record
	 * @param workDirectory - compile output directory
	 * @param baseFileName - class file to be executed
	 * @return the running command
	 */
	private String getCommandLine(Submission submission, 
			String workDirectory, String baseFileName) {
		Language language = submission.getLanguage();
		String filePathWithoutExtension = String.format("%s/%s", 
											new Object[] {workDirectory, baseFileName});
		StringBuilder runCommand = new StringBuilder(language.getRunCommand()
													.replaceAll("\\{filename\\}", filePathWithoutExtension)); 
		
		if ( language.getLanguageName().equalsIgnoreCase("Java") ) {
			int lastIndexOfSpace = runCommand.lastIndexOf("/");
			runCommand.setCharAt(lastIndexOfSpace, ' ');
		}
		return runCommand.toString();
	}

	/**
	 * Time limit to execute the program
	 * @param submission - record submission
	 * @return max time limit
	 */
	private int getTimeLimit(Submission submission) {
		Language language = submission.getLanguage();
		int timeLimit = submission.getProblem().getTimeLimit();
		
		if ( language.getLanguageName().equalsIgnoreCase("Java") ) {
			timeLimit *= 2;
		}
		return timeLimit;
	}
	
	/**
	 * memory limit.
	 * @param submission - submission record
	 * @return max memory limit
	 */
	private int getMemoryLimit(Submission submission) {
		int memoryLimit = submission.getProblem().getMemoryLimit();
		return memoryLimit;
	}

	/**
	 * 
	 * @param exitCode
	 * @param timeLimit
	 * @param timeUsed
	 * @param memoryLimit
	 * @param memoryUsed
	 * @return 
	 */
	private String getRuntimeResultSlug(int exitCode, int timeLimit, int timeUsed, int memoryLimit, int memoryUsed) {
		if ( exitCode == 0 ) {
			// Output will be compared in next stage
			return "AC";
		}
		if ( timeUsed >= timeLimit ) {
			return "TLE";
		}
		if ( memoryUsed >= memoryLimit ) {
			return "MLE";
		}
		return "RE";
	}
	
	/**
	 * result of running the program.
	 * @param commandLine 
	 * @param inputFilePath 
	 * @param outputFilePath 
	 * @param timeLimit 
	 * @param memoryLimit
	 * @return 
	 */
	public Result getRuntimeResult(String commandLine,String workDir, String baseFileName,String inputFilePath, String outputFilePath, int timeLimit,
			int memoryLimit) {
		
		Result result = null;
		try {
			
			File inputFile = null;
			File workingDir = null;
			if(inputFilePath!=null){
				inputFile = new File(inputFilePath);
			}
			if(workDir!=null){
				workingDir = new File(workDir);
			}
			result = execute(commandLine,workingDir, baseFileName,inputFile, timeLimit, memoryLimit);
		} catch ( Exception ex ) {
			LOGGER.catching(ex);
		}
		return result;
	}
	public Result execute(String command, File workDir, String baseFileName, File inputFile,int timeout, int memoryLimit) throws IOException {
	
        ProcessBuilder processBuilder = new ProcessBuilder(command,baseFileName).redirectErrorStream(true);
        if (inputFile != null)
            processBuilder.redirectInput(inputFile);
        
        processBuilder.directory(workDir);
        
        long start = System.currentTimeMillis();
        
        Process process = processBuilder.start();
        
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        
        Future<String> future = executorService.submit(() -> IOUtils.toString(process.getInputStream(), "UTF-8"));
        
        try {
            boolean hasExisted = process.waitFor(timeout, TimeUnit.MILLISECONDS);
            if (hasExisted) {
            	
                return new Result(future.get(), System.currentTimeMillis() - start, false);
            } else {
                future.cancel(true);
                process.destroyForcibly();
                return new Result(null, timeout, true);
            }
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
            return new Result(ExceptionUtils.getMessage(e), 0, false);
        } finally {
            if (process.isAlive())
                process.destroyForcibly();
            executorService.shutdownNow();
        }
    }
}
