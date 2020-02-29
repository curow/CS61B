import java.security.Key;
import java.util.Iterator;
import java.util.Set;

public class BSTMap<K extends Comparable<K>, V> implements Map61B<K, V> {
    private class Node {
        public K key;
        public V value;
        public Node left;
        public Node right;

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }

    private Node root;
    private int size;

    public BSTMap() {
        clear();
    }

    @Override
    public void clear() {
        root = null;
        size = 0;
    }

    private V get(Node p, K key) {
        if (p == null) {
            return null;
        }
        int cmp = key.compareTo(p.key);
        if (cmp == 0) {
            return p.value;
        } else if (cmp < 0) {
            return get(p.left, key);
        } else {
            return get(p.right, key);
        }
    }

    @Override
    public V get(K key) {
        return get(root, key);
    }

    @Override
    public boolean containsKey(K key) {
        V value = get(key);
        return value != null;
    }

    @Override
    public int size() {
        return size;
    }

    private Node put(Node p, K key, V value) {
        if (p == null) {
            size++;
            return new Node(key, value);
        } else if (key.compareTo(p.key) > 0) {
            p.right = put(p.right, key, value);
        } else {
            p.left = put(p.left, key, value);
        }
        return p;
    }

    @Override
    public void put(K key, V value) {
        if (!containsKey(key)) {
            root = put(root, key, value);
        }
    }

    private void printInOrder(Node p) {
        if (p != null) {
            printInOrder(p.left);
            System.out.println(p.key + " : " + p.value);
            printInOrder(p.right);
        }
    }

    public void printInOrder() {
        printInOrder(root);
    }

    @Override
    public V remove(K key) {
        throw new UnsupportedOperationException("remove(key) is not supported");
    }

    @Override
    public V remove(K key, V value) {
        throw new UnsupportedOperationException("remove(key, value) is not " +
                "supported");
    }

    private class KeyIterator implements Iterator<K> {
        private K[] keys;
        private int current;

        private int insertInOrder(Node p, int start) {
            if (p == null) {
                return start;
            } else {
                start = insertInOrder(p.left, start);
                keys[start] = p.key;
                return insertInOrder(p.right, start + 1);
            }
        }

        @SuppressWarnings("unchecked")
        public KeyIterator(Node root) {
            keys = (K[]) new Comparable[size];
            insertInOrder(root, 0);
            current = 0;
        }

        @Override
        public boolean hasNext() {
            return current < keys.length;
        }

        @Override
        public K next() {
            return keys[current++];
        }
    }

    @Override
    public Iterator<K> iterator() {
        return new KeyIterator(root);
    }

    @Override
    public Set<K> keySet() {
        throw new UnsupportedOperationException("keySet is not supported");
    }
}
