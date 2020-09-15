import java.util.Random;

/**
 * IDUtils to generate unique Ids
 *
 * @author
 * @date
 */
public class IDUtils {

    /**
     * Get a random int to be used as id
     *
     * @return
     */
    public static int getRandomIntID() {
        Random rand = new Random();
        return rand.nextInt();
    }

    /**
     * Get a random string to be used as id
     *
     * @return
     */
    public static String getRandomStringID() {
        int rand = getRandomIntID();
        return Integer.toString(rand);
    }
}