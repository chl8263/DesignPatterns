package decorator;

import decorator.component.Beverage;
import decorator.component.Espresso;
import decorator.component.HouseBlend;
import decorator.decorator.Mocha;
import decorator.decorator.Soy;
import decorator.decorator.Whip;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CoffeMachine {
    private static final Logger logger = LoggerFactory.getLogger(CoffeMachine.class);

    public CoffeMachine(){
        run();
    }

    public void run(){
        Beverage beverage = new Espresso();

        logger.info(beverage.getDescription() + " $" + beverage.cost());

        Beverage beverage2 = new HouseBlend();
        beverage2 = new Mocha(beverage2);
        beverage2 = new Soy(beverage2);
        beverage2 = new Whip(beverage2);

        logger.info(beverage2.getDescription() + " $" + beverage2.cost());
    }
}
