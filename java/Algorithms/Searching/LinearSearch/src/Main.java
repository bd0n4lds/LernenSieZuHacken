import java.lang.reflect.Array;
import java.util.*;

public class Main {

    public static void main(String[] args) {
    /*
        Given an array arr[] of n elements, write a function to search
        a given element x in arr[].
    */
    ArrayClass arr;
    arr = new ArrayClass(10);

    int selectedNumber;
    selectedNumber = 82;

    int nElements = 10;

    arr.setElements(0,23);
    arr.setElements(1,71);
    arr.setElements(2,82);
    arr.setElements(3,6);
    arr.setElements(4,25);
    arr.setElements(5,34);
    arr.setElements(6,56);
    arr.setElements(7,89);
    arr.setElements(8,17);
    arr.setElements(9,37);

    for (int i = 0; i < nElements; i++) {
        System.out.print(arr.getElements(i) + " ");
    }

    System.out.println(" ");


    for ( int i = 0; i < nElements; i++) {
        if ( selectedNumber == arr.getElements(i)) {
            System.out.println( selectedNumber + " is in the array!");
        }
    }



    // Arrange Array in ascending order
        System.out.println(arr.ascending(0));

/*

        System.out.println(" ");

    // Arrange Descending in descending order
    for ( int i = 0; i < arr.length; i++) {
        for ( int j = i + 1; j < arr.length; j++ ) {
            if ( arr[i] < arr[j]) {
                temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }

        System.out.print(arr[i] + " ");
    }

    System.out.println(" ");
*/



    }
}
