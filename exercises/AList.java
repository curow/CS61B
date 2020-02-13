/** Array based list.
 *  @author Josh Hug
 */

public class AList {
    private int[] innerArray;
    private int size;
    /** Creates an empty list. */
    public AList() {
        innerArray = new int[101];
        size = 0;
    }

    /** Inserts X into the back of the list. */
    public void addLast(int x) {
        innerArray[size++] = x;
    }

    /** Returns the item from the back of the list. */
    public int getLast() {
        return innerArray[size - 1];        
    }
    /** Gets the ith item in the list (0 is the front). */
    public int get(int i) {
        return innerArray[i];        
    }

    /** Returns the number of items in the list. */
    public int size() {
        return size;        
    }

    /** Deletes item from back of the list and
      * returns deleted item. */
    public int removeLast() {
        return innerArray[--size];
    }
} 
