public class HelloNumbers {
    public static void main(String[] args) {
        int x = 0, total = 0;
        while (x < 10) {
            System.out.print(total + " ");
            x += 1;
            total += x;
        }
        System.out.println();
    }
}