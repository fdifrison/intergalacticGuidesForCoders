package org.simUDuck;

public class Main {
    public static void main(String[] args) {
        System.out.println("Welcome to the Duck Simulator");
        Duck rubberDuck = new RubberDuck();
        System.out.println("#############################");
        rubberDuck.display();
        rubberDuck.fly();
        rubberDuck.quack();
    }
}