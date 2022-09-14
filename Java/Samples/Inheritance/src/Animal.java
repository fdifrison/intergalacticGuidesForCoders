import java.util.Optional;

public class Animal {

    private String name;
    private int legs;
    private boolean tail;

    public Animal(String name, int legs, boolean tail) {
        this.name = name;
        this.legs = legs;
        this.tail = tail;
    }

    public String getName() {
        return name;
    }

    public int getLegs() {
        return legs;
    }

    public boolean isTail() {
        return tail;
    }

    public void talk(String name, String cry) {
        System.out.println(name + " says: " + cry);
    }
}