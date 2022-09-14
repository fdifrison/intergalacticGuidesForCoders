import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        int iteration = 1;
        int sum = 0;
        Scanner myScanner = new Scanner(System.in);
        System.out.println("Enter number #" + iteration + ":");
        while (iteration < 4) {
            boolean isInt = myScanner.hasNextInt();
            if (isInt) {
                sum += myScanner.nextInt();
                iteration++;
                System.out.println("Enter number #" + iteration + ":");
            } else {
                System.out.println("Not a number, try again.");
                myScanner.nextLine();
            }

            //myScanner.nextLine();
        }
        System.out.println("The sum of the number is: " + sum);
        myScanner.close();


    }


}