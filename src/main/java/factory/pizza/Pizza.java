package factory.pizza;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class Pizza {
    private static final Logger logger = LoggerFactory.getLogger(Pizza.class);

    public String loca ;

    public String name ;

    public void prepare(){
        logger.info(loca + " " + name + "준비중~~");
    }

    public void bake(){
        logger.info("굽는중~~");
    }

    public void cut(){
        logger.info("자르는중~~");
    }

    public void box(){
        logger.info("포장중~~");
    }
}
