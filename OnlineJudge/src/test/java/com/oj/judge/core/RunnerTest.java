package com.oj.judge.core;

import java.util.Map;

import org.junit.Test;
import static org.junit.Assert.*;


import com.oj.judge.model.Submission;
import com.oj.judge.core.Runner.Result;

/**
 * 
 * @author Tiger
 */

public class RunnerTest {
	
	private Runner runner;
	

	private Compiler compiler;
	

	private Preprocessor preprocessor;
	

	private String workBaseDirectory;

	private Submission getSubmission(int id){
		return new Submission();
	}
	

	@Test
	public void testGetRuntimeResultJava() throws Exception {
		String workDirectory = workBaseDirectory + "/oj-1001";
		String baseFileName = "RandomName";
		Submission submission = this.getSubmission(1001);

		String inputFilePath = workBaseDirectory + "/testpoints/1001/input#0.txt";
		String outputFilePath = workBaseDirectory + "/voj-1000/output#0.txt";
		
		preprocessor.createTestCode(submission, workDirectory, baseFileName);
	
		compiler.getCompileResult(submission, workDirectory, baseFileName);
		
		Result result = 
				runner.getRuntimeResult(submission, workDirectory, baseFileName, inputFilePath, outputFilePath);
		//assertEquals("AC", result.get("runtimeResult"));
	}
	


}

