public class Main {

    public static void main(String[] args) {

        Stack<Integer> myStack = new Stack<>();

        myStack.push(10);
        myStack.push(100);
        myStack.push(1000);

        System.out.println(myStack.size());

        System.out.println("This is the last item in the stack: " + myStack.pop());

    }
}
