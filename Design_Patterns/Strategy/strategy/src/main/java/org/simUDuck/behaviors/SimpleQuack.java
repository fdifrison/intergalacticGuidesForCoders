package org.simUDuck.behaviors;

public class SimpleQuack implements QuackBehavior {
    @Override
    public void quack() {
        System.out.println("Quack!");
    }
}
