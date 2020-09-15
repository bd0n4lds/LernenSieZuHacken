import java.lang.reflect.Field;

/**
 * ReflectionUtil
 *
 * @author
 * @date
 */
public class ReflectionUtil {

    /**
     * @return
     */
    public static <T> T getValue(String fieldName, Object object) {
        try {
            Field field = object.getClass().getSuperclass().getDeclaredField(fieldName);
            field.setAccessible(true);
            return (T) field.get(object);
        } catch (Exception e) {
            return null;
        }
    }
}
