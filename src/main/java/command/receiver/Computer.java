package command.receiver;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Computer {

    private static final Logger logger = LoggerFactory.getLogger(Computer.class);

    private String name = "";

    public Computer(String name){
        this.name = name;
    }

    public void on(){
        logger.info(name + " 컴퓨터가 켜졌습니다.");
    }

    public void off(){
        logger.info(name + " 컴퓨터가 꺼졌습니다.");
    }
}
