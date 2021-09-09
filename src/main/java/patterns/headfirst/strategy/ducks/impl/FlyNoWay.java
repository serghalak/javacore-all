package patterns.headfirst.strategy.ducks.impl;

import patterns.headfirst.strategy.ducks.FlyBehavior;

public class FlyNoWay implements FlyBehavior {



    @Override
    public void fly() {
        System.out.println("I am not flying");
    }
}
