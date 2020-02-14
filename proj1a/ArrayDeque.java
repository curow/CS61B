public class ArrayDeque<T> {
    private T[] items;
    private int nextFirst;
    private int nextLast;
    private int size;

    public ArrayDeque() {
        size = 0;
        items = (T[]) new Object[8];
        nextFirst = 3;
        nextLast = 4;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    private int addModuloLength(int a, int b) {
        return (a + b) % items.length;
    }

    public void addFirst(T item) {
        size++;
        items[nextFirst] = item;
        nextFirst = addModuloLength(nextFirst, -1);
    }

    public void addLast(T item) {
        size++;
        items[nextLast] = item;
        nextLast = addModuloLength(nextLast, 1);
    }

    public void printDeque() {
        int first = addModuloLength(nextFirst, 1);
        for (int i = 0; i < size; i++) {
            int index = addModuloLength(first, i);
            System.out.print(items[index]);
            if (index == addModuloLength(nextLast, -1)) {
                System.out.println();
            } else  {
                System.out.print(" ");
            }
        }
    }

    public T removeFirst() {
        if (size == 0) {
            return null;
        } else {
            size--;
            nextFirst = addModuloLength(nextFirst, 1);
            T item = items[nextFirst];
            items[nextFirst] = null;
            return item;
        }
    }

    public T removeLast() {
        if (size == 0) {
            return null;
        } else {
            size--;
            nextLast = addModuloLength(nextLast, -1);
            T item = items[nextLast];
            items[nextLast] = null;
            return item;
        }
    }

    public T get(int index) {
        int front = addModuloLength(nextFirst, 1);
        return items[front + index];
    }

    public static void main(String[] args) {
        ArrayDeque<Integer> L = new ArrayDeque<>();
        System.out.println(L.isEmpty());
        L.addFirst(4);
        L.addFirst(3);
        L.addFirst(2);
        L.addLast(5);
        L.addLast(6);
        System.out.println(L.isEmpty());
        System.out.println(L.size());
        L.printDeque();
        int head = L.removeFirst();
        System.out.println("head: " + head);
        int tail = L.removeLast();
        System.out.println("tail: " + tail);
        System.out.println(L.size());
        L.printDeque();
        for (int i = 0; i < L.size(); i++) {
            System.out.print(L.get(i) + " ");
        }
    }
}