import java.util.*;

public class Main 
{
    public static void main(String[] args) 
    {
        List<String> list = new ArrayList<>();

        list.add("Darron");
        list.add("Ade");
        list.add("Kevon");

        // Removes the 0 index of the array
        list.remove(0);

        // Gets the 1 index of the array
        System.out.println(list.get(0));

        // Foreach loop prints all items of the array
        for ( String a: list ) {
            System.out.print(a + " ");
        }

        System.out.println(" ");

        // Gets array size
        System.out.print(list.size());

    }
}