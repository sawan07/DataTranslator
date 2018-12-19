package datatranslator;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.logging.Logger;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class TranslatorTest {

	private Logger logger = Logger.getLogger(TranslatorTest.class.getName());
	private static final Path TEST_FILE = Paths.get("test.txt");
	private static final Path TEST_COLUMN_CONFIG = Paths.get("testColumnConfig.txt");
	private static final Path TEST_ID_CONFIG = Paths.get("testIdConfig.txt");


	@Before
	public void before() {
		logger.info("setting up tests");
	}

	@After
	public void after() {
		logger.info("tests complete");
	}


	@Test
	public void translate() throws IOException {

		Translator translator = Translator.of(TEST_FILE, TEST_COLUMN_CONFIG, TEST_ID_CONFIG);
		String translated = translator.translate();

		String[] rows = translated.split("\n");
		assertTrue(!translated.isEmpty());
		assertEquals(11, rows.length);
		assertEquals("OURID002", rows[1].split("\t")[0]);
	}
}
