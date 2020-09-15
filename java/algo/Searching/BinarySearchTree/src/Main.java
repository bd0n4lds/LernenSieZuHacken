public class Main {

    public static void main(String[] args) {
        Tree<Integer> bat = new BinarySearchTree<>();

        bat.insert(10);
        bat.insert(17);
        bat.insert(72);
        bat.insert(-33);
        bat.insert(-8);
        bat.insert(57);

        bat.delete(-8);

        bat.traversal();
    }
}
