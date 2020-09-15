import android.os.Looper;

/**
 * Utils
 *
 * @author
 * @date
 */
public final class ThreadUtils {
    private ThreadUtils() {
    }

    public static boolean isMainThread() {
        return Looper.getMainLooper().getThread() == Thread.currentThread();
    }
}
