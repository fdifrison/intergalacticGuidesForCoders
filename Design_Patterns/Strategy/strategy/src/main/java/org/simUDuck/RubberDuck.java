package org.simUDuck;

import org.simUDuck.behaviors.FlyNot;
import org.simUDuck.behaviors.MuteQuack;

public class RubberDuck extends Duck{

    public RubberDuck() {
        setQuackBehavior(new MuteQuack());
        setFlyBehavior(new FlyNot());
    }

    @Override
    public void display() {
        System.out.println("A rubber Duck");
    }
}
