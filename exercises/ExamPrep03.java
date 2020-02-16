public class ExamPrep03 {
    public static int[] flatten(int[][] x) {
        int totalLength = 0;
        for (int[] y : x) {
            totalLength += y.length;
        }
        int[] result = new int[totalLength];
        int index = 0;
        for (int[] y : x) {
            for (int z : y) {
                result[index++] = z;
            }
        }
        return result;
    }
}