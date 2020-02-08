public class ArgsSum {
    public static void main(String[] args) {
        int total = 0;
        for (String s : args) {
            total += Integer.parseInt(s);
        }
        System.out.println(total);
    }
}