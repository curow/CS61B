public class IntList {
    public int first;
    public IntList rest;

    public IntList(int f, IntList r) {
        first = f;
        rest = r;
    }

    public int size() {
        if (rest == null) {
            return 1;
        } else {
            return 1 + rest.size();
        }
    }

    public int iterativeSize() {
        IntList p = this;
        int total = 0;
        while (p != null) {
            total += 1;
            p = p.rest;
        }
        return total;
    }

    public int get(int i) {
        if (i == 0) {
            return first;
        } else {
            return rest.get(i - 1);
        }
    }

    public void addFirst(int item) {
        IntList p = new IntList(first, rest);
        first = item;
        rest = p;
    }

    public static IntList square(IntList L) {
        if (L == null) {
            return null;
        } else {
            IntList rest = square(L.rest);
            return new IntList(L.first * L.first, rest);
        }
    }

    public static IntList squareMutative(IntList L) {
        if (L == null) {
            return null;
        } else {
            L.first *= L.first;
            L.rest = squareMutative(L.rest);
            return L;
        }
    }

    public static void main(String[] args) {
        IntList L = new IntList(5, null);
        L = new IntList(10, L);
        L = new IntList(15, L);
        L.addFirst(77);
        System.out.println(L.first);
        System.out.println(L.size());
        System.out.println(L.iterativeSize());
        System.out.println(L.get(0));
        System.out.println(L.get(1));
        System.out.println(L.get(2));
    }
}