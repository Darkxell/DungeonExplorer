package util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

/** Static class that holds util static methods to manipulate text files. */
public abstract class TextFileUtility {

	/**
	 * Gets the text content of a Text file. Returns the whole thing in a single
	 * line.
	 */
	public static String getTextFileContent(File file) {
		String filestring = "";
		StringBuilder builder = new StringBuilder();
		try {
			BufferedReader br = new BufferedReader(new FileReader(file.getPath()));
			String line;
			while ((line = br.readLine()) != null)
				builder.append(line);
			br.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		filestring = builder.toString();
		return filestring.toLowerCase();
	}

	/**
	 * @return a String representation of this inputstream's content
	 */
	public static String fromInputStream(InputStream is) {
		String filestring = "";
		StringBuilder builder = new StringBuilder();
		try (InputStreamReader streamReader = new InputStreamReader(is, StandardCharsets.UTF_8);
				BufferedReader reader = new BufferedReader(streamReader)) {
			String line;
			while ((line = reader.readLine()) != null)
				builder.append(line);
		} catch (IOException e) {
			e.printStackTrace();
		}
		filestring = builder.toString();
		return filestring.toLowerCase();
	}

}
