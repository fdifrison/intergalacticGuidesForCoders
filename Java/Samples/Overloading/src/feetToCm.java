public class feetToCm {
    public static void main(String[] args) {
        System.out.println(calcFeetToCM(11));
    }

    public static double calcFeetToCM(double feet, double inch) {
        if (feet < 0 || (inch < 0 || inch > 12)) return -1;
        return feet * 12 * 2.54 + inch * 2.54;
    }

    public static double calcFeetToCM(double inch) {
        if (inch < 0) return -1;
        double feet = (int) (inch / 12);
        double remainder = inch % 12;
        return calcFeetToCM(feet, remainder);
    }
}
