public class Car {
    private boolean engine;
    private int cylinders;
    private String name;
    private int wheels;

    public Car(int cylinders, String name) {
        this.cylinders = cylinders;
        this.name = name;
        this.engine = true;
        this.wheels = 4;
    }

    public int getCylinders() {
        return cylinders;
    }

    public String getName() {
        return name;
    }

    public String startEngine() {
       return "The "+ this.getName() + "'s engine is starting";
    }

    public String accelerate() {
        return "The "+ this.getName() + " is accelerating";
    }

    public String brake() {
       return "The "+ this.getName() + " is braking";
    }

}
