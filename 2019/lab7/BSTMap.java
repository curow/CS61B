import javax.swing.*;
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

    private void swap(Node p, Node q) {
        K key = p.key;
        V value = p.value;
        p.key = q.key;
        p.value = q.value;
        q.key = key;
        q.value = value;
    }

    private Node remove(Node p, K key) {
        if (p == null) {
            return null;
        }
        int cmp = key.compareTo(p.key);
        if (cmp < 0) {
            p.left = remove(p.left, key);
        } else if (cmp > 0) {
            p.right = remove(p.right, key);
        } else {
            size--;
            if (p.left == null || p.right == null) {
                p = p.left == null ? p.right : p.left;
            } else {
                Node rightestNodeInLeft = p.left;
                while (rightestNodeInLeft.right != null) {
                    rightestNodeInLeft = rightestNodeInLeft.right;
                }
                swap(p, rightestNodeInLeft);
                p.left = remove(p.left, key);
            }
        }
        return p;
    }

    @Override
    public V remove(K key) {
        V value = get(key);
        if (value != null) {
            root = remove(root, key);
        }
        return value;
    }

    @Override
    public V remove(K key, V value) {
        V realValue = get(key);
        if (realValue == value) {
            root = remove(root, key);
            return value;
        }
        return null;
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

    private int addKeys(Node p, K[] keys, int start) {
        if (p != null) {
            keys[start++] = p.key;
            start = addKeys(p.left, keys, start);
            start = addKeys(p.right, keys, start);
        }
        return start;
    }

    @Override
    @SuppressWarnings("unchecked")
    public Set<K> keySet() {
        K[] keys = (K[]) new Comparable[size];
        addKeys(root, keys, 0);
        return Set.of(keys);
    }
}
