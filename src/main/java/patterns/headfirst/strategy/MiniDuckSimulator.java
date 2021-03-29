package patterns.headfirst.strategy;

import patterns.headfirst.strategy.ducks.Duck;
import patterns.headfirst.strategy.ducks.MallardDuck;
import patterns.headfirst.strategy.ducks.impl.FlyRocketPowered;

public class MiniDuckSimulator {
    public static void main(String[] args) {
        Duck mallard=new MallardDuck();
        mallard.performQuack();
        mallard.setFlyBehavior(new FlyRocketPowered());
        mallard.performFly();
    }
}
