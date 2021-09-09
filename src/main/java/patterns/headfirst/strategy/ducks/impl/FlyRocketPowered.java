package patterns.headfirst.strategy.ducks.impl;

import patterns.headfirst.strategy.ducks.FlyBehavior;

public class FlyRocketPowered implements FlyBehavior {
    @Override
    public void fly() {
        System.out.println("I am flying with rocket");
    }
}
