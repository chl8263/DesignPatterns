package command.receiver;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Light {

    private static final Logger logger = LoggerFactory.getLogger(Light.class);


    public void on(){
        logger.info("전등이 켜졌습니다.");
    }
}
