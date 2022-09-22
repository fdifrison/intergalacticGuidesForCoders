import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
    //private static Map<Integer, Location> locations = new HashMap<Integer, Location>();
    private static Locations locations = new Locations();

    public static void main(String[] args) throws IOException {
        locations.loadData();
        Scanner scanner = new Scanner(System.in);

        int loc = 1;
        while (true) {
            System.out.println((locations.get(loc).getDescription()));
            if (loc == 0) break;

            Map<String, Integer> exits = locations.get(loc).getExists();
            System.out.println("Available exits are ");
            for (String exit: exits.keySet()) {
                System.out.print(exit + ", ");
            }
            System.out.println(); // empty line

            String direction = scanner.nextLine().toUpperCase();
            if (exits.containsKey(direction)) {
                loc = exits.get(direction);
            } else {
                System.out.println("You cannot go in that direction");
            }

        }

    }
}