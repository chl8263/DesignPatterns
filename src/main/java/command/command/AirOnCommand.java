package command.command;

import command.receiver.Air;

public class AirOnCommand implements Command {

    private Air air;

    public AirOnCommand(Air air){
        this.air = air;
    }
    @Override
    public void execute() {
        air.on();
    }

    @Override
    public void undo() {
        air.off();
    }
}
