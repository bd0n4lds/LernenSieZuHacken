/**
 * EnumUtil
 *
 * @author
 * @date
 */
public class EnumUtil {

    /**
     * @return
     */
    public static <T extends Enum<T>> T safeParse(Class<T> enumType, String value) {
        try {
            return Enum.valueOf(enumType, value.toUpperCase());
        } catch (Throwable t) {
            return null;
        }
    }
}
