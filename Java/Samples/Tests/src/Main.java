public class Main {
    public static void main(String[] args) {
        //isPalindrome(-121);
        //sumFirstAndLastDigit(234);
        //getEvenDigitSum(123456789);
        //hasSameLastDigit(9, 99, 19);
        //getGreatestCommonDivisor(12, 30);
    }



    public static boolean isPalindrome (int number) {
        String str = Integer.toString(number).replace("-", "");
        StringBuilder newString = new StringBuilder();
        for (int i=0; i<str.length(); i++)
        {
            newString.insert(0, str.charAt(i)); //adds each character in front of the existing string
        }

        int newNumber = Integer.parseInt(newString.toString());
        System.out.println(newNumber);
        if (number<0) number*=-1;
        return newNumber == number;
    }

    public static int sumFirstAndLastDigit (int number) {
        if (number < 0) return -1;
        if (number == 0) return 0;
        if (number < 10) return number+= number;
        int sum = 0;
        String str = Integer.toString(number);
        sum += Integer.parseInt(String.valueOf(str.charAt(0)));
        sum += Integer.parseInt(String.valueOf(str.charAt(str.length()-1)));
        System.out.println(sum);
        return sum;
    }

    public static int getEvenDigitSum (int number) {
        if (number <0) return -1;
        if (number <100) return number/10;
        String str = Integer.toString(number);
        int sum = 0;
        for (int i = 0; i< str.length(); i++) {
            int num = Integer.parseInt(String.valueOf(str.charAt(i)));
            if (num%2 == 0) sum += num;
        }
        //System.out.println(sum);
        return sum;
    }

    public static boolean hasSharedDigit (int var1, int var2) {
        if ((var1 < 10 || var1>99) || (var2 < 10 || var2>99)) return false;
        String str1 = Integer.toString(var1);
        String str2 = Integer.toString(var2);
        for (int i =0; i < str1.length(); i++) {
            String check = Character.toString(str1.charAt(i));
            if (str2.contains(check)) return true;
        }
        return false;
    }

    public static boolean hasSameLastDigit (int var1, int var2, int var3) {
        if (!(isValid(var1) && isValid(var2) && isValid(var3))) return false;
        int last1 = var1%10;
        int last2 = var2%10;
        int last3 = var3%10;

        return (last1 == last2) || (last1 == last3) || (last2 == last3);
    }

    public static boolean isValid(int number) {
        return number >= 10 && number <= 1000;
    }

    public static int getGreatestCommonDivisor( int first, int second) {
        if (first <10 || second<10) return -1;
        int minor = (first >= second) ? second : first;
        for (int i = minor; i>=0; i--) {

            if (first%i==0 && second%i==0) {
                System.out.println(i);
                return i;}
        }
        return -1;
    }
}