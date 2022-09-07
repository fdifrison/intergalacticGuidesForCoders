public class Main {

    public static void main(String[] args){

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
    }
}
