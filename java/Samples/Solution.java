public class Solution {

    public int[] twoSum(int[] nums, int target) {

        /* array to return the result */
        int[] arr = new int[2];

        /* map for num and index pair */
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();

        /* iterate through the array */
        for (int i = 0; i < nums.length; i++) {
            /* check if the map has an element which is
             * equal to the difference between the
             * target and current element */
            Integer val = map.get(target - nums[i]);
            if (val == null) {
                /* no match found, add the current item
                 * and index to map */
                map.put(nums[i], i);
            } else {
                /* match found, update the index values */
                arr[0] = val;
                arr[1] = i;
                break; // exit the loop
            }
        }
        return arr;
    }
}