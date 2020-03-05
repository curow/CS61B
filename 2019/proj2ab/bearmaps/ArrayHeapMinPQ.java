package bearmaps;

import edu.princeton.cs.algs4.BinaryDump;

import java.util.*;

public class ArrayHeapMinPQ<T> implements ExtrinsicMinPQ<T> {
    private int size;
    private ArrayList<PriorityNode> heap;
    private HashMap<T, Integer> location;

    private class PriorityNode implements Comparable<PriorityNode> {
        private T item;
        private double priority;

        public PriorityNode(T item, double priority) {
            this.item = item;
            this.priority = priority;
        }

        @Override
        public int compareTo(PriorityNode o) {
            if (o == null) {
                return -1;
            }
            return Double.compare(this.getPriority(), o.getPriority());
        }

        @Override
        @SuppressWarnings("unchecked")
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            } else if (o == null || this.getClass() != o.getClass()) {
                return false;
            } else {
                return ((PriorityNode) o).getItem().equals(getItem());
            }
        }

        @Override
        public int hashCode() {
            return item.hashCode();
        }

        public T getItem() {
            return item;
        }

        public double getPriority() {
            return priority;
        }

        public void setPriority(double priority) {
            this.priority = priority;
        }
    }

    public ArrayHeapMinPQ() {
        size = 0;
        heap = new ArrayList<>();
        heap.add(0, null);
        location = new HashMap<>();
    }

    @Override
    public void add(T item, double priority) {
        PriorityNode node = new PriorityNode(item, priority);
        if (contains(item)) {
            throw new IllegalArgumentException("item already in PQ");
        }
        add(node);
    }

    @Override
    public boolean contains(T item) {
        return location.get(item) != null;
    }

    @Override
    public T getSmallest() {
        if (size == 0) {
            throw new NoSuchElementException("PQ is empty");
        }
        return heap.get(1).getItem();
    }

    @Override
    public T removeSmallest() {
        if (size == 0) {
            throw new NoSuchElementException("PQ is empty");
        }
        exchange(1, size);
        T item = remove();
        sink(1);
        return item;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void changePriority(T item, double priority) {
        if (!contains(item)) {
            throw new IllegalArgumentException("item not in PQ");
        }
        int index = location.get(item);
        PriorityNode node = heap.get(index);
        double oldPriority = node.getPriority();
        node.setPriority(priority);
        if (priority < oldPriority) {
            swim(index);
        } else if (priority > oldPriority){
            sink(index);
        }
    }

    private void add(PriorityNode node) {
        assert node != null;
        ++size;
        heap.add(node);
        location.put(node.getItem(), size);
        swim(size);
    }

    private T remove() {
        PriorityNode node = heap.get(size);
        T item = node.getItem();
        heap.remove(size);
        location.remove(item);
        --size;
        return item;
    }

    private void set(PriorityNode node, int index) {
        assert node != null && index >= 1 && index <= size;
        heap.set(index, node);
        location.put(node.getItem(), index);
    }

    private void exchange(int a, int b) {
        assert a >= 1 && a <= size && b >= 1 && b <= size;
        PriorityNode nodeA = heap.get(a);
        PriorityNode nodeB = heap.get(b);
        set(nodeA, b);
        set(nodeB, a);
    }

    private boolean greater(int a, int b) {
        assert a >= 1 && a <= size && b >= 1 && b <= size;
        PriorityNode nodeA = heap.get(a);
        PriorityNode nodeB = heap.get(b);
        return nodeA.compareTo(nodeB) > 0;
    }

    private void swim(int index) {
        int parentIndex = index / 2;
        if (index > 1 && greater(parentIndex, index)) {
                exchange(index, parentIndex);
                swim(parentIndex);
        }
    }

    /**
     * @source https://algs4.cs.princeton.edu/24pq/MinPQ.java.html
     */
    private void sink(int index) {
        int childIndex = 2 * index;
        if (childIndex <= size) {
            if (childIndex < size && greater(childIndex, childIndex + 1)) {
                ++childIndex;
            }
            if (greater(index, childIndex)) {
                exchange(index, childIndex);
                sink(childIndex);
            }
        }
    }
}
