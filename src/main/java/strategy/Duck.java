package strategy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import strategy.behavior.QuackBehavior;
import strategy.quack.Quack;

public class Duck {

    private static final Logger logger = LoggerFactory.getLogger(Duck.class);

    private QuackBehavior quackBehavior;

    public Duck(){
        quackBehavior = new Quack();
    }

    public void setQuackBehavior(QuackBehavior quackBehavior) {
        this.quackBehavior = quackBehavior;
    }

    public void swim(){
        logger.info("수영하는중~");
    }

    public void display(){
        this.swim();
        this.quackBehavior.quack();
    }
}
