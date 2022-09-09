public class Main {
    public static void main(String[] args) {

        boolean human = false;
        boolean negative = !human;
        if (human) {
            System.out.println("It is an Alien!");
            System.out.println(negative);

        }

        // ternary operators
        boolean ternary = (1 > 2) ? true : false;
        System.out.println("is 1 > 2 ? " + ternary);

        // if-then-else

        int test = 101;
        if (test < 100) {
            System.out.println("the test is < 100");
        } else if (test > 100) {
            System.out.println("the test is > 100");
        } else {
            System.out.println("something different");
        }

        // logical operators

        double var1 = 20.00d;
        double var2 = 80.00d;

        double var3 = (var1 + var2) * 100.00d;

        double remainder = var3 % 40.00d;
        System.out.println(remainder);

        boolean isThereNoRemainder = remainder == 0.0d ? true : false;
        System.out.println("is the remainder 0? " + isThereNoRemainder);

        if (!isThereNoRemainder) {
            System.out.println("Got some remainder");
        }


    }
}