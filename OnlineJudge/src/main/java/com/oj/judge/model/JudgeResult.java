package com.oj.judge.model;
import java.io.Serializable;

/**
 * 
 * @author Tiger
 */
public class JudgeResult implements Serializable {
	
	private int judgeResultId;
	
	private String judgeResultSlug;
	
	private String judgeResultName;
	
	private static final long serialVersionUID = -1572920076997918781L;
	

	public JudgeResult() { }
	

	public JudgeResult(int judgeResultId, String judgeResultSlug, String judgeResultName) {
		this.judgeResultId = judgeResultId;
		this.judgeResultSlug = judgeResultSlug;
		this.judgeResultName = judgeResultName;
	}
	

	public int getJudgeResultId() {
		return judgeResultId;
	}


	public void setJudgeResultId(int judgeResultId) {
		this.judgeResultId = judgeResultId;
	}


	public String getJudgeResultSlug() {
		return judgeResultSlug;
	}


	public void setJudgeResultSlug(String judgeResultSlug) {
		this.judgeResultSlug = judgeResultSlug;
	}


	public String getJudgeResultName() {
		return judgeResultName;
	}


	public void setJudgeResultName(String judgeResultName) {
		this.judgeResultName = judgeResultName;
	}

	@Override
	public String toString() {
		return String.format("JudgeResult [Id=%d, Slug=%s, Name=%s]",
				new Object[] { judgeResultId, judgeResultSlug, judgeResultName });
	}


}
