
/**
 * HashMap Chaining
 *
 * @author
 * @date
 */
public class HashItem {
    private int key;
    private int value;
    private HashItem nextHashItem;

    public HashItem (int key, int value) {
        this.key = key;
        this.value = value;
    }

    /**
     * @return
     */
    public int getKey() {
        return key;
    }

    /**
     * @param key
     */
    public void setKey(int key) {
        this.key = key;
    }

    /**
     * @return
     */
    public int getValue() {
        return value;
    }

    /**
     * @param value
     */
    public void setValue(int value) {
        this.value = value;
    }

    /**
     * @return
     */
    public HashItem getNextHashItem() {
        return nextHashItem;
    }

    /**
     * @param nextHashItem
     */
    public void setNextHashItem(HashItem nextHashItem) {
        this.nextHashItem = nextHashItem;
    }
}
