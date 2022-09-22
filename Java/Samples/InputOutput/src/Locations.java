import java.io.*;
import java.util.*;

public class Locations implements Map<Integer, Location> {
    public static Map<Integer, Location> locations = new HashMap<>();

    /*public static void main(String[] args) throws IOException {
        FileWriter localFile = new FileWriter("locations.txt");
        for (Location location: locations.values()) {
            localFile.write(location.getLocationID() + ", " + location.getDescription() + "\n");
        }
        localFile.close();
    }*/

    public Locations() {

    }

    public static void main(String[] args) throws IOException {


    }

    public void writeData() throws IOException {
        try (FileWriter locationFile = new FileWriter("locations.txt");
             FileWriter directionsFile = new FileWriter("directions.txt")) {
            for (Location location : locations.values()) {
                locationFile.write(location.getLocationID() + ", " + location.getDescription() + "\n");
                for (String direction : location.getExists().keySet()) {
                    directionsFile.write(location.getLocationID() + "," + direction + "," + location.getExists().get(direction) + "\n");
                }
            }
        }
    }

    public void loadData() throws IOException {
        try (Scanner scanner = new Scanner(new FileReader("locations.txt"))) {
            scanner.useDelimiter(",");
            while (scanner.hasNextLine()) {
                int loc = scanner.nextInt();
                scanner.skip(scanner.delimiter());
                String description = scanner.nextLine();
                locations.put(loc, new Location(loc, description.strip()));
            }
        }

        // now read the exits but using buffer stream
        try (Scanner scanner = new Scanner(new BufferedReader(new FileReader("directions.txt")))) {
            scanner.useDelimiter(",");
            while (scanner.hasNextLine()) {
                int loc = scanner.nextInt();
                scanner.skip(scanner.delimiter());
                String direction = scanner.next().strip();
                scanner.skip(scanner.delimiter());
                int destination = Integer.parseInt(scanner.nextLine().strip());
                locations.get(loc).addExit(direction, destination);
            }
        } finally {

        }



      /*  locations.put(0, new Location(0, "You are sitting in front of a computer learning Java"));
        locations.put(1, new Location(1, "You are standing at the end of a road before a small brick building"));
        locations.put(2, new Location(2, "You are at the top of a hill"));
        locations.put(3, new Location(3, "You are inside a building, a well house for a small spring"));
        locations.put(4, new Location(4, "You are in a valley beside a stream"));
        locations.put(5, new Location(5, "You are in the forest"));

        // add the exits to the locations
        // see the Adventurer_Map.PNG
        locations.get(1).addExit("W", 2);
        locations.get(1).addExit("E", 3);
        locations.get(1).addExit("S", 4);
        locations.get(1).addExit("N", 5);

        locations.get(2).addExit("N", 5);

        locations.get(3).addExit("W", 1);

        locations.get(4).addExit("N", 1);
        locations.get(4).addExit("W", 2);

        locations.get(5).addExit("S", 1);
        locations.get(5).addExit("W", 2);*/
    }


    @Override
    public int size() {
        return locations.size();
    }

    @Override
    public boolean isEmpty() {
        return locations.isEmpty();
    }

    @Override
    public boolean containsKey(Object key) {
        return locations.containsKey(key);
    }

    @Override
    public boolean containsValue(Object value) {
        return locations.containsValue(value);
    }

    @Override
    public Location get(Object key) {
        return locations.get(key);
    }

    @Override
    public Location put(Integer key, Location value) {
        return locations.put(key, value);
    }

    @Override
    public Location remove(Object key) {
        return locations.remove(key);
    }

    @Override
    public void putAll(Map<? extends Integer, ? extends Location> m) {

    }

    @Override
    public void clear() {
        locations.clear();
    }

    @Override
    public Set<Integer> keySet() {
        return locations.keySet();
    }

    @Override
    public Collection<Location> values() {
        return locations.values();
    }

    @Override
    public Set<Entry<Integer, Location>> entrySet() {
        return locations.entrySet();
    }
}
