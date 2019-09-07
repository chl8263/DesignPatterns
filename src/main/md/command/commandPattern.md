## 6. Command pattern

### 정의
> 커맨드 패턴을 이용하면 요구 사항을 객체로 갭슐화 할 수 있으며, 매개변수를 써서 여러 가지 다른 요구사항을 집어넣을 수도 있다.
> 또한 요청 내역을 큐에 저장하거나 로그로 기록할 수도 있으며 작업 취소 기능도 지원 가능하다.

>실행될 기능을 캡슐화함으로써 주어진 여러 기능을 실행할 수 있는 재사용성이 높은 클래스를 설계하는 패턴
>즉, 이벤트가 발생했을 때 실행될 기능이 다양하면서도 변경이 필요한 경우에 이벤트를 발생시키는 클래스를 변경하지 않고 재사용하고자 할 때 유용하다

### 커맨드 패턴의 역할 및 구성

- __Command__
  - 실행될 기능에 대한 인터페이스
  - 실행될 기능을 execute 메서드로 선언함
  
- __ConcreateCommnad__
  - 실제로 실행되는 기능을 구현
  - 즉 , Command 라는 인터페이스를 구현함

- __Invoker__
  - 기능의 실행을 요청하는 호출자
  
- __Receiver__
  - ConcreateCommand 에서 execute 메서드를 구현할 때 필요한 클래스
  - 즉, ConcreateCommand 의 기능을 실행하기 위해 사용하는 수신자 클래스

### 기본적인 예제 
스마트폰의 Application 에 스마트 리모컨을 만들어 본다고 가정하자.

------

[execute] [execute]  - <비어있는 슬롯1>

[execute] [execute]  - <비어있는 슬롯1>

[execute] [execute]  - <비어있는 슬롯1>

[execute] [execute]  - <비어있는 슬롯1>

[execute] [execute]  - <비어있는 슬롯1>

------

위와같이 버튼이 2개가 들어있고 사용자의 기호에 따라 원하는 기능을 넣을 수 있다. 

execute는 해당하는 기능에 on , off , turn right 등등 해당하는 기능사항에따라 바뀔 수 있다.

예를들어 전등을 키고 끄는 기능 , 커튼을 닫는기능 등등....

따라서 리모컨의 execute, execute 버튼은 해당 슬롯의 기능중 구현되어 있는 execute, execute 를 호출 하기만 하면 된다.

execute,execute 에 해당하는 버튼들은 어떤것이 구현되어 있는지 알 필요가 없다.

간단하게 전등을 키고끄는 슬롯에 대한 기능을 만들겠다.

우선 Command 인터페이스 부터 만들어 보자.

~~~java
public interface Command {
 
    public void execute();
}
~~~

Receiver 에 해당하는 Light Class 

~~~java
public class Light {

    private static final Logger logger = LoggerFactory.getLogger(Light.class);


    public void on(){
        logger.info("전등이 켜졌습니다.");
    }
}
~~~

ConcreateCommand 에 해당하는 LightOnCommand Class

~~~java
public class LightOnCommand implements Command {

    private Light light ;

    public LightOnCommand (Light light){
        this.light = light;
    }
    @Override
    public void execute() {
        light.on();
    }
}
~~~

기능을 호출하는 invoker 인 SImpleRemoteControl Class 즉 이 예제 에서는 리모컨이 되겠다.
~~~java
public class SImpleRemoteControl {

    private Command slot;      // 우선 리모컨의 슬롯 1개만 만들어 놓았음

    public SImpleRemoteControl(){}

    public void setCommand(Command command){    // Command 를 받아 제어하기 위한 메소드
        this.slot = command;
    }

    public void buttonWasPressed(){     // 리모컨의 버튼이 눌릴경우 command 의 execute가 호출
        slot.execute();
    }
}
~~~

위의 예제를 실행하기 위한 Test 코드와 결과는 아래와 같다.
~~~java
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
~~~

~~~
22:36:47.436 [main] INFO command.receiver.Light - 전등이 켜졌습니다.

Process finished with exit code 0
~~~

__의 예제를 통해 살펴봤던것과 같이 Command pattern의 클래스 다이어 그램은 다음과 같다.__

![base](/src/main/md/command/img/command1.PNG)

### 슬롯에 더 많은 기능을 추가하기

이제 각 슬롯에 더 많은 기능들을 추가해 보겠다.

------

   [execute]           [execute]       - <거실전등>
(LightOnCommand)   (LightOffCommand)

   [execute]           [execute]       - <부엌전등>
(LightOnCommand)   (LightOffCommand)

   [execute]            [execute]       - <거실 선풍기>
(FanHighCommand)     (FanOffCommand)

   [execute]            [execute]  - <거실 에어컨>
(AirOnCommand)     (AirOffCommand)

   [execute]            [execute]  - <컴퓨터>
(ComputerOnCommand)  (ComputerOffCommand)

------

Invoker 를 새롭게 변경하여 보자.

~~~java
public class RemoteControl {
    Command [] slot1Commands;
    Command [] slot2Commands;

    public RemoteControl(int slotCount){
        slot1Commands = new Command[slotCount];
        slot2Commands = new Command[slotCount];

        Command noCommand = new NoCommand();  // NULL 을 피하기 위해 아무것도 실행하지 않는 Command 

        for (Command com : slot1Commands){  // slot1Commands 초기화
            com = noCommand;
        }
        for (Command com : slot2Commands){  // slot2Commands 초기화
            com = noCommand;
        }
    }

    public void setCommand(int slot, Command slot1Command, Command slot2Command){
        slot1Commands[slot] = slot1Command;
        slot2Commands[slot] = slot2Command;
    }

    public void slot1ButtonWasPushed(int slot){     // slot1 의 execute 를 실행
        slot1Commands[slot].execute();
    }

    public void slot2ButtonWasPushed(int slot){     // slot2 의 execute 를 실행
        slot2Commands[slot].execute();
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();

        builder.append("\n-------- Remote Control ----------");

        for(int i=0; i< slot1Commands.length; i++){
            builder.append("[slot" +i + "] " + slot1Commands[i].getClass().getName());
            builder.append("       ");
            builder.append(slot2Commands[i].getClass().getName());
        }

        return builder.toString();
    }
}
~~~

그리고 실행할 Test를 작성해 보자.

~~~java
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
    }
}
~~~

그에따른 결과는 아래와 같다.
~~~
00:34:52.804 [main] INFO command.receiver.Light - 거실 전등이 켜졌습니다.
00:34:52.804 [main] INFO command.receiver.Light - 부엌 전등이 켜졌습니다.
00:34:52.804 [main] INFO command.receiver.Fan - 거실 선풍기가 켜졌습니다.
00:34:52.804 [main] INFO command.receiver.Air - 거실 에어컨이 켜졌습니다.
00:34:52.804 [main] INFO command.receiver.Computer - 내방 컴퓨터가 켜졌습니다.
00:34:52.804 [main] INFO command.receiver.Light - 거실 전등이 꺼졌습니다.
00:34:52.804 [main] INFO command.receiver.Light - 부엌 전등이 꺼졌습니다.
00:34:52.804 [main] INFO command.receiver.Fan - 거실 선풍기가 꺼졌습니다.
00:34:52.804 [main] INFO command.receiver.Air - 거실 에어컨이 꺼졌습니다.
00:34:52.804 [main] INFO command.receiver.Computer - 내방 컴퓨터가 꺼졌습니다.

Process finished with exit code 0
~~~





### UNDO 버튼 추가하기




