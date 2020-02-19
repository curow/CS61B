import java.util.Iterator;
import java.util.Set;

public class ArraySet<T> implements Iterable<T> {
    private int size;
    private T[] items;

    @SuppressWarnings("unchecked")
    public ArraySet() {
        size = 0;
        items = (T[]) new Object[100];
    }

    @Override
    public Iterator<T> iterator() {
        return new SetIterator();
    }

    class SetIterator implements Iterator<T> {
        private int p;

        public SetIterator() {
            p = 0;
        }

        @Override
        public boolean hasNext() {
            return p < size;
        }

        @Override
        public T next() {
            return items[p++];
        }
    }
    public boolean contains(T x) {
        for (int i = 0; i < size; i++) {
            if (items[i].equals(x)) {
                return true;
            }
        }
        return false;
    }

    public void add(T x) {
        if (x == null) {
            throw new IllegalArgumentException("you can't add null to " +
                    "an ArraySet");
        } else if (!contains(x)) {
            items[size++] = x;
        }
    }

    public int size() {
        return size;
    }

    public static void main(String[] args) {
        ArraySet<String> s = new ArraySet<>();
//        s.add(null);
        s.add("horse");
        s.add("fish");
        s.add("house");
        s.add("fish");        
        System.out.println(s.contains("horse"));        
        System.out.println(s.size());

        for (String str : s) {
            System.out.println(str);
        }
    }

    /* Also to do:
    1. Make ArraySet implement the Iterable<T> interface.
    2. Implement a toString method.
    3. Implement an equals() method.
    */
}
