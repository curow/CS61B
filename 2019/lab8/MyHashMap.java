import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Set;

public class MyHashMap<K, V> implements Map61B<K, V>{

    private int size;
    private int initialSize = 16;
    private double loadFactor = 0.75;
    private HashSet<K> keys;

    private class Node {
        public K key;
        public V value;

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }

    private LinkedList<Node>[] buckets;

    @SuppressWarnings("unchecked")
    private void setUp(int initialSize, double loadFactor) {
        buckets = new LinkedList[initialSize];
        keys = new HashSet<>();
        this.size = 0;
        this.initialSize = initialSize;
        this.loadFactor = loadFactor;
    }

    public MyHashMap(int initialSize, double loadFactor) {
        setUp(initialSize, loadFactor);
    }

    public MyHashMap(int initialSize) {
        setUp(initialSize, this.loadFactor);
    }

    public MyHashMap() {
        setUp(initialSize, loadFactor);
    }

    @Override
    public void clear() {
        setUp(initialSize, loadFactor);
    }

    private int getIndex(K key) {
        return getIndex(key, buckets.length);
    }

    private int getIndex(K key, int size) {
        return Math.floorMod(key.hashCode(), size);
    }

    @Override
    public boolean containsKey(K key) {
        V value = get(key);
        return value != null;
    }

    @Override
    public V get(K key) {
        int index = getIndex(key);
        LinkedList<Node> bucket = buckets[index];
        if (bucket != null) {
            for (Node node : bucket) {
                if (node.key.equals(key)) {
                    return node.value;
                }
            }
        }
        return null;
    }

    @Override
    public int size() {
        return size;
    }

    private double getLoadFactor() {
        return ((double ) size) / buckets.length;
    }

    @SuppressWarnings("unchecked")
    private void resize() {
        LinkedList<Node>[] newBuckets = new LinkedList[buckets.length * 2];
        for (LinkedList<Node> bucket : buckets) {
            if (bucket != null) {
                for (Node node : bucket) {
                    int index = getIndex(node.key, newBuckets.length);
                    if (newBuckets[index] == null) {
                        newBuckets[index] = new LinkedList<>();
                    }
                    newBuckets[index].add(node);
                }
            }
        }
        buckets = newBuckets;
    }

    @Override
    public void put(K key, V value) {
        if (getLoadFactor() > loadFactor) {
            resize();
        }
        if (!containsKey(key)) {
            size++;
            Node node = new Node(key, value);
            int index = getIndex(key);
            if (buckets[index] == null) {
                buckets[index] = new LinkedList<Node>();
            }
            buckets[index].add(node);
            keys.add(key);
        } else {
            int index = getIndex(key);
            LinkedList<Node> bucket = buckets[index];
            for (Node node : bucket) {
                if (node.key == key) {
                    node.value = value;
                    return;
                }
            }
        }
    }

    @Override
    public V remove(K key) {
        throw new UnsupportedOperationException("remove not supported");
    }

    @Override
    public V remove(K key, V value) {
        throw new UnsupportedOperationException("remove not supported");
    }

    @Override
    public Set<K> keySet() {
        return keys;
    }

    @Override
    public Iterator<K> iterator() {
        return keys.iterator();
    }
}
