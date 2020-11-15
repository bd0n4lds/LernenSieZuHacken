public class Stack<T extends Comparable<T>> {

    private Node<T> root;
    private int count;

    // O(1)
    public void push(T newData) {
        this.count++;

        if (this.root == null) {
            this.root = new Node<>(newData);
        } else {
            Node<T> oldRoot = this.root;
            this.root = new Node<>(newData);
            this.root.setNextNode(oldRoot);
        }
    }

    // 0(1)
    public T pop() {
        T itemToPop = this.root.getData();
        this.root = this.root.getNextNode();
        this.count--;
        return itemToPop;
    }

    // 0(1)
    public int size() {
        return count;
    }

    // 0(1)
    public boolean isEmpty() {
        return this.root == null;
    }

}
