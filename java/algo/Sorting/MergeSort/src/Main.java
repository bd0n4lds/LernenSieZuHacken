import java.util.Random;

public class Main {

    public static void main(String[] args) {

        Random random = new Random();

        // Created the nums array
        int[] nums = new int[30];

        // Filled the array with random values
        for ( int j = 0; j < nums.length; j++ ) {
            nums[j] = random.nextInt(1000) - 500;
        }

        // O(n logn)
        // Created mergeSort using nums array
        MergeSort mergeSort =  new MergeSort(nums);

        // Used mergeSort method. Indicated the low and high parameters
        mergeSort.mergeSort(0, nums.length - 1);

        // Showing the result of the merge
        mergeSort.showResult();

    }
}
