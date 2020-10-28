public class MergeSort {

    private int[] nums; // One dimensional array of integers
    private int[] tempArray; // Not in place algorithm. Needs extra memory

    public MergeSort( int[] nums ) { // numbers on items in array
        this.nums = nums;
        tempArray = new int[nums.length]; // matches the numbers array
    }

    // Show result of Merge Sort
    public void showResult() {
        for ( int i = 0; i < nums.length; i++ ) {
            System.out.print(nums[i] + " ");
        }
    }

    // Divide the array into two seperate arrays
    public void mergeSort(int low, int high) {

        // Means there is a single item in the sub array
        if(low >= high) return;

        // Calculate the middle index
        int middle = (low + high) / 2;

        mergeSort(low, middle);
        mergeSort(middle + 1, high);
        merge(low, middle, high);

    }

    // Merging sub arrays together
    private void merge( int low, int middle, int high ) {

        // Copy nums[i] -> temp[i]
        for ( int i = low; i <= high; i++ ) {
            tempArray[i] = nums[i];
        }

        int i = low; // Tracks items in the left sub array
        int j = middle + 1; // Tracks items in the right sub array
        int k = low; // points to the empty slot in the result array

        // Copy the smallest values from either the left or the right side back
        // to the original array (result array)
        while ((i <= middle) && (j <= high)) {
            if (tempArray[i] <= tempArray[j]) {
                nums[k] = tempArray[i];
                i++;
            } else {
                nums[k] = tempArray[j];
                j++;
            }

            k++;
        }

        // Copy the rest of the left side of the array into the target array
        while ( i <= middle ) {
            nums[k] = tempArray[i];
            k++;
            i++;
        }

        // Copy the rest of the right side of the array into the target array
        while ( j <= high ) {
            nums[k] = tempArray[j];
            k++;
            j++;
        }

    }

    // Helper method that lets is use whether array is sorted or not
    public boolean isSorted() {
        for ( int i = 0; i < nums.length - 1; i++ ) {
            if ( nums[i] > nums[i + 1]) {
                return false;
            }
        }

        return true;

    }

}
