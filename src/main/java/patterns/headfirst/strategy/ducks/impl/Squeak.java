package patterns.headfirst.strategy.ducks.impl;

import patterns.headfirst.strategy.ducks.QuackBehavior;

public class Squeak implements QuackBehavior {
    @Override
    public void quack() {
        System.out.println("I am quacking Squeak");
    }
}
