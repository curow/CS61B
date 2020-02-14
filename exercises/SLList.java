public class SLList {
    private static class IntNode {
        int item;
        IntNode next;
        IntNode(int x, IntNode nextIntNode) {
            item = x;
            next = nextIntNode;
        }
    }

    /* The first item (if it exists) is at sentinel.next,
    sentinel.item caches the size of SLList. */
    private IntNode sentinel;

    public SLList() {
        sentinel = new IntNode(0, null);
    }

    public SLList(int x) {
        sentinel = new IntNode(1, null);
        sentinel.next = new IntNode(x, null);
    }

    public void addFirst(int x) {
        sentinel.item++;
        sentinel.next = new IntNode(x, sentinel.next);
    }

    public int getFirst() {
        return sentinel.next.item;
    }
    
    public int size() {
        return sentinel.item;
    }

    public void addLast(int x) {
        sentinel.item++;
        IntNode p = sentinel;
        while (p.next != null) {
            p = p.next;
        }
        p.next = new IntNode(x, null);
    }

    private void insert(IntNode p, int item, int position) {
        if (position == 0 || p.next == null) {
            p.next = new IntNode(item, p.next);
        } else {
            insert(p.next, item, position - 1);
        }
    }

    public void insert(int item, int position) {
        sentinel.item++;
        if (sentinel.next == null || position == 0) {
            addFirst(item);
        } else {
            insert(sentinel.next, item, position - 1);
        }
    }

    public void printList() {
        IntNode p = sentinel.next;
        while (p != null) {
            System.out.print(p.item + " ");
            p = p.next;
        }
        System.out.println();
    }

    public void reverse() {
        if (sentinel.next == null || sentinel.next.next == null) {
            return;
        }
        IntNode tail = sentinel.next;
        IntNode currentNode = sentinel.next.next;
        tail.next = null;
        while (currentNode != null) {
            IntNode head = sentinel.next;
            IntNode nextNode = currentNode.next;

            sentinel.next = currentNode;
            currentNode.next = head;

            currentNode = nextNode;
        }
    }

    private IntNode reverse(IntNode L1, IntNode L2) {
        if (L2 == null) {
            return L1;
        } else {
            IntNode p = L2;
            L2 = L2.next;
            p.next = L1;
            return reverse(p, L2);
        }
    }

    public void reverseRecursive() {
        if (sentinel.next == null) {
            return;
        }
        IntNode L1 = sentinel.next;
        IntNode L2 = sentinel.next.next;
        L1.next = null;
        sentinel.next = reverse(L1, L2);

    }

    public static void main(String[] args) {
        SLList L = new SLList();
        L.addFirst(3);
        L.addFirst(5);
        L.addLast(32);
        System.out.println(L.getFirst());
        System.out.println(L.size());

        L.printList();
        L.insert(100, 0);
        L.insert(78, 1);
        L.insert(23, 5);
        L.insert(13, 234);
        L.printList();

        L.reverse();
        L.printList();

        L.reverseRecursive();
        L.printList();
    }
}
