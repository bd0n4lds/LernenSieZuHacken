public class Queue<T extends Comparable<T>> {

    private Node<T> firstNode; // Pointer: points to the first node in the Queue
    private Node<T> lastNode; // Pointer: points to the last node in the Queue
    private int count;

    // Checks if the Queue is empty
    public boolean isEmpty() {
        return this.firstNode == null;
    }

    // Give you the current size of the container
    public int size() {
        return this.count;
    }

    // O(1) Adding data to the container
    public void enqueue( T newData) {

        this.count++;

        Node<T> oldLastNode = this.lastNode;
        this.lastNode = new Node<>(newData);
        this.lastNode.setNextNode(null);

        // If container is empty the first node is the last node
        if ( isEmpty() ) {
            this.firstNode = this.lastNode;
        } else {
            oldLastNode.setNextNode(this.lastNode);
        }

    }

    // O(1)
    public T dequeue() {

        this.count--;

        T dataToDequeue = this.firstNode.getData();
        this.firstNode = this.firstNode.getNextNode();

        if ( isEmpty() ) {
            this.lastNode = null;
        }

        return dataToDequeue;

    }
}
