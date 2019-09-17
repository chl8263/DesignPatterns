package teplateMethod;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Tea extends CoffeineBeverage{
    private static final Logger logger = LoggerFactory.getLogger(Tea.class);

    @Override
    void grew() {
        logger.info("차를 우려내는중");
    }

    @Override
    void addCondiments() {
        logger.info("레몬을 추가하는중");
    }
}
