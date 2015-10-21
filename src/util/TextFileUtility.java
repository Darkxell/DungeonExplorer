package util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

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
	    BufferedReader br = new BufferedReader(new FileReader(
		    file.getPath()));
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

}
