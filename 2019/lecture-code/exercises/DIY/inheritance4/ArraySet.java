import java.util.*;

public class ArraySet<T> implements Iterable<T> {
    private int size;
    private T[] items;

    @SuppressWarnings("unchecked")
    public ArraySet() {
        size = 0;
        items = (T[]) new Object[100];
    }

    @Override
    @SuppressWarnings("unchecked")
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ArraySet<T> arraySet = (ArraySet<T>) o;
        for (int i = 0; i < arraySet.size; i++) {
            if (!contains(arraySet.items[i])) {
                return false;
            }
        }
        return true;
    }

    // @Override
    // public String toString() {
    //     StringBuilder itemString = new StringBuilder();
    //     for (int i = 0; i < size - 1; i++) {
    //         itemString.append(items[i].toString());
    //         itemString.append(", ");
    //     }
    //     itemString.append(items[size - 1].toString());
    //     return "ArraySet{" +
    //             "size=" + size +
    //             ", items=" + itemString +
    //             '}';
    // }


    @Override
    public String toString() {
        List<String> listOfItems = new ArrayList<>();
        for (T x : this) {
            listOfItems.add(x.toString());
        }
        String stringOfItems = String.join(", ", listOfItems);
        return "ArraySet{" +
                "size=" + size +
                ", items=" + stringOfItems +
                '}';
    }

    public static <T> ArraySet<T> of(T... args) {
        ArraySet<T> result = new ArraySet<>();
        for (T x : args) {
            result.add(x);
        }
        return result;
    }

    @Override
    public Iterator<T> iterator() {
        return new SetIterator();
    }

    private class SetIterator implements Iterator<T> {
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
        s.add("horse");
        s.add("fish");
        s.add("house");
        s.add("fish");        
        System.out.println(s.contains("horse"));        
        System.out.println(s.size());

        for (String str : s) {
            System.out.println(str);
        }

        System.out.println(s);

        ArraySet<String> s2 = new ArraySet<>();
        s2.add("fish");
        s2.add("horse");
        s2.add("house");
        // s2.add("cat");
        System.out.println(s.equals(s2));

        ArraySet<Integer> s3 = ArraySet.of(1, 2, 3, 4);
        System.out.println(s3);
    }

    /* Also to do:
    1. Make ArraySet implement the Iterable<T> interface.
    2. Implement a toString method.
    3. Implement an equals() method.
    */
}
