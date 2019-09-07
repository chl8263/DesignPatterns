package command.command;

import command.receiver.Air;
import command.receiver.Computer;

public class ComputerOffCommand implements Command {

    private Computer computer;

    public ComputerOffCommand(Computer computer){
        this.computer = computer;
    }
    @Override
    public void execute() {
        computer.off();
    }

    @Override
    public void undo() {
        computer.on();
    }
}
