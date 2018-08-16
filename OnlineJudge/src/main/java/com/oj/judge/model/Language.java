package com.oj.judge.model;

import java.io.Serializable;

/**
 * @author Tiger
 */
public class Language implements Serializable {
	
	private int languageId;
	

	private String languageSlug;
	
	
	private String languageName;
	
	
	private String compileCommand;
	
	
	private String runCommand;

	
	private static final long serialVersionUID = 9065824880175832696L;

	public Language() { }
	

	public Language(String languageSlug, String languageName, String compileCommand, String runCommand) {
		this.languageSlug = languageSlug;
		this.languageName = languageName;
		this.compileCommand = compileCommand;
		this.runCommand = runCommand;
	}
	

	public Language(int languageId, String languageSlug, String languageName, String compileCommand, String runCommand) {
		this(languageSlug, languageName, compileCommand, runCommand);
		this.languageId = languageId;
	}

	public int getLanguageId() {
		return languageId;
	}
	

	public void setLanguageId(int languageId) {
		this.languageId = languageId;
	}
	

	public String getLanguageSlug() {
		return languageSlug;
	}
	

	public void setLanguageSlug(String languageSlug) {
		this.languageSlug = languageSlug;
	}
	

	public String getLanguageName() {
		return languageName;
	}
	

	public void setLanguageName(String languageName) {
		this.languageName = languageName;
	}
	

	public String getCompileCommand() {
		return compileCommand;
	}


	public void setCompileCommand(String compileCommand) {
		this.compileCommand = compileCommand;
	}


	public String getRunCommand() {
		return runCommand;
	}


	public void setRunCommand(String runCommand) {
		this.runCommand = runCommand;
	}

	@Override
	public String toString() {
		return String.format("Language [ID=%d, Slug=%s, Name=%s, CompileCommand=%s, runCommand=%s]",
				new Object[] { languageId, languageSlug, languageName, compileCommand, runCommand });
	}
	
}
