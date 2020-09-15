public class Main {

    public static void main(String[] args) {
        HashTable hashTable = new HashTable(10);


            hashTable.put(0,10);
            hashTable.put(1,100);
            hashTable.put(2,1000);

        System.out.println("Hash is " + hashTable.get(2));

    }
}
