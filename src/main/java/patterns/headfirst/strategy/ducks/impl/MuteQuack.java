package patterns.headfirst.strategy.ducks.impl;

import patterns.headfirst.strategy.ducks.QuackBehavior;

public class MuteQuack implements QuackBehavior {
    @Override
    public void quack() {
        System.out.println("I am not quacking << Silence >> ");
    }
}
