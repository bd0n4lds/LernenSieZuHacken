public class QuickSort {
    private int temp;
    private int[] nums;

    // Constructor: Array selected for function
    public QuickSort(int[] nums) {
        this.nums = nums;
    }

    // Sort method
    public void sort() {
        quicksort(0, nums.length - 1);
    }

    // Display items
    public void showArray() {
        for (int i = 0; i < nums.length; i++) {
            System.out.print(nums[i] + " ");
        }
    }

    // Quick sort method
    public void quicksort(int low, int high) {

        // When the low value becomes the high value exit
        if (low >= high) return;

        int pivot = partition(low, high);

        // Values on the left side of pivot
        quicksort(low, pivot - 1);

        // Values on the right side of pivot
        quicksort(pivot + 1, high);

    }

    // Grabs the middle index of array
    private int partition(int low, int high) {

        // Getting the middle pivot
        int pivotIndex = (low + high) / 2;

        // Swapping the pivot value with the high value
        swap(pivotIndex, high);

        // Initializing the low index
        int i = low;

        // Checking if values are lower or higher
        for (int j = low; j < high; j++) {

            // Ensures that items on the left side of pivot are smaller
            if (nums[j] <= nums[high]) {
                swap(i, j);
                i++;
            }
        }

        swap(i, high);
        return i;
    }

    // Swapping values
    private void swap(int i, int j) {
        temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}
