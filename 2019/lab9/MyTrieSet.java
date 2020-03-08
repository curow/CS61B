import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MyTrieSet implements TrieSet61B {
    private static class Node {
        public boolean isKey;
        private HashMap<Character, Node> next;
        public Node() {
            isKey = false;
            next = new HashMap<>();
        }
    }

    private Node root;

    public MyTrieSet() {
        root = new Node();
    }

    @Override
    public void clear() {
        root = new Node();
    }

    @Override
    public boolean contains(String key) {
        Node p = root;
        boolean contains = true;
        for (Character ch : key.toCharArray()) {
            if (p.next.containsKey(ch)) {
                p = p.next.get(ch);
            } else {
                contains = false;
                break;
            }
        }
        return contains && p.isKey;
    }

    @Override
    public void add(String key) {
        if (!contains(key)) {
            Node p = root;
            for (Character ch : key.toCharArray()) {
                if (p.next.containsKey(ch)) {
                    p = p.next.get(ch);
                } else {
                    Node node = new Node();
                    p.next.put(ch, node);
                    p = node;
                }
            }
            p.isKey = true;
        }
    }

    private void keyTraverse(Node p, String prefix, List<String> lst) {
        if (p != null) {
            if (p.isKey) {
                lst.add(prefix);
            }
            for (Character ch : p.next.keySet()) {
                keyTraverse(p.next.get(ch), prefix + ch, lst);
            }
        }
    }

    @Override
    public List<String> keysWithPrefix(String prefix) {
        Node p = root;
        List<String> lst = new ArrayList<>();
        boolean isEmpty = false;
        for (Character ch : prefix.toCharArray()) {
            if (p.next.containsKey(ch)) {
                p = p.next.get(ch);
            } else {
                isEmpty = true;
                break;
            }
        }
        if (isEmpty) {
            return lst;
        }
        keyTraverse(p, prefix, lst);
        return lst;
    }

    @Override
    public String longestPrefixOf(String key) {
        Node p = root;
        StringBuilder longestPrefix = new StringBuilder();
        for (Character ch : key.toCharArray()) {
            if (p.next.containsKey(ch)) {
                p = p.next.get(ch);
                longestPrefix.append(ch);
            } else {
                break;
            }
        }
        return longestPrefix.toString();
    }
}
