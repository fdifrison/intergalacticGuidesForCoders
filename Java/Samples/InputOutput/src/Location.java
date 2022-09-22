import java.util.HashMap;
import java.util.Map;

public class Location {
    // the variable are protected from outside by the final access modifier
    private final int locationID;
    private final String description;
    private final Map<String, Integer> exists;

    public Location(int locationID, String description) {
        this.locationID = locationID;
        this.description = description;
        exists = new HashMap<String, Integer>();
    }

    public void addExit(String direction, int location) {
        exists.put(direction, location);
    }

    public int getLocationID() {
        return locationID;
    }

    public String getDescription() {
        return description;
    }

    public Map<String, Integer> getExists() {
        // to avoid access to the hash map from outside the class
        // we return a copy of it
        return new HashMap<String, Integer>(exists);
    }
}
