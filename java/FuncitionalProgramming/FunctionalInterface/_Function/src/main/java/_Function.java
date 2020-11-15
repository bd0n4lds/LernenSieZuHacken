
import java.util.function.BiFunction;
import java.util.function.Function;

/**
 * _Function
 *
 * @author
 * @date
 */
public class _Function {

    /**
     * @return
     */
    static int incrementByOne(int number) {
        return number + 1;
    }

    /**
     * @return
     */
    static Function<Integer, Integer> incrementByOneFunction = number -> number + 1;

    /**
     * @return
     */
    static int incrementByOneAndMultiply(int number, int numToMultiplyBy) {
        return (number + 1) * numToMultiplyBy;
    }

    /**
     * @return
     */
    static Function<Integer, Integer> multiplyBy10Function = number -> number * 10;

    /**
     * @return
     */
    static BiFunction<Integer, Integer, Integer> incrementByOneAndMultiplyBiFunction =
            (numberToIncrementByOne, numberToMultiplyBy)
                    -> (numberToIncrementByOne + 1) * numberToMultiplyBy;
}