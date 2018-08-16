package com.oj.judge.model;
import java.io.Serializable;

/**
 * 
 * @author Tiger
 */
public class Problem implements Serializable {
	
	private long problemId;
	

	private boolean isPublic;
	

	private String problemName;
	

	private int timeLimit;
	

	private int memoryLimit;
	

	private String description;
	

	private String inputFormat;
	

	private String outputFormat;
	

	private String sampleInput;
	
	
	private String sampleOutput;
	
	private String hint;
	
	
	private static final long serialVersionUID = 4703482016721365341L;

	public Problem() { }
	
	public Problem(boolean isPublic, String problemName, int timeLimit, int memoryLimit,  
					String description, String inputFormat, String outputFormat, 
					String sampleInput, String sampleOutput, String hint) { 
		this.isPublic = isPublic;
		this.problemName = problemName;
		this.timeLimit = timeLimit;
		this.memoryLimit = memoryLimit;
		this.description = description;
		this.inputFormat = inputFormat;
		this.outputFormat = outputFormat;
		this.sampleInput = sampleInput;
		this.sampleOutput = sampleOutput;
		this.hint = hint;
	}
	

	public Problem(long problemId, boolean isPublic, String problemName, int timeLimit, 
					int memoryLimit, String description, String inputFormat, String outputFormat, 
					String sampleInput, String sampleOutput, String hint) { 
		this(isPublic, problemName, timeLimit, memoryLimit, description, inputFormat, outputFormat, sampleInput, sampleOutput, hint);
		this.problemId = problemId;
	}

	public long getProblemId() {
		return problemId;
	}


	public void setProblemId(long problemId) {
		this.problemId = problemId;
	}
	
	
	public boolean isPublic() {
		return isPublic;
	}


	public void setPublic(boolean isPublic) {
		this.isPublic = isPublic;
	}


	public String getProblemName() {
		return problemName;
	}


	public void setProblemName(String problemName) {
		this.problemName = problemName;
	}

	public int getTimeLimit() {
		return timeLimit;
	}


	public void setTimeLimit(int timeLimit) {
		this.timeLimit = timeLimit;
	}


	public int getMemoryLimit() {
		return memoryLimit;
	}


	public void setMemoryLimit(int memoryLimit) {
		this.memoryLimit = memoryLimit;
	}
	

	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}

	public String getInputFormat() {
		return inputFormat;
	}


	public void setInpuFormatt(String inputFormat) {
		this.inputFormat = inputFormat;
	}


	public String getOutputFormat() {
		return outputFormat;
	}


	public void setOutputFormat(String outputFormat) {
		this.outputFormat = outputFormat;
	}


	public String getSampleInput() {
		return sampleInput;
	}


	public void setSampleInput(String sampleInput) {
		this.sampleInput = sampleInput;
	}


	public String getSampleOutput() {
		return sampleOutput;
	}


	public void setSampleOutput(String sampleOutput) {
		this.sampleOutput = sampleOutput;
	}


	public String getHint() {
		return hint;
	}


	public void setHint(String hint) {
		this.hint = hint;
	}
	

	@Override
	public String toString() {
		return String.format("Problem: [ProblemID=%s, isPublic=%s, ProblemName=%s, TimeLimit=%s, "
							+ "MemoryLimit=%s, Description=%s, InputFormat=%s, OutputFormat=%s, " 
							+ "SampleInput=%s, SampleOutput=%s, Hint=%s]", 
				new Object[] { problemId, isPublic, problemName, timeLimit, memoryLimit, 
								description, inputFormat, outputFormat, sampleInput, sampleOutput, hint});
	}

	
}