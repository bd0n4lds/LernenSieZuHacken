import java.io.IOException;
import java.io.InputStream;

public interface AssetManagerAPI {
    InputStream open(String fileName) throws IOException;

    String[] getLocales();

    void close();
}