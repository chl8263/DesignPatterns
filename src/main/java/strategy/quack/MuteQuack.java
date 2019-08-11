package strategy.quack;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import strategy.behavior.QuackBehavior;

public class MuteQuack implements QuackBehavior {
    private static final Logger logger = LoggerFactory.getLogger(MuteQuack.class);
    @Override
    public void quack() {
        logger.info("조용~");
    }
}
