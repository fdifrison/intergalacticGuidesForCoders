import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        int iteration = 0;
        Scanner myScanner = new Scanner(System.in);
        boolean isInt = myScanner.hasNextInt();
        System.out.println("Enter number #" + iteration);
        while (iteration<10) {
            iteration = readingUserInput(myScanner, iteration);
        }
    }

    public static int readingUserInput(Scanner scanner, int iteration){

        boolean isInt = scanner.hasNextInt();
        if (isInt) {
            System.out.println("Enter number #" + iteration);
            scanner.nextLine();
            return iteration++;
        }
        return iteration;
    }
}