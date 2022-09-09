public class sum3and5 {
    public static void main(String[] args) {
        int sumIfTrue = 0;
        int count = 0;
        for (int i = 1; i <= 1000; i++) {
            if (count == 3) {
                System.out.println("3 numbers found, their sum is equal to " + sumIfTrue);
                break;
            }
            if (i % 3 == 0 && i % 5 == 0) {
                System.out.println(i + " is divisible both by 3 and 5");
                sumIfTrue += i;
                count++;
            }
        }
    }
}
