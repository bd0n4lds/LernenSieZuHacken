
import java.util.function.Function;

public class _Function
{
    static int incrementByOne(int number)
    {
        return number + 1;
    }

    static Function<Integer, Integer> incrementByOneFunction = number -> number + 1;
}