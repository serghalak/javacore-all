package patterns.headfirst.strategy.ducks;

import patterns.headfirst.strategy.ducks.impl.FlyNoWay;
import patterns.headfirst.strategy.ducks.impl.Quack;

public class ModelDuck extends Duck{
    public ModelDuck() {
        flyBehavior=new FlyNoWay();
        quackBehavior=new Quack();
    }

    @Override
    public void display() {
        System.out.println("I am a model duck");
    }
}
