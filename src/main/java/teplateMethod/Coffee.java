package teplateMethod;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Coffee extends CoffeineBeverage {
    private static final Logger logger = LoggerFactory.getLogger(Coffee.class);

    @Override
    void grew() {
        logger.info("필터로 커피를 우려내는중");
    }

    @Override
    void addCondiments() {
        logger.info("설탕과 우류를 추가하는중");
    }
}
