package com.oj.judge.model;

import java.io.Serializable;
import java.util.Date;

/**
 * submission model.
 * 
 * @author Tiger
 */
public class Submission implements Serializable {
	
	private long submissionId;
	
	private Problem problem;
	
	private long uid;

	private Language language;
	
	private Date submitTime;
	
	private Date executeTime;
	
	private int usedTime;
	
	private int usedMemory;
	
	private String judgeResultSlug;
	
	private int judgeScore;
	
	private String judgeLog;
	
	private String code;
	
	private static final long serialVersionUID = -6017296127470986766L;

	public Submission() { }
	
	/**
	 * Constructor.
	 * @param submissionId - unique number
	 * @param problem - problem being resolved
	 * @param uid - user id
	 * @param language - program language
	 * @param submitTime - submission time
	 * @param executeTime - start time
	 * @param usedTime - running time
	 * @param usedMemory - used memory
	 * @param judgeResultSlug - unique number of the result
	 * @param judgeScore - score of the submission
	 * @param judgeLog - log
	 * @param code - code being used
	 */
	public Submission(long submissionId, Problem problem, long uid, Language language, Date submitTime, 
			Date executeTime, int usedTime, int usedMemory, String judgeResultSlug, int judgeScore, 
			String judgeLog, String code) { 
		this.submissionId = submissionId;
		this.problem = problem;
		this.uid = uid;
		this.language = language;
		this.submitTime = submitTime;
		this.executeTime = executeTime;
		this.usedTime = usedTime;
		this.usedMemory = usedMemory;
		this.judgeResultSlug = judgeResultSlug;
		this.judgeScore = judgeScore;
		this.judgeLog = judgeLog;
		this.code = code;
	}
	

	public long getSubmissionId() {
		return submissionId;
	}
	

	public void setSubmissionId(long submissionId) {
		this.submissionId = submissionId;
	}
	

	public Problem getProblem() {
		return problem;
	}

	public void setProblem(Problem problem) {
		this.problem = problem;
	}
	

	public long getUid() {
		return uid;
	}
	

	public void setUid(long uid) {
		this.uid = uid;
	}
	

	public Language getLanguage() {
		return language;
	}
	

	public void setLanguage(Language language) {
		this.language = language;
	}
	

	public Date getSubmitTime() {
		return submitTime;
	}
	

	public void setSubmitTime(Date submitTime) {
		this.submitTime = submitTime;
	}
	

	public Date getExecuteTime() {
		return executeTime;
	}
	

	public void setExecuteTime(Date executeTime) {
		this.executeTime = executeTime;
	}
	

	public int getUsedTime() {
		return usedTime;
	}
	

	public void setUsedTime(int usedTime) {
		this.usedTime = usedTime;
	}
	

	public int getUsedMemory() {
		return usedMemory;
	}
	

	public void setUsedMemory(int usedMemory) {
		this.usedMemory = usedMemory;
	}
	

	public String getJudgeResultSlug() {
		return judgeResultSlug;
	}
	

	public void setJudgeResultSlug(String judgeResultSlug) {
		this.judgeResultSlug = judgeResultSlug;
	}
	

	public int getJudgeScore() {
		return judgeScore;
	}
	

	public void setJudgeScore(int judgeScore) {
		this.judgeScore = judgeScore;
	}
	

	public String getJudgeLog() {
		return judgeLog;
	}
	

	public void setJudgeLog(String judgeLog) {
		this.judgeLog = judgeLog;
	}
	

	public String getCode() {
		return code;
	}
	

	public void setCode(String code) {
		this.code = code;
	}
	

	@Override
	public String toString() {
		return String.format("Submission [ID=%d, Problem={%s}, Uid={%s}, Language={%s}, SubmitTime={%s}, "
				+ "ExecuteTime={%s}, UsedTime=%d, UsedMemory=%d, JudgeResultSlug={%s}, JudgeScore=%d, "
				+ "JudgeLog=%s, Code=%s]",
				new Object[] { submissionId, problem, uid, language, submitTime, executeTime, usedTime, usedMemory,
						judgeResultSlug, judgeScore, judgeLog, code});
	}
	
}
