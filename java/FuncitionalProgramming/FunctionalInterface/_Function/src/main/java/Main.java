
public class Main
{
    public static void main(String[] args)
    {
        // Function takes 1 argument and produces 1 result
        int increment = _Function.incrementByOne(1);
        System.out.println(increment);

        int increment2 = _Function.incrementByOneFunction.apply(1);
        System.out.println(increment2);

    }
}
