package command.receiver;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Light {

    private static final Logger logger = LoggerFactory.getLogger(Light.class);

    private String name = "";

    public Light(String name){
        this.name = name;
    }

    public void on(){
        logger.info(name + " 전등이 켜졌습니다.");
    }

    public void off(){
        logger.info(name + " 전등이 꺼졌습니다.");
    }
}
