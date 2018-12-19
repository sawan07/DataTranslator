package datatranslator;

import datatranslator.utils.FileUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

import static org.junit.Assert.assertEquals;

public class DataConfiguratorTest {

	private Logger logger = Logger.getLogger(DataConfiguratorTest.class.getName());

	private static final Path TEST_COLUMN_CONFIG = Paths.get("testColumnConfig.txt");
	private static final Path TEST_ID_CONFIG = Paths.get("testIdConfig.txt");
	private static final Path TEST_FILE = Paths.get("test.txt");
	private List<List<String>> testRows = new ArrayList<>();
	private String[] testColumnHeader;

	@Before
	public void before() throws IOException {
		logger.info("setting up tests");
		List<String> lines = FileUtils.readFile(TEST_FILE);

		testColumnHeader = lines.get(0).split("\t");

		lines.remove(0);

		for (String s : lines) {
			String[] dataPerRow = s.split("\t");
			testRows.add(Arrays.asList(dataPerRow));
		}
	}

	@After
	public void after() {
		logger.info("tests complete");
	}


	@Test
	public void configureRows() throws IOException {
		assertEquals("OURID002", DataConfigurator.configure().configureColumn
				(DataConfigurator.configure().rowToColumn(
						DataConfigurator.configure().configureRows(testRows,
								TEST_ID_CONFIG)
						, testColumnHeader),
						testColumnHeader,
						TEST_COLUMN_CONFIG).get(0).get(1));
	}

	@Test
	public void configureColumn() throws IOException {
		assertEquals("OURID002", DataConfigurator.configure().configureRows(testRows, TEST_ID_CONFIG).get(0).get(0));
	}


}