import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

/**
 * Utils
 *
 * @author
 * @date
 */

public final class BundleUtils {

    private BundleUtils() {
    }

    public static Bundle maybeGetBundle(final @Nullable Bundle state, final @NonNull String key) {
        if (state == null) {
            return null;
        }

        return state.getBundle(key);
    }
}
