package com.oj.judge.core;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.attribute.PosixFilePermission;
import java.util.HashSet;

import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.io.IOUtils;



import com.oj.judge.model.Language;
import com.oj.judge.model.Submission;
import com.oj.judge.exception.CreateDirectoryException;

/**
 * 
 * @author Tiger
 */

public class Preprocessor {
	/**
	 * create the test code on the server side.
	 * 
	 * @param submission 
	 * @param workDirectory
	 * @param baseFileName 
	 * @throws Exception 
	 */
	public void createTestCode(Submission submission, 
			String workDirectory, String baseFileName) throws Exception {
		File workDirFile = new File(workDirectory);
		if ( !workDirFile.exists() && !workDirFile.mkdirs() ) {
			throw new CreateDirectoryException("Failed to create directory: " + workDirectory);
		}
		//setWorkDirectoryPermission(workDirFile);
		
		Language language = submission.getLanguage();
		//String code = replaceClassName(language, submission.getCode(), baseFileName);
		String code = submission.getCode();
		String codeFilePath = String.format("%s/%s.%s", 
				new Object[] {workDirectory, baseFileName, getCodeFileSuffix(language)});
		
		FileOutputStream outputStream = new FileOutputStream(new File(codeFilePath));
		IOUtils.write(code, outputStream);
		IOUtils.closeQuietly(outputStream);
	}
	
	/**
	 * 
	 * @param language
	 * @return 
	 */
	private String getCodeFileSuffix(Language language) {
		String compileCommand = language.getCompileCommand();
		
		Pattern pattern = Pattern.compile("\\{filename\\}\\.((?!exe| ).)+");
		Matcher matcher = pattern.matcher(compileCommand);
		
		if ( matcher.find() ) {
			String sourceFileName = matcher.group();
			return sourceFileName.replaceAll("\\{filename\\}\\.", "");
		}
		return "";
	}
	

	/*private String replaceClassName(Language language, String code, String newClassName) {
		if ( !language.getLanguageName().equalsIgnoreCase("Java") ) {
			return code;
		}
		return code.replaceAll("class[ \n]+Main", "class " + newClassName);
	}
*/
	/**
	 */
	private void setWorkDirectoryPermission(File workDirectory) throws IOException {
		Set<PosixFilePermission> permissions = new HashSet<>();

		permissions.add(PosixFilePermission.OWNER_READ);
		permissions.add(PosixFilePermission.OWNER_WRITE);
		permissions.add(PosixFilePermission.OWNER_EXECUTE);

		permissions.add(PosixFilePermission.GROUP_READ);
		permissions.add(PosixFilePermission.GROUP_WRITE);
		permissions.add(PosixFilePermission.GROUP_EXECUTE);

		permissions.add(PosixFilePermission.OTHERS_READ);
		permissions.add(PosixFilePermission.OTHERS_WRITE);
		permissions.add(PosixFilePermission.OTHERS_EXECUTE);
		Files.setPosixFilePermissions(workDirectory.toPath(), permissions);
	}
	
}
