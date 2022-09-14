public class Vehicle {
    private int numOfWheels;

    public Vehicle() {}

    public Vehicle(int numOfWheels) {
        this.numOfWheels = numOfWheels;
    }

    public int getNumOfWheels() {
        return numOfWheels;
    }

    public void setNumOfWheels(int numOfWheels) {
        this.numOfWheels = numOfWheels;
    }
}

class Car extends Vehicle {
    private  int numOfDoors;
    private int diesel;
    private int gasoline;

    public Car(int numOfDoors, int diesel, int gasoline) {
        super(4);
        this.numOfDoors = numOfDoors;
        this.diesel = diesel;
        this.gasoline = gasoline;
    }

    public int getNumOfDoors() {
        return numOfDoors;
    }

    public void setNumOfDoors(int numOfDoors) {
        this.numOfDoors = numOfDoors;
    }

    public int getDiesel() {
        return diesel;
    }

    public void setDiesel(int diesel) {
        this.diesel = diesel;
    }

    public int getGasoline() {
        return gasoline;
    }

    public void setGasoline(int gasoline) {
        this.gasoline = gasoline;
    }
}

class Ferrari extends Car {
    private String name;

    public Ferrari(int diesel, int gasoline, String name) {
        super(2, diesel, gasoline);
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}