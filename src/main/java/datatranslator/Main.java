package datatranslator;

import datatranslator.utils.FileUtils;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.logging.Logger;

public class Main {

	private static final Logger logger = Logger.getLogger(Main.class.getName());

	public static void main(String[] args) throws IOException {

		Path dataFile = Paths.get("Data.txt");
		Path columnConfigFile = Paths.get("columnConfigFile.txt");
		Path idConfigFile = Paths.get("rowConfigFile.txt");
		Path outputFile = Paths.get("processedFile.txt");


		Translator translatorV2 = Translator.of(dataFile, columnConfigFile, idConfigFile);
		String processedData = translatorV2.translate();

		boolean written = FileUtils.writeDataToFile(processedData, outputFile);

		logger.info("file process completed. " +
				(written? "New file has been successfully created.":"New file could not be created!"));
	}
}
