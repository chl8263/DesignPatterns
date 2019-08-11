package strategy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ToyDuck extends Duck {
    private static final Logger logger = LoggerFactory.getLogger(ToyDuck.class);


    @Override
    public void swim() {
        logger.info("장난감 수영~");
    }


    @Override
    public void display() {
        super.display();
    }

}
