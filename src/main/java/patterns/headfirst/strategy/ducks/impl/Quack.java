package patterns.headfirst.strategy.ducks.impl;

import patterns.headfirst.strategy.ducks.QuackBehavior;

public class Quack implements QuackBehavior {
    @Override
    public void quack() {
        System.out.println("Quack quack ...");
    }
}
