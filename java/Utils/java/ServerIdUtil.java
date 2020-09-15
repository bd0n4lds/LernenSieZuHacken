import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * ServerIdUtil
 *
 * @author
 * @date
 */
public class ServerIdUtil {

    private static final String SERVER_ID_PREFIX = "SERVER_ID_PREFIX:";
    private static Map<String, String> idCache = Collections.synchronizedMap(new HashMap<String, String>());

    public static void setLocalId(String serverId, String localId) {
        if (!serverId.startsWith(SERVER_ID_PREFIX)) {
            serverId = SERVER_ID_PREFIX + serverId;
        }
        idCache.put(serverId, localId);
    }

    /**
     * @return
     */
    public static String getLocalId(String serverId) {
        return idCache.get(serverId);
    }

    /**
     * @return
     */
    public static boolean containsServerId(String serverId) {
        return idCache.containsKey(serverId);
    }

    /**
     * @return
     */
    public static String prefixServerId(String serverId) {
        return SERVER_ID_PREFIX + serverId;
    }

    /**
     * @return
     */
    public static String removePrefix(String serverId) {
        return serverId.substring(SERVER_ID_PREFIX.length());
    }

    /**
     * @return
     */
    public static boolean isServerId(String serverId) {
        return serverId.startsWith(SERVER_ID_PREFIX);
    }
}