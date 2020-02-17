public class Palindrome {

    public Deque<Character> wordToDeque(String word) {
        Deque<Character> res = new LinkedListDeque<>();
        for (char ch : word.toCharArray()) {
            res.addLast(ch);
        }
        return res;
    }

    private boolean isPalindrome(Deque<Character> word,
                                 CharacterComparator cc) {
        if (word.isEmpty() || word.size() == 1) {
            return true;
        } else {
            char first = word.removeFirst();
            char last = word.removeLast();
            return cc.equalChars(first, last) && isPalindrome(word, cc);
        }
    }

    public boolean isPalindrome(String word, CharacterComparator cc) {
        Deque<Character> wordDeque = wordToDeque(word);
        return isPalindrome(wordDeque, cc);
    }

    public boolean isPalindrome(String word) {
        return isPalindrome(word, (x, y) -> (x == y));
    }
}
