package datatranslator.utils;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;

public class FileUtils {

	private FileUtils() {
	}

	/**
	 * writing content to file
	 *
	 * @param content    the content to write to the file
	 * @param outPutPath the file path to write to
	 * @return true if the file is successfully written
	 * @throws IOException if there is an I/O exception during the write process
	 */
	public static Boolean writeDataToFile(String content, Path outPutPath) throws IOException {

		Files.deleteIfExists(outPutPath);
		BufferedWriter writer = new BufferedWriter(new FileWriter(outPutPath.toFile()));

		writer.write(content);
		writer.close();

		return Files.exists(outPutPath);
	}

	/**
	 * lazily read file from the given path and present the contents
	 *
	 * @param path path to the file
	 * @return List of lines as String
	 * @throws IOException if an I/O Exception occurs while reading a file
	 */
	public static List<String> readFile(Path path) throws IOException {
		return Files.lines(path).collect(Collectors.toList());
	}


}
