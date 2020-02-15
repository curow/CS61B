public class Sort {
    private static int findSmallest(String[] input, int start) {
        int smallest = start;
        for (int i = start + 1; i < input.length; i++) {
            if (input[smallest].compareTo(input[i]) > 0) {
                smallest = i;
            }
        }
        return smallest;
    }

    private static void swap(String[] input, int i, int j) {
        String temp = input[i];
        input[i] = input[j];
        input[j] = temp;
    }

    public static void sort(String[] input) {
        for (int i = 0; i < input.length; i++) {
            int j = findSmallest(input, i);
            if (j != i) {
                swap(input, i, j);
            }
        }
    }
}