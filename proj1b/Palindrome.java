public class Palindrome {

    public Deque<Character> wordToDeque(String word) {
        Deque<Character> res = new LinkedListDeque<>();
        for (char ch : word.toCharArray()) {
            res.addLast(ch);
        }
        return res;
    }

    private boolean isPalindrome(Deque<Character> word) {
        if (word.isEmpty() || word.size() == 1) {
            return true;
        } else {
            char first = word.removeFirst();
            char last = word.removeLast();
            return first == last && isPalindrome(word);
        }
    }

    public boolean isPalindrome(String word) {
        Deque<Character> wordDeque = wordToDeque(word);
        return isPalindrome(wordDeque);
    }
}
