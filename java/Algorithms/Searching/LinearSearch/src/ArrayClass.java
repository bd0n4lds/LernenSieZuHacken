public class ArrayClass {

    private int[] arr;
    private int nElements =  arr.length;
    int temp;
    String value;

    // Create an Array
    public ArrayClass( int size ) {
        arr = new int[size];
    }

    // Input contents of Array
    public void setElements(int index, int value) {
        arr[index] = value;
    }

    // Get contents of Array
    public int getElements( int index) {
        return arr[index];
    }

    public int ascending( int[] arr ) {
        for ( int i = startingPoint; i < arr.length; i++) {
            for ( int j = i + 1; j < arr.length; j++ ) {
                if ( arr[i] > arr[j]) {
                    temp = arr[i];
                    arr[i] = arr[j];
                    arr[j] = temp;
                }
            }
        }

        return arr[startingPoint];
    }

}
