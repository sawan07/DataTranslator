package datatranslator;

import datatranslator.utils.FileUtils;

import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class DataConfigurator {

	private DataConfigurator() {

	}

	static DataConfigurator configure() {
		return new DataConfigurator();
	}


	/**
	 * converting List of processed data to String
	 *
	 * @param manipulatedData List of processed column (List<String>)
	 * @return String value of the content to be written in a new file.
	 */
	String convertColumnToString(List<List<String>> manipulatedData) {

		StringBuilder sb = new StringBuilder();

		for (int i = 0; i < manipulatedData.get(0).size(); i++) {
			for (List<String> list : manipulatedData) {
				sb.append(list.get(i)).append("\t");
			}
			sb.append("\n");
		}

		return sb.toString();
	}


	/**
	 * Configuting the rows of the existing data based on the mapping
	 * provided in thr row configuration text file
	 *
	 * @param rows List of unprocessed data as row (List<String>)
	 * @return List of processed rows (List<String>) after manipulating using the row configuration
	 * @throws IOException while reading file from directory
	 */
	List<List<String>> configureRows(List<List<String>> rows, Path rowConfigFile) throws IOException {
		List<List<String>> rowList = new ArrayList<>();
		Map<String, String> rowConfigMap = fileLinesToMap(FileUtils.readFile(rowConfigFile));
		for (List<String> s : rows) {
			String rowHeader = s.get(0);
			if (rowConfigMap.keySet().contains(rowHeader)) {
				s.set(0, rowConfigMap.get(rowHeader));
				rowList.add(s);
			}
		}
		return rowList;
	}

	/**
	 * Converting from List of rows(List<String>) to list of Column(List<String>)
	 *
	 * @param rowList       List of processed rows (List<String>)
	 * @param columnHeaders Array of column headers
	 * @return List of column (List<String>)
	 */
	List<List<String>> rowToColumn(List<List<String>> rowList, String[] columnHeaders) {
		List<List<String>> columnList = new ArrayList<>();
		for (int i = 0; i < columnHeaders.length; i++) {
			List<String> singleColumn = new ArrayList<>();

			for (List<String> s : rowList) {
				singleColumn.add(s.get(i));
			}
			columnList.add(singleColumn);
		}
		return columnList;
	}

	/**
	 * configuring the column of the data based on the mapping provided
	 * inside the column configuration text file
	 *
	 * @param columnList    List of column (List<String>)
	 * @param columnHeaders Array of column headers
	 * @return List of processed columns (List<String>) after manipulating using the column configuration
	 * @throws IOException while reading file from directory
	 */
	List<List<String>> configureColumn(List<List<String>> columnList, String[] columnHeaders, Path columnConfigFile) throws IOException {
		List<List<String>> finalData = new ArrayList<>();
		int index = 0;

		Map<String, String> columnConfigMap = fileLinesToMap(FileUtils.readFile(columnConfigFile));

		for (String s : columnHeaders) {
			if (columnConfigMap.keySet().contains(s)) {
				List<String> singleColumn = new ArrayList<>();
				singleColumn.add(columnConfigMap.get(s));
				singleColumn.addAll(columnList.get(index));
				finalData.add(singleColumn);
			}
			index++;
		}
		return finalData;
	}


	/**
	 * Mapping the content of configuration file in a map
	 *
	 * @param config lines of the file as list of string
	 * @return the resulting map
	 */
	private static Map<String, String> fileLinesToMap(List<String> config) {
		Map<String, String> configMap = new HashMap<>();
		for (String s : config) {
			String[] values = s.split("\\s+");
			configMap.put(values[0], values[1]);
		}
		return configMap;
	}
}
