package command.receiver;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Air {

    private static final Logger logger = LoggerFactory.getLogger(Air.class);

    private String name = "";

    public Air(String name){
        this.name = name;
    }

    public void on(){
        logger.info(name + " 에어컨이 켜졌습니다.");
    }

    public void off(){
        logger.info(name + " 에어컨이 꺼졌습니다.");
    }
}
