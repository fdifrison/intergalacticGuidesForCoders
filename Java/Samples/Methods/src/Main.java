public class Main {
    public static void main(String[] args) {

        System.out.println("task1:");
        saySomething();

        System.out.println("\ntask2:");
        double yourWeight = 87.0d;
        double inPound = kgToPound(yourWeight);
        System.out.println(inPound);

        System.out.println("\ntask3:");
        String mySelf = "Giovanni";
        myScore(mySelf, 1500);
        myScore(mySelf, 900);
        myScore(mySelf, 400);
        myScore(mySelf, 50);
    }

    public static void saySomething() {
        System.out.println("A Method has been called!");
    }

    public static double kgToPound(double kilos) {
        double conversion = 2.205d;
        return kilos * conversion;
    }

    // exercise
    public static void myScore(String name, int score) {
        System.out.println(name +
                " managed to get into position " +
                calcHighScore(score));
    }

    public static int calcHighScore(int score) {

        int position = 4;

        if (score >= 1000) {
            position = 1;
        } else if (score >= 500) {
            position = 2;
        } else if (score > 100) {
            position = 3;
        }

        return position;
    }

}