public class ArrayDeque<T> {
    private T[] items;
    private int nextFirst;
    private int nextLast;
    private int size;

    public ArrayDeque() {
        size = 0;
        items = (T[]) new Object[8];
        nextFirst = 0;
        nextLast = 1;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    private int addThenModulo(int a, int b, int modulo) {
        return Math.floorMod(a + b, modulo);
    }

    private int addThenModuloByLength(int a, int b) {
        return addThenModulo(a, b, items.length);
    }

    private void resize(int capacity) {
        T[] a = (T[]) new Object[capacity];
        int front = addThenModuloByLength(nextFirst, 1);
        int newNextFirst = 0;
        int newNextLast = 1;
        for (int i = 0; i < size; i++) {
            a[newNextLast] = items[front];
            newNextLast = addThenModulo(newNextLast, 1, capacity);
            front = addThenModuloByLength(front, 1);
        }
        items = a;
        nextFirst = newNextFirst;
        nextLast = newNextLast;
    }

    public void addFirst(T item) {
        if (size == items.length) {
            resize(items.length * 2);
        }
        size++;
        items[nextFirst] = item;
        nextFirst = addThenModuloByLength(nextFirst, -1);
    }

    public void addLast(T item) {
        if (size == items.length) {
            resize(items.length * 2);
        }
        size++;
        items[nextLast] = item;
        nextLast = addThenModuloByLength(nextLast, 1);
    }

    public void printDeque() {
        int first = addThenModuloByLength(nextFirst, 1);
        for (int i = 0; i < size; i++) {
            int index = addThenModuloByLength(first, i);
            System.out.print(items[index]);
            if (index == addThenModuloByLength(nextLast, -1)) {
                System.out.println();
            } else {
                System.out.print(" ");
            }
        }
    }

    private double usageRatio() {
        return (double) size / items.length;
    }

    public T removeFirst() {
        if (size == 0) {
            return null;
        } else {
            size--;
            nextFirst = addThenModuloByLength(nextFirst, 1);
            T item = items[nextFirst];
            items[nextFirst] = null;
            if (usageRatio() < 0.25 && items.length >= 4) {
                resize(items.length / 2);
            }
            return item;
        }
    }

    public T removeLast() {
        if (size == 0) {
            return null;
        } else {
            size--;
            nextLast = addThenModuloByLength(nextLast, -1);
            T item = items[nextLast];
            items[nextLast] = null;
            if (usageRatio() < 0.25 && items.length >= 4) {
                resize(items.length / 2);
            }
            return item;
        }
    }

    public T get(int index) {
        if (index >= size) {
            return null;
        }
        index = addThenModuloByLength(nextFirst + 1, index);
        return items[index];
    }

    private static void main(String[] args) {
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
        System.out.println("size: " + L.size());
        L.printDeque();
        L.resize(20);
        L.printDeque();
        for (int i = 0; i < 100; i++) {
            L.addLast(i);
        }
        L.printDeque();
        System.out.println("size: " + L.size());


        System.out.println("get test");
        for (int i = 0; i < 100; i++) {
            System.out.print(L.get(i) + " ");
        }
        System.out.println();

        System.out.println("remove last test");
        for (int i = 0; i < 100; i++) {
            L.removeLast();
        }
        L.printDeque();
        System.out.println("size: " + L.size());

        ArrayDeque<Integer> L2 = new ArrayDeque<>();
        L2.addFirst(0);
        L2.removeFirst();
        L2.addFirst(2);
        L2.addLast(3);
        L2.get(1);
        L2.addLast(4);
        L2.addFirst(5);
        L2.addLast(6);
        L2.addLast(7);
        L2.removeFirst();
        L2.addFirst(9);
        L2.removeFirst();
        L2.removeLast();
        L2.addFirst(12);
        L2.get(4);
    }
}
