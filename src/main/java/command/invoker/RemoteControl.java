package command.invoker;

import command.command.Command;
import command.command.NoCommand;
import command.receiver.Air;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RemoteControl {

    Command [] slot1Commands;
    Command [] slot2Commands;
    Command undo;

    public RemoteControl(int slotCount){
        slot1Commands = new Command[slotCount];
        slot2Commands = new Command[slotCount];
        undo = new NoCommand(); // undo default 생성

        Command noCommand = new NoCommand();

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
        undo = slot1Commands[slot]; // undo 설정
    }

    public void slot2ButtonWasPushed(int slot){     // slot2 의 execute 를 실행
        slot2Commands[slot].execute();
        undo = slot2Commands[slot]; // undo 설정
    }

    public void undoButtonWasPressed(){
        undo.undo();
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
