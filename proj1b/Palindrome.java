public class Palindrome {
    public Deque<Character> wordToDeque(String word) {
        Deque<Character> res = new LinkedListDeque<>();
        for (char ch : word.toCharArray()) {
            res.addLast(ch);
        }
        return res;
    }
}
