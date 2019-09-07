package command.command;

import command.receiver.Fan;
import command.receiver.Light;

public class FanOffCommand implements Command {

    private Fan fan ;

    public FanOffCommand(Fan fan){
        this.fan = fan;
    }
    @Override
    public void execute() {
        fan.off();
    }

    @Override
    public void undo() {
        fan.on();
    }
}
