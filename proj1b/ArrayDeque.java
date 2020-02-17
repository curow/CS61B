public class ArrayDeque<T> implements Deque<T>{
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

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private int moduloByLength(int index) {
        return Math.floorMod(index, items.length);
    }

    private void resize(int capacity) {
        T[] newItems = (T[]) new Object[capacity];
        int newNextFirst = 0;
        int newNextLast = 1;
        for (int i = 0; i < size; i++) {
            newItems[newNextLast] = this.get(i);
            newNextLast = Math.floorMod(newNextLast + 1, capacity);
        }
        items = newItems;
        nextFirst = newNextFirst;
        nextLast = newNextLast;
    }

    @Override
    public void addFirst(T item) {
        if (size == items.length) {
            resize(items.length * 2);
        }
        size++;
        items[nextFirst] = item;
        nextFirst = moduloByLength(nextFirst - 1);
    }

    @Override
    public void addLast(T item) {
        if (size == items.length) {
            resize(items.length * 2);
        }
        size++;
        items[nextLast] = item;
        nextLast = moduloByLength(nextLast + 1);
    }

    @Override
    public void printDeque() {
        for (int i = 0; i < size; i++) {
            System.out.print(this.get(i));
            if (i == size - 1) {
                System.out.println();
            } else {
                System.out.print(" ");
            }
        }
    }

    private double usageRatio() {
        return (double) size / items.length;
    }

    private void resizeWhenNecessary() {
        if (usageRatio() < 0.25 && items.length >= 4) {
            resize(items.length / 2);
        }
    }

    @Override
    public T removeFirst() {
        if (size == 0) {
            return null;
        } else {
            size--;
            nextFirst = moduloByLength(nextFirst + 1);
            T item = items[nextFirst];
            items[nextFirst] = null;
            return item;
        }
    }

    @Override
    public T removeLast() {
        if (size == 0) {
            return null;
        } else {
            size--;
            nextLast = moduloByLength(nextLast - 1);
            T item = items[nextLast];
            items[nextLast] = null;
            resizeWhenNecessary();
            return item;
        }
    }

    @Override
    public T get(int index) {
        if (index >= size) {
            return null;
        }
        index = moduloByLength(nextFirst + 1 + index);
        return items[index];
    }
}
