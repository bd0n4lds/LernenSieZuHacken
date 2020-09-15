import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;

import java.io.IOException;

/**
 * Utils
 *
 * @author
 * @date
 */

public class JsonUtils {
    public static String readNullSafeString(JsonReader reader) throws IOException {
        if (reader.peek() == JsonToken.NULL) {
            reader.nextNull();
            return null;
        } else {
            return reader.nextString();
        }
    }

    public static Long readNullSafeLong(JsonReader reader) throws IOException {
        if (reader.peek() == JsonToken.NULL) {
            reader.nextNull();
            return null;
        } else {
            return reader.nextLong();
        }
    }

    public static Integer readNullSafeInteger(JsonReader reader) throws IOException {
        if (reader.peek() == JsonToken.NULL) {
            reader.nextNull();
            return null;
        } else {
            return reader.nextInt();
        }
    }

    public static Double readNullSafeDouble(JsonReader reader) throws IOException {
        if (reader.peek() == JsonToken.NULL) {
            reader.nextNull();
            return null;
        } else {
            return reader.nextDouble();
        }
    }

    public static Float readNullSafeFloat(JsonReader reader) throws IOException {
        if (reader.peek() == JsonToken.NULL) {
            reader.nextNull();
            return null;
        } else {
            return (float) reader.nextDouble();
        }
    }

    public static Boolean readNullSafeBoolean(JsonReader reader) throws IOException {
        if (reader.peek() == JsonToken.NULL) {
            reader.nextNull();
            return null;
        } else {
            return reader.nextBoolean();
        }
    }
}
