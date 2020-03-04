import java.lang.management.ManagementFactory;

/**
 * A String-like class that allows users to add and remove characters in the String
 * in constant time and have a constant-time hash function. Used for the Rabin-Karp
 * string-matching algorithm.
 */
class RollingString{

    /**
     * Number of total possible int values a character can take on.
     * DO NOT CHANGE THIS.
     */
    static final int UNIQUECHARS = 128;

    /**
     * The prime base that we are using as our mod space. Happens to be 61B. :)
     * DO NOT CHANGE THIS.
     */
    static final int PRIMEBASE = 6113;

    private static class Node {
        public int value;
        public Node next;
        public Node prev;

        public Node(int value) {
            this.value = value;
        }
    }

    private Node sentinel;
    private int hashCode;
    private int highestWeight;

    /**
     * Initializes a RollingString with a current value of String s.
     * s must be the same length as the maximum length.
     */
    public RollingString(String s, int length) {
        assert(s.length() == length);
        sentinel = new Node(length);
        Node last = null;
        Node current = sentinel;
        for (char ch : s.toCharArray()) {
            last = new Node((int) ch);
            current.next = last;
            last.prev = current;
            current = last;
        }
        assert last != null;
        last.next = sentinel;
        sentinel.prev = last;

        current = sentinel.next;
        for (hashCode = 0; current != sentinel; current = current.next) {
            hashCode *= Math.floorMod(UNIQUECHARS, PRIMEBASE);
            hashCode = Math.floorMod(hashCode, PRIMEBASE);
            hashCode += Math.floorMod(current.value, PRIMEBASE);
            hashCode = Math.floorMod(hashCode, PRIMEBASE);
        }

        highestWeight = 1;
        for (int i = 0; i < length - 1; i++) {
            highestWeight *= Math.floorMod(UNIQUECHARS, PRIMEBASE);
            highestWeight = Math.floorMod(highestWeight, PRIMEBASE);
        }
    }

    /**
     * Adds a character to the back of the stored "string" and 
     * removes the first character of the "string". 
     * Should be a constant-time operation.
     */
    public void addChar(char c) {
        Node first = sentinel.next;
        Node last = sentinel.prev;
        Node current = new Node((int) c);
        sentinel.next = first.next;
        sentinel.next.prev = sentinel;
        last.next = current;
        current.prev = last;
        current.next = sentinel;
        sentinel.prev = current;

        hashCode -= Math.floorMod(first.value * highestWeight, PRIMEBASE);
        hashCode = Math.floorMod(hashCode, PRIMEBASE);
        hashCode *= Math.floorMod(UNIQUECHARS, PRIMEBASE);
        hashCode = Math.floorMod(hashCode, PRIMEBASE);
        hashCode += c;
        hashCode = Math.floorMod(hashCode, PRIMEBASE);
    }


    /**
     * Returns the "string" stored in this RollingString, i.e. materializes
     * the String. Should take linear time in the number of characters in
     * the string.
     */
    public String toString() {
        StringBuilder strb = new StringBuilder();
        for (Node current = sentinel.next; current != sentinel; current =
                current.next) {
            strb.append((char) current.value);
        }
        return strb.toString();
    }

    /**
     * Returns the fixed length of the stored "string".
     * Should be a constant-time operation.
     */
    public int length() {
        return sentinel.value;
    }


    /**
     * Checks if two RollingStrings are equal.
     * Two RollingStrings are equal if they have the same characters in the same
     * order, i.e. their materialized strings are the same.
     */
    @Override
    public boolean equals(Object o) {
        if (o == null) return false;
        if (this.getClass() != o.getClass()) return false;
        return toString().equals(o.toString());
    }

    /**
     * Returns the hashcode of the stored "string".
     * Should take constant time.
     */
    @Override
    public int hashCode() {
        return hashCode;
    }
}
