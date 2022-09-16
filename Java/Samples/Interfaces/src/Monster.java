import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

public class Monster implements  ISaveable{

    private String name;

    private int hitPoints;
    private int strength;

    public Monster(String name, int hitPoints, int strength) {
        this.name = name;

        this.hitPoints = hitPoints;
        this.strength = strength;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public int getHitPoints() {
        return hitPoints;
    }

    public void setHitPoints(int hitPoints) {
        this.hitPoints = hitPoints;
    }

    public int getStrength() {
        return strength;
    }

    public void setStrength(int strength) {
        this.strength = strength;
    }

    @Override
    public String toString() {
        return "Monster{" +
                "name='" + name + '\'' +
                ", hitPoints=" + hitPoints +
                ", strength=" + strength +
                '}';
    }

    @Override
    public List<String> write() {
        List<String> list = new ArrayList<String>();
        list.add(0, "name="+name);
        list.add(1, "hitPoints="+hitPoints);
        list.add(2, "strength="+strength);
        return list;
    }

    @Override
    public void read(List<String> myList) {
        if (myList!= null && myList.size()>0) {
            name = myList.get(0);
            hitPoints = Integer.parseInt(myList.get(1));
            strength = Integer.parseInt(myList.get(2));
        }
    }
}
