package command.command;

import command.receiver.Computer;

public class ComputerOnCommand implements Command {

    private Computer computer;

    public ComputerOnCommand(Computer computer){
        this.computer = computer;
    }
    @Override
    public void execute() {
        computer.on();
    }

    @Override
    public void undo() {
        computer.off();
    }
}
