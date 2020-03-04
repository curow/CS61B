public class RabinKarpAlgorithm {


    /**
     * This algorithm returns the starting index of the matching substring.
     * This method will return -1 if no matching substring is found,
     * or if the input is invalid.
     */
    public static int rabinKarp(String input, String pattern) {
        if (pattern.length() > input.length()) {
            return -1;
        }
        int size = pattern.length();
        RollingString rs = new RollingString(input.substring(0, size), size);
        RollingString patternRS = new RollingString(pattern, size);
        int patternHash = patternRS.hashCode();
        if (rs.hashCode() == patternHash) {
            return 0;
        }

        int end = size;
        while (end < input.length()) {
            rs.addChar(input.charAt(end));
            if (rs.hashCode() == patternHash) {
                break;
            }
            end++;
        }
        if (end < input.length()) {
            return end - size + 1;
        }
        return -1;
    }

}
