
/**
 * BinarySearch
 *
 * @author
 * @date
 */
public class Main {
    public static void main(String[] args) {

        int[] arr = new int[11];

        arr[0] = 27;
        arr[1] = 23;
        arr[2] = 7;
        arr[3] = 14;
        arr[4] = 9;
        arr[5] = 54;
        arr[6] = 21;
        arr[7] = 19;
        arr[8] = 3;
        arr[9] = 78;
        arr[10] = 63;

        BinarySearch list = new BinarySearch();

        System.out.println(list.binarySearch(arr, 63));
    }
}
