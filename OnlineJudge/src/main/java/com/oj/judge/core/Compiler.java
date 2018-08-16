package com.oj.judge.core;
import java.io.FileInputStream;
import java.util.Date;

import org.apache.commons.io.IOUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.oj.judge.core.Runner.Result;
import com.oj.judge.model.Language;
import com.oj.judge.model.Problem;
import com.oj.judge.model.Submission;
/**
 * Compiler, used to compile user's code
 * 
 * @author Tiger
 */

public class Compiler {
	
	/**
	 * runner used to execute the compile command
	 */
	private Runner compilerRunner = new Runner();
	
	private static final Logger LOGGER = LogManager.getLogger(Compiler.class);
	
	
	/**
	 * Compile the code and get the result.
	 * @param submission - submission record
	 * @param workDirectory - output directory
	 * @param baseFileName - output class name
	 * @return compiled result 
	 */
	public Result getCompileResult(Submission submission, 
			String workDirectory, String baseFileName) {
		String commandLine = getCompileCommandLine(submission);
		//System.out.println("command line:"+ commandLine);
		
		String compileLogPath = getCompileLogPath(workDirectory, baseFileName);
		
		//System.out.println("compile log path:"+compileLogPath);
		
		return getCompileResult(commandLine, workDirectory,baseFileName, compileLogPath);
	}
	
	/**
	 * get the compile command.
	 * @param submission - submission record
	 * @param workDirectory - output directory
	 * @param baseFileName - output file name
	 * @return compile command
	 */
	private String getCompileCommandLine(Submission submission) {

		String compileCommand = submission.getLanguage().getCompileCommand();
		return compileCommand;
	}
	
	/**
	 * get the log out path.
	 * @param workDirectory - compile output directory
	 * @param baseFileName - compile output file name
	 * @return the full path of the log file
	 */
	private String getCompileLogPath(String workDirectory, String baseFileName) {
		return String.format("%s/%s-compile.log", 
				new Object[] {workDirectory, baseFileName});
	}
	
	/**
	 * get the compiled result.
	 * @param commandLine - compile command
	 * @param compileLogPath - compile log path
	 * @return compiled result
	 */
	private Result getCompileResult(String commandLine, String workdir, String baseFileName,String compileLogPath) {
		String inputFilePath = null;
		int timeLimit = 5000;
		int memoryLimit = 0;
		
		LOGGER.info("Start compiling with command: " + commandLine);

		Result runningResult = compilerRunner.getRuntimeResult(
				commandLine, workdir, baseFileName, inputFilePath, compileLogPath, timeLimit, memoryLimit);
		//Map<String, Object> result = new HashMap<>(3, 1);
		
		//boolean isSuccessful = false;	
		//if ( runningResult != null ) {
		//	int exitCode = (Integer)runningResult.get("exitCode");
		//	isSuccessful = exitCode == 0;
		//}
		//result
		//result.put("isSuccessful", isSuccessful);
		//result.put("log", getCompileOutput(compileLogPath));
		return runningResult;
	}
	
	/**
	 * get the compile log content.
	 * @param compileLogPath - log path
	 * @return log content
	 */
	private String getCompileOutput(String compileLogPath) {
		FileInputStream inputStream = null;
		String compileLog = "";
		try {
			inputStream = new FileInputStream(compileLogPath);
			compileLog = IOUtils.toString(inputStream);
			inputStream.close();
		} catch (Exception ex) {
			// Do nothing
		}
		return compileLog;
	}
	
	public static void main(String[] args){
		Compiler compiler = new Compiler();
		
		Preprocessor preprocessor = new Preprocessor();
		
		String workBaseDirectory = "c:/Mywork/tmp";
		
		String workDirectory = workBaseDirectory + "/oj-1001";
		String baseFileName = "Solution.java";
		Submission submission = compiler.getSubmission(1000);
		
		try {
			preprocessor.createTestCode(submission, workDirectory, baseFileName);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Result result = compiler.getCompileResult(submission, workDirectory, baseFileName);
		if(result==null){
			System.out.println("result is null");
		}else{
			System.out.println("----"+result.output);
		}
		
	}
	private Submission getSubmission(int id){
		Problem problem = new Problem();
		problem.setProblemId(1000);
		problem.setPublic(true);
		problem.setProblemName("A+B Problem");
		problem.setDescription("输入两个自然数, 输出他们的和");
		problem.setTimeLimit(1000);
		problem.setMemoryLimit(65536);
		problem.setHint(" Java Code\r\n\r\n    import java.util.Scanner;\r\n\r\n    public class Solution {\r\n        public static void main(String[] args) {\r\n            Scanner in = new Scanner(System.in);\r\n            int a = in.nextInt();\r\n            int b = in.nextInt();\r\n            System.out.println(a + b);\r\n        }\r\n    }\r\n");
		problem.setSampleInput("123 500");
		problem.setSampleOutput("623");
		problem.setInpuFormatt("两个自然数x和y (0<=x, y<=32767).");
		problem.setOutputFormat("一个数, 即x和y的和.");
		
		Submission submission = new Submission();
		
		//submission_id`, `problem_id`, `uid`, `language_id`, `submission_submit_time`, `submission_execute_time`, `submission_used_time`, `submission_used_memory`, `submission_judge_result`, `submission_judge_score`, `submission_judge_log`, `submission_code`
		
		Language lang = new Language();
		lang.setLanguageId(1);
		lang.setLanguageName("Java");
		lang.setCompileCommand("javac ");
		lang.setRunCommand("java -cp ");
		
		submission.setSubmissionId(1000);
		submission.setProblem(problem);
		submission.setUid(1000);
		submission.setLanguage(lang);
		submission.setSubmitTime(new Date());
		submission.setCode("public class Solution {\r\n    public static void main(String[] args) {\r\n        System.out.println(\"Hello World\");\r\n    }\r\n}");
		
		return submission;
		
	}
	
}