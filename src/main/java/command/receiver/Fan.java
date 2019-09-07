package command.receiver;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Fan {

    private static final Logger logger = LoggerFactory.getLogger(Fan.class);

    private String name = "";

    public Fan(String name){
        this.name = name;
    }

    public void on(){
        logger.info(name + " 선풍기가 켜졌습니다.");
    }

    public void off(){
        logger.info(name + " 선풍기가 꺼졌습니다.");
    }
}
