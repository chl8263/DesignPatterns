package command.command;

import command.receiver.Air;
import command.receiver.Fan;

public class AirOffCommand implements Command {

    private Air air;

    public AirOffCommand(Air air){
        this.air = air;
    }
    @Override
    public void execute() {
        air.off();
    }

    @Override
    public void undo() {
        air.on();
    }
}
