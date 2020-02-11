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

    public static void main(String[] args) {
        SLList L = new SLList();
        L.addFirst(3);
        L.addFirst(5);
        L.addLast(32);
        System.out.println(L.getFirst());
        System.out.println(L.size());
    }
}