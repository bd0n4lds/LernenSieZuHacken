public class Main {

    public static void main(String[] args) {
        FakeQueue<Integer> myQueue = new FakeQueue<>();

        myQueue.enqueue(10);
        myQueue.enqueue(100);
        myQueue.enqueue(1000);

        System.out.println(myQueue.size());

        System.out.println("The next item in line is: " + myQueue.dequeue());

    }
}
