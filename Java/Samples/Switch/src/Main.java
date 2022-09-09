public class Main {
    public static void main(String[] args) {
        testSwitch(10);

    }

    public static void testSwitch(int value) {
        switch (value) {
            case 1:
                System.out.println("You chose 1");
                break;

            case 2:
                System.out.println("You chose 2");
                break;

            default:
                System.out.println("Not 1 or 2 was chosen but " + value);
        }
    }

}