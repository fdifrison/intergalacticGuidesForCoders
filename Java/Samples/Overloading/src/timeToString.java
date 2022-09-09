public class timeToString {

    public static void main(String[] args) {
        System.out.println(getDurationString(19780));
    }

    public static String getDurationString(int min, int sec) {
        if (min < 0 || (sec < 0 || sec > 59)) return "Invalid value";

        if ((min / 60) < 1) {
            if (min < 10) {
                return "00h 0" + min + "m " + sec + "s";
            } else {
                return "00h " + min + "m " + sec + "s";
            }
        } else {
            if ((min % 60)  < 10) {
                return "0" + (min / 60) + "h 0" + (min % 60) + "m " + sec + "s";
            } else {
                return "0" + (min / 60) + "h " + (min % 60) + "m " + sec + "s";
            }

        }

    }

    public static String getDurationString(int sec) {
        if (sec < 0) return "Invalid value";
        int min = sec / 60;
        int remainder = sec % 60;
        return getDurationString(min, remainder);

    }
    


}
