import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
//        divideNoExceptionHandling();
        divideEAFP(10,0);
    }

    private static int divideLBYL(int x, int y) {
        // the exception is hadled before it can happen
        if (y != 0) {
            return x / y;
        } else return 0;
    }

    private static int divideEAFP(int x, int y) {
        try {
            return x / y;
        } catch (ArithmeticException e) {
            return 0;
        }
    }

    private static int divideNoExceptionHandling() {
        Scanner scanner = new Scanner(System.in);
        int x = scanner.nextInt();
        scanner.nextLine();
        int y = scanner.nextInt();

        return x / y;

    }

}