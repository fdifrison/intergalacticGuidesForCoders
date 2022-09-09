import java.text.MessageFormat;

public class Main {
    public static void main(String[] args) {
        countFirst3PrimeInRange(10, 15);
    }

    public static boolean isPrime(int n) {
        if (n == 1) return false;
        for (int i = 2; i <= n / 2; i++) {
            if (n % i == 0) return false;
        }
        return true;
    }

    public static void countFirst3PrimeInRange(int from, int to) {
        if (from >= to) System.out.println("from must be < then to!");
        int numOfPrimeFound = 0;
        for (int i = from; i <= to; i++) {
            if (isPrime(i)) {
                System.out.println(MessageFormat.format(
                        "Prime number found in range {0}-{1} is {2}",
                        from, to, i));
                numOfPrimeFound++;

            }
            if (numOfPrimeFound == 3) {
                System.out.println("Done!");
                break;
            }
        }
        System.out.println(MessageFormat.format(
                "The prime number found in range {0}-{1} are {2}",
                from, to, numOfPrimeFound));

    }
}