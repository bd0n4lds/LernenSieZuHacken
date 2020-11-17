import java.util.function.Consumer;

/**
 * Callbacks
 *
 * @author Brian Donaldson
 * @date 11/11/2020
 */
public class Main {
    public static void main(String[] args) {

        hello("John", null, value -> {
            System.out.println("no lastName provided for " + value);
        });

        hello2("John",
                null,
                () -> System.out.println("no lastName provided"));

    }

    /**
     * Displays First and last Name
     *
     * @param firstName This is the first parameter to hello method
     * @param lastName  This is the second parameter to hello method
     * @param callback  This is the third parameter to hello method
     */
    static void hello(String firstName, String lastName, Consumer<String> callback) {
        System.out.println(firstName);
        if (lastName != null) {
            System.out.println(lastName);
        } else {
            callback.accept(firstName);
        }
    }

    /**
     * Displays First and last Name
     *
     * @param firstName This is the first parameter to hello2 method
     * @param lastName  This is the second parameter to hello2 method
     * @param callback  This is the third parameter to hello2 method
     */
    static void hello2(String firstName, String lastName, Runnable callback) {
        System.out.println(firstName);
        if (lastName != null) {
            System.out.println(lastName);
        } else {
            callback.run();
        }
    }
}