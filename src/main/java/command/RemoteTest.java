package command;

import command.command.LightOnCommand;
import command.invoker.SImpleRemoteControl;
import command.receiver.Light;

public class RemoteTest {

    public RemoteTest(){
        run();
    }

    private void run(){
        SImpleRemoteControl control = new SImpleRemoteControl();    // invoker 새성
        Light light = new Light();      // Command 생성
        LightOnCommand lightOnCommand = new LightOnCommand(light);  // ConcreateCommand 생성

        control.setCommand(lightOnCommand); // invoker 에 ConcreateCommand 를 넣어줌
        control.buttonWasPressed();
    }
}
