public class BinarySearch {
    private int[] list;
    private int key;

    public int binarySearch(int list[], int key) {
        sort(list);

        int low = 0;
        int high = list.length - 1;

        while ( high >= low ) {
            int mid = (low + high) / 2;

            if ( key < list[mid]) {
                high = mid - 1;
            } else if ( key == list[mid] ) {
                return list[mid];
            } else {
                low = mid + 1;
            }
        }
        return -low - 1;
    }

    // Helper method: makes sure array is sorted from smallest to largest
    private void sort(int list[]) {
        for ( int i = 0; i < list.length; i++ ) {
            for ( int j = i + 1; j < list.length; j++) {
                if (list[i] > list[j]) {
                    int temp = list[i];
                    list[i] = list[j];
                    list[j] = temp;
                }
            }
        }
    }

}
