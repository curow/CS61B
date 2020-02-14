public class Array {
    public static int[] insert(int[] arr, int item, int position) {
        int[] result = new int[arr.length + 1];
        position = Math.min(arr.length, position);
        for (int i = 0; i < position; i++) {
            result[i] = arr[i];
        }
        result[position] = item;
        for (int i = position; i < arr.length; i++) {
            result[i + 1] = arr[i];
        }
        return result;
    }

    public static void reverse(int[] arr) {
        for (int i = 0; i < arr.length / 2; i++) {
            int j = arr.length - i - 1;
            int temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
        }
    }

    public static int[] replicate(int[] arr) {
        int size = 0;
        for (int item : arr) {
            size += item;
        }
        int[] result = new int[size];

        int n = 0;
        for (int item : arr) {
            for (int i = 0; i < item; i++) {
                result[n] = item;
                n++;
            }
        }
        return result;
    }
}
