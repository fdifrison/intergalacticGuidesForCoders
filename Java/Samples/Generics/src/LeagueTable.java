import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Collections;

public class LeagueTable<T extends Team> {

    private ArrayList<T> table = new ArrayList<>();

    public boolean addTeam(T team){
        if (table.contains(team)) {
            System.out.println(team.getName() + " already present");
            return false;
        } else {
            table.add(team);
            return true;
        }
    }

    public void printSortedTable() {
        Collections.sort(table);
        for (T i : table) {
            System.out.println(i.getName() + ": " + i.getPoints());
        }
    }

}
