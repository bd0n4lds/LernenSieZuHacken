public class Stack<T> {

    // Stack Arrays are not dynamic. You have to set the size of the array
    private T[] stack;
    private int count;

    public Stack() {
        this.stack = ( T[] ) new Object[1];
    }

    public void push ( T newData ) {
        // If stack is full, the array will double in size
        if( count == this.stack.length ) {
            resize( 2 * this.stack.length );
        }

        this.stack[count++] = newData;
    }

    public T pop() {

        T itemToPop = this.stack[--count];

        if( count > 0 && count == this.stack.length / 4) {
            resize(this.stack.length / 2);
        }

        return itemToPop;
    }

    // Empty stack
    public boolean isEmpty() {
        return this.count == 0;
    }

    // Reports the size of the size
    public int size() {
        return this.count;
    }

    // 0(N) Linear time capacity
    public void resize( int capacity) {
        T[] stackCopy = (T[]) new Object[capacity];

        // Copy the items from old stack to new stack
        for ( int i = 0; i < count; i++ ) {
            stackCopy[i] = this.stack[i];
        }

        this.stack = stackCopy;
    }
}
