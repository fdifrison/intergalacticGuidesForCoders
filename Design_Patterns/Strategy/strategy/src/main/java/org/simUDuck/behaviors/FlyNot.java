package org.simUDuck.behaviors;

public class FlyNot implements FlyBehavior {
    @Override
    public void fly() {
        System.out.println("I can't fly ...");
    }
}
