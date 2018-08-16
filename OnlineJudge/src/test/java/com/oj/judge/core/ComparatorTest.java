package com.oj.judge.core;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;

import org.junit.*;
import static org.junit.Assert.*;


import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @author Tiger
 */

public class ComparatorTest {

	@Before
	public static void setUp() throws IOException {
		FileOutputStream stdOutputStream = null;
		FileOutputStream outputStream = null;
		// Make Directory for TestCases
		File checkpointsDirFile = new File("/tmp/oj-matcher-tests");
		if (!checkpointsDirFile.exists()) {
			checkpointsDirFile.mkdirs();
		}
		// TestCase: MatchExactly
		stdOutputStream = new FileOutputStream(new File(
				"/tmp/oj-matcher-tests/match-exactly-std.txt"));
		outputStream = new FileOutputStream(new File(
				"/tmp/oj-matcher-tests/match-exactly.txt"));
		String matchExactlyString = "32768";
		IOUtils.write(matchExactlyString, stdOutputStream);
		IOUtils.write(matchExactlyString, outputStream);
		// TestCase: MatchWithSpaces
		stdOutputStream = new FileOutputStream(new File(
				"/tmp/oj-matcher-tests/match-with-spaces-std.txt"));
		outputStream = new FileOutputStream(new File(
				"/tmp/oj-matcher-tests/match-with-spaces.txt"));
		String matchWithSpacesString = "Output Test with Spaces";
		IOUtils.write(matchWithSpacesString, stdOutputStream);
		IOUtils.write(matchWithSpacesString + " \n  \n", outputStream);
		// TestCase: Mismatch
		stdOutputStream = new FileOutputStream(new File(
				"/tmp/oj-matcher-tests/mimatch-std.txt"));
		outputStream = new FileOutputStream(new File(
				"/tmp/oj-matcher-tests/mimatch.txt"));
		String mismatchString1 = "45652  \n\n";
		String mismatchString2 = "24334";
		IOUtils.write(mismatchString1, stdOutputStream);
		IOUtils.write(mismatchString2, outputStream);
	}


	@After
	public static void tearDown() {
		File checkpointsDirFile = new File("/tmp/oj-matcher-tests");
		if (checkpointsDirFile.exists()) {
			checkpointsDirFile.delete();
		}
	}

	@Test
	public void testMatchExactly() throws IOException {
		String standardOutputFilePath = "/tmp/oj-matcher-tests/match-exactly-std.txt";
		String outputFilePath = "/tmp/oj-matcher-tests/match-exactly.txt";
		assertTrue(comparator.isOutputTheSame(standardOutputFilePath, outputFilePath));
	}

	@Test
	public void testMatchWithSpaces() throws IOException {
		String standardOutputFilePath = "/tmp/oj-matcher-tests/match-with-spaces-std.txt";
		String outputFilePath = "/tmp/oj-matcher-tests/match-with-spaces.txt";
		assertTrue(comparator.isOutputTheSame(standardOutputFilePath,
				outputFilePath));
		assertTrue(comparator.isOutputTheSame(outputFilePath, standardOutputFilePath));
	}

	@Test
	public void testMismatch() throws IOException {
		String standardOutputFilePath = "/tmp/oj-matcher-tests/mimatch-std.txt";
		String outputFilePath = "/tmp/oj-matcher-tests/mimatch.txt";
		assertFalse(comparator.isOutputTheSame(standardOutputFilePath, outputFilePath));
	}

	private Comparator comparator;
}
