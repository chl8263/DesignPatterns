package command.invoker;

import command.command.Command;

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
