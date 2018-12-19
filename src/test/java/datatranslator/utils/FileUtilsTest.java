package datatranslator.utils;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.logging.Logger;

import static org.junit.Assert.*;

public class FileUtilsTest {

	private Logger logger = Logger.getLogger(FileUtilsTest.class.getName());
	private static final Path TEST_FILE = Paths.get("test.txt");
	private static final Path TEST_OUTPUT_FILE = Paths.get("test_output.txt");

	@Before
	public void before() {
		logger.info("setting up tests");
	}

	@After
	public void after() throws IOException {
		Files.deleteIfExists(TEST_OUTPUT_FILE);
		logger.info("tests complete");
	}

	@Test
	public void writeDataToFile() throws IOException {
		assertTrue(FileUtils.writeDataToFile("this is a test", TEST_OUTPUT_FILE));
		assertTrue(Files.exists(TEST_OUTPUT_FILE));
	}

	@Test
	public void readFile() throws IOException {
		List<String> lines = FileUtils.readFile(TEST_FILE);
		assertFalse(lines.isEmpty());
		assertEquals(3, lines.size());
	}

	@Test(expected = IOException.class)
	public void testReadFileException() throws IOException {
		FileUtils.readFile(Paths.get("wrong"));
	}
}