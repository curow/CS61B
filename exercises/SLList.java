public class SLList {
    private static class IntNode {
        int item;
        IntNode next;
        IntNode(int x, IntNode nextIntNode) {
            item = x;
            next = nextIntNode;
        }
    }

    private IntNode first;
    private int size;

    public SLList(int x) {
        first = new IntNode(x, null);
        size = 1;
    }

    public void addFirst(int x) {
        first = new IntNode(x, first);
        size++;
    }

    public int getFirst() {
        return first.item;
    }
    
    public int size() {
        return size;
    }

    public void addLast(int x) {
        IntNode p = first;
        while (p.next != null) {
            p = p.next;
        }
        p.next = new IntNode(x, null);
        size++;
    }

    public static void main(String[] args) {
        SLList L = new SLList(10);
        L.addFirst(3);
        L.addFirst(5);
        System.out.println(L.getFirst());
        System.out.println(L.sizeIterative());
        System.out.println(L.size());
    }
}