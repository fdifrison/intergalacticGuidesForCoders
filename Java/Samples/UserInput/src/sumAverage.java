import java.util.Scanner;

public class sumAverage {
    public static void main(String[] args) {
        inputThenPrintSumAndAverage();
    }

    public static void inputThenPrintSumAndAverage() {
        Scanner scanner = new Scanner(System.in);
        int count = 0;
        int sum = 0;

        while (true) {
            boolean isInt = scanner.hasNextInt();
            if (isInt) {
                int num = scanner.nextInt();
                sum+= num;
                count++;
            } else {
                long avg = (count > 0) ? (sum / count) : 0;
                System.out.println("SUM = " + sum +
                        " AVG = " + avg);
            }
            scanner.nextLine();
        }
    }
}
