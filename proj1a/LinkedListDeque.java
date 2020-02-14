public class LinkedListDeque<T> {
    private class ListNode {
        T item;
        ListNode prev;
        ListNode next;

        ListNode(ListNode prev, ListNode next) {
            this.prev = prev;
            this.next = next;
        }

        ListNode(T item, ListNode prev, ListNode next) {
            this.item = item;
            this.prev = prev;
            this.next = next;
        }
    }

    private int size;
    private ListNode sentinel;

    public LinkedListDeque() {
        size = 0;
        sentinel = new ListNode(null, null);
        sentinel.next = sentinel;
        sentinel.prev = sentinel;
    }

    public void addFirst(T item) {
        size++;
        ListNode prevFirst = sentinel.next;
        sentinel.next = new ListNode(item, sentinel, prevFirst);
        prevFirst.prev = sentinel.next;
    }

    public void addLast(T item) {
        size++;
        ListNode prevLast = sentinel.prev;
        sentinel.prev = new ListNode(item, prevLast, sentinel);
        prevLast.next = sentinel.prev;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public void printDeque() {
        ListNode p = sentinel.next;
        while (p != sentinel) {
            System.out.print(p.item);
            if (p != sentinel.prev) {
                System.out.print(" ");
            } else {
                System.out.println();
            }
            p = p.next;
        }
    }

    public T removeFirst() {
        if (isEmpty()) {
            return null;
        } else {
            size--;
            T item = sentinel.next.item;
            sentinel.next = sentinel.next.next;
            sentinel.next.prev = sentinel;
            return item;
        }
    }

    public T removeLast() {
        if (isEmpty()) {
            return null;
        } else {
            size--;
            T item = sentinel.prev.item;
            sentinel.prev = sentinel.prev.prev;
            sentinel.prev.next = sentinel;
            return item;
        }
    }

    public T get(int index) {
        if (index >= size) {
            return null;
        } else {
            ListNode p = sentinel;
            for (int i = 0; i <= index; i++) {
                p = p.next;
            }
            return p.item;
        }
    }

    private T getRecursive(ListNode p, int index) {
        if (p == null) {
            return null;
        } else if (index == 0) {
            return p.item;
        } else {
            return getRecursive(p.next, index - 1);
        }
    }

    public T getRecursive(int index) {
        return getRecursive(sentinel.next, index);
    }
}
