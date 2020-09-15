public class HashTable {

    private HashItem[] hashTable;
    private int count;

    public HashTable(int size) {
        hashTable = new HashItem[size];
    }

    // Getting Values from the Hash Table
    public int get(int key) {

        int index = hash(key);

        if ( hashTable[index] == null ) {
            return -1;
        } else {

            HashItem hashItem = hashTable[index];

            while (hashItem != null && hashItem.getKey() != key ) {
                hashItem = hashItem.getNextHashItem();
            }

            if ( hashItem == null ) {
                return -1;
            } else {
                return hashItem.getValue();
            }

        }

    }

    // Putting Values into the Hash Table
    public void put( int key, int value ) {
        int index = hash(key);

        if ( hashTable[index] == null ) {
            // There is NO collision
            System.out.println("There is no collision");
            hashTable[index] = new HashItem(key, value);
        } else {
            // There is a collision
            System.out.println("Collision when inserting with a key " + key);
            HashItem hashItem = hashTable[index];

            while ( hashItem.getNextHashItem() != null ) {
                // Adding another value to the Link list
                hashItem = hashItem.getNextHashItem();
                System.out.println("Considering the item in the linked list " + hashItem.getValue());
            }
            // Found the place to insert hash item
            System.out.println("Finally we have fond the place to insert");
            hashItem.setNextHashItem( new HashItem(key, value));

        }
    }


    private int hash(int key) {
        return key % hashTable.length;
    }
}
