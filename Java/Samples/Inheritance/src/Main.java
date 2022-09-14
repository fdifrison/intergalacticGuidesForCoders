
public class Main {

    public static void main(String[] args) {

        Vehicle vehicle = new Vehicle(4);
        Car car = new Car(4,1,0);
        Ferrari enzo = new Ferrari(1, 0, "Enzo");

        vehicle.getNumOfWheels();
        car.getNumOfWheels();
        enzo.getName();

    }




}

