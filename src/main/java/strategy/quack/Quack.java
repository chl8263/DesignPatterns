package strategy.quack;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import strategy.behavior.QuackBehavior;

public class Quack implements QuackBehavior {
    private static final Logger logger = LoggerFactory.getLogger(Quack.class);
    @Override
    public void quack() {
        logger.info("꿲꿲");
    }
}
