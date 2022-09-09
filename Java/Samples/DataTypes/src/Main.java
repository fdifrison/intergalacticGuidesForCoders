public class Main {

    public static void main(String[] args){

        // integers

        int myMinInt = Integer.MIN_VALUE;
        int myMaxInt = Integer.MAX_VALUE;
        System.out.println(myMinInt);
        System.out.println(myMinInt - 1);
        System.out.println(myMaxInt);
        System.out.println(myMaxInt + 1);

        byte myMinByte = Byte.MIN_VALUE;
        byte myMaxByte = Byte.MAX_VALUE;
        System.out.println(myMinByte);
        System.out.println(myMaxByte);

        short myMinShort = Short.MIN_VALUE;
        short myMaxShort = Short.MAX_VALUE;
        System.out.println(myMinShort);
        System.out.println(myMaxShort);

        long val = 100_000_000_000L;
        System.out.println(val);

        long myMinLong = Long.MIN_VALUE;
        long myMaxLong = Long.MAX_VALUE;
        System.out.println(myMinLong);
        System.out.println(myMaxLong);

        byte useCasting = (byte)(myMinByte/2);
        System.out.println(useCasting);

        
        // float and double

        float myMinFloat = Float.MIN_VALUE;
        float myMaxFloat = Float.MAX_VALUE;
        System.out.println(myMinFloat);
        System.out.println(myMaxFloat);
        
        double myMaxDouble = Double.MAX_VALUE;
        double myMinDouble = Double.MIN_VALUE;
        System.out.println(myMinDouble);
        System.out.println(myMaxDouble);

        // char and strings

        char myChar = 'D';
        char myUniChar = '\u0044';
        System.out.println(myChar);
        System.out.println(myUniChar);

        String myString = "This is  a string";
        System.out.println(myString);
        String autoCasting = myString + '_' + 10;
        System.out.println(autoCasting);

        // boolean

        boolean myTrue = true;
        boolean myFalse = false;
        System.out.println(myTrue);
        System.out.println(myFalse);

    }
}
