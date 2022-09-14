import java.util.Optional;

public class Dog extends Animal {
    private String breed;
    private int teeth;

    // removing the state "tail" and setting a default "true" values since all dogs have a tail
    public Dog(String name, int legs, String breed, int teeth) {
        super(name, legs, true);
        // extending the base constructor of the parent class
        this.breed = breed;
        this.teeth = teeth;
    }

    @Override
    public void talk(String name, String cry) {
        super.talk(name, cry);

    }
}
