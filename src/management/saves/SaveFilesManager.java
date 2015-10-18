package management.saves;

/** Static class that manages all the savefiles. */
public abstract class SaveFilesManager {

    /**
     * The current file being used. This equals 1,2 or 3. This indicates from
     * wich file the data should be read and where it should be saved.
     */
    public static int currentFile = 1;

    public static boolean file1set;
    public static boolean file2set;
    public static boolean file3set;

    public static String file1name = "";
    public static String file2name = "";
    public static String file3name = "";
}
