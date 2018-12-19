package datatranslator;

import datatranslator.utils.FileUtils;

import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

class Translator {

	private static final Logger logger = Logger.getLogger(Translator.class.getName());


	/**
	 * dataFile the file name to translate
	 */
	private final Path dataPath;
	private final Path columnConfigFile;
	private final Path idConfigFile;

	private Translator(Path dataPath, Path columnConfigFile, Path idConfigFile) {
		this.dataPath = dataPath;
		this.columnConfigFile = columnConfigFile;
		this.idConfigFile = idConfigFile;
	}

	static Translator of(Path dataPath, Path columnConfigFile, Path idConfigFile) {
		return new Translator(dataPath, columnConfigFile, idConfigFile);
	}


	/**
	 * This is the translator function which is
	 * reading data from the existing and based
	 * on the column and row configuration creating
	 * a new file with translated data
	 *
	 * @throws IOException while reading/writing file from/to directory
	 */
	String translate() throws IOException {

		List<String> lines = FileUtils.readFile(dataPath);

		String[] columnHeaders = lines.get(0).split("\t");
		lines.remove(0);

		List<List<String>> rows = new ArrayList<>();
		for (String s : lines) {
			String[] dataPerRow = s.split("\t");
			rows.add(Arrays.asList(dataPerRow));
		}

		return DataConfigurator.configure()
				.convertColumnToString(processData(rows, columnHeaders));
	}


	/**
	 * Processing the existing data to a new sets of data
	 *
	 * @param rows          List of existing rows (List<String>) without the column header
	 * @param columnHeaders Array of column headers
	 * @return List of columns (List<String>) as processed data
	 * @throws IOException while reading file from directory
	 */
	private List<List<String>> processData(List<List<String>> rows, String[] columnHeaders) throws IOException {
		DataConfigurator configurator = DataConfigurator.configure();
		List<List<String>> rowList = configurator.configureRows(rows, idConfigFile);

		List<List<String>> columnList = configurator.rowToColumn(rowList, columnHeaders);

		return configurator.configureColumn(columnList, columnHeaders, columnConfigFile);
	}

}
