package com.oj.judge.core;

import java.util.Map;
import java.util.Date;

import org.junit.Test;
import static org.junit.Assert.*;



import com.oj.judge.model.Submission;
import com.oj.judge.model.Problem;
import com.oj.judge.model.Language;
import com.oj.judge.core.Preprocessor;
import com.oj.judge.core.Runner.Result;
/**
 * 
 * @author Tiger
 */

public class CompilerTest {
	
	private Compiler compiler = new Compiler();
	
	private Preprocessor preprocessor = new Preprocessor();
	
	private String workBaseDirectory = "c:/Mywork/tmp";
	
	private Submission getSubmission(int id){
		Problem problem = new Problem();
		problem.setProblemId(1000);
		problem.setPublic(true);
		problem.setProblemName("A+B Problem");
		problem.setDescription("输入两个自然数, 输出他们的和");
		problem.setTimeLimit(1000);
		problem.setMemoryLimit(65536);
		problem.setHint(" Java Code\r\n\r\n    import java.util.Scanner;\r\n\r\n    public class Main {\r\n        public static void main(String[] args) {\r\n            Scanner in = new Scanner(System.in);\r\n            int a = in.nextInt();\r\n            int b = in.nextInt();\r\n            System.out.println(a + b);\r\n        }\r\n    }\r\n");
		problem.setSampleInput("123 500");
		problem.setSampleOutput("623");
		problem.setInpuFormatt("两个自然数x和y (0<=x, y<=32767).");
		problem.setOutputFormat("一个数, 即x和y的和.");
		
		Submission submission = new Submission();
		
		//submission_id`, `problem_id`, `uid`, `language_id`, `submission_submit_time`, `submission_execute_time`, `submission_used_time`, `submission_used_memory`, `submission_judge_result`, `submission_judge_score`, `submission_judge_log`, `submission_code`
		
		Language lang = new Language();
		lang.setLanguageId(1);
		lang.setLanguageName("Java");
		lang.setCompileCommand("javac {filename}.java");
		lang.setRunCommand("java -cp {filename}");
		
		submission.setSubmissionId(1000);
		submission.setProblem(problem);
		submission.setUid(1000);
		submission.setLanguage(lang);
		submission.setSubmitTime(new Date());
		submission.setCode("public class Main {\r\n    public static void main(String[] args) {\r\n        System.out.println(\"Hello World\");\r\n    }\r\n}'),");
		
		return submission;
		
	}
	

	@Test
	public void testGetCompileResultJavaWithSuccess() throws Exception {
		String workDirectory = workBaseDirectory + "/oj-1001";
		String baseFileName = "RandomName";
		Submission submission = this.getSubmission(1000);
		preprocessor.createTestCode(submission, workDirectory, baseFileName);
		
		Result result = compiler.getCompileResult(submission, workDirectory, baseFileName);
		if(result==null){
			System.out.println("result is null");
		}
		assertEquals(false, result.isTimeout);
	}
	

}

