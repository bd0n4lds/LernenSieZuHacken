import java.util.Locale;

/**
 * OsUtils
 *
 * @author
 * @date
 */
public class OsUtils {

    /**
     * @return
     */
    public static boolean isOsX() {
        return System.getProperty("os.name").toLowerCase(Locale.US).contains("os x");
    }

    /**
     * @return
     */
    public static boolean isLinux() {
        return System.getProperty("os.name").equalsIgnoreCase("linux");
    }

    /**
     * @return
     */
    public static boolean isWindows() {
        return System.getProperty("os.name").equalsIgnoreCase("windows");
    }
}