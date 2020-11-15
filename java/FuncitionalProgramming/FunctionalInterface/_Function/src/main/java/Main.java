import java.util.function.Function;

/**
 * _Function
 *
 * @author
 * @date
 */
public class Main {
    public static void main(String[] args) {
        // Function takes 1 argument and produces 1 result
        int increment = _Function.incrementByOne(1);
        System.out.println(increment);

        int increment2 = _Function.incrementByOneFunction.apply(1);
        System.out.println(increment2);

        int multiply = _Function.multiplyBy10Function.apply(increment2);
        System.out.println(multiply);

        Function<Integer, Integer> addBy1AndThenMultiplyBy10 =
                _Function.incrementByOneFunction.andThen(_Function.multiplyBy10Function);
        System.out.println(addBy1AndThenMultiplyBy10.apply(4));

        // BiFunction takes 2 argument and produces 1 result
        System.out.println(
                _Function.incrementByOneAndMultiply(4, 100)
        );

        System.out.println(
                _Function.incrementByOneAndMultiplyBiFunction.apply(4, 100)
        );
    }
}
