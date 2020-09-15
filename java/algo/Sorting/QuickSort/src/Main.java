public class Main {

    public static void main(String[] args) {

        // Created nums array
        int[] nums = { 1, 5, 3, 7, 6, 9, 8, 3, 2 };

        // Used QuickSort class. Applied nums array to class
        QuickSort quickSort = new QuickSort(nums);

        // Sorting the array using: Quick Sort method and Partition method
        quickSort.sort();

        // Displaying the array
        quickSort.showArray();
    }
}
