package command;

import command.command.*;
import command.invoker.RemoteControl;
import command.invoker.SImpleRemoteControl;
import command.receiver.Air;
import command.receiver.Computer;
import command.receiver.Fan;
import command.receiver.Light;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RemoteTest {
    private static final Logger logger = LoggerFactory.getLogger(RemoteTest.class);

    public RemoteTest(){
        run();
    }

    private void run(){
        // invoker 생성
        RemoteControl remoteControl = new RemoteControl(5);

        // receiver 생성
        Light livingRoomLight = new Light("거실");
        Light kitchenLight = new Light("부엌");
        Fan livingRoomFan = new Fan("거실");
        Air livingRoomAir = new Air("거실");
        Computer computer = new Computer("내방");

        // Command 생성
        LightOnCommand  livingRoomlightOnCommand = new LightOnCommand(livingRoomLight);
        LightOffCommand  livingRoomlightOffCommand = new LightOffCommand(livingRoomLight);

        LightOnCommand  kitchenOnCommand = new LightOnCommand(kitchenLight);
        LightOffCommand  kitchenOffCommand = new LightOffCommand(kitchenLight);

        FanOnCommand livingRoomFanOnCommand = new FanOnCommand(livingRoomFan);
        FanOffCommand livingRoomFanOffCommand = new FanOffCommand(livingRoomFan);

        AirOnCommand livingRoomAirOnCommand = new AirOnCommand(livingRoomAir);
        AirOffCommand livingRoomAirOffCommand = new AirOffCommand(livingRoomAir);

        ComputerOnCommand computerOnCommand = new ComputerOnCommand(computer);
        ComputerOffCommand computerOffCommand = new ComputerOffCommand(computer);

        // invoker 에 command setting
        remoteControl.setCommand(0, livingRoomlightOnCommand , livingRoomlightOffCommand);
        remoteControl.setCommand(1, kitchenOnCommand , kitchenOffCommand);
        remoteControl.setCommand(2, livingRoomFanOnCommand , livingRoomFanOffCommand);
        remoteControl.setCommand(3, livingRoomAirOnCommand , livingRoomAirOffCommand);
        remoteControl.setCommand(4, computerOnCommand , computerOffCommand);

        logger.info(remoteControl.toString());// 설정이 잘 되었는지 확인

        // slot 1 버튼 누르기
        remoteControl.slot1ButtonWasPushed(0);
        remoteControl.slot1ButtonWasPushed(1);
        remoteControl.slot1ButtonWasPushed(2);
        remoteControl.slot1ButtonWasPushed(3);
        remoteControl.slot1ButtonWasPushed(4);

        // slot 2 버튼 누르기
        remoteControl.slot2ButtonWasPushed(0);
        remoteControl.slot2ButtonWasPushed(1);
        remoteControl.slot2ButtonWasPushed(2);
        remoteControl.slot2ButtonWasPushed(3);
        remoteControl.slot2ButtonWasPushed(4);

        remoteControl.undoButtonWasPressed();
    }
}
