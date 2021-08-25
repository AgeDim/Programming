package servercommands;

import utility.Receiver;

public class ValidateIdCommand extends AbstractCommand {
    private final Receiver receiver;

    public ValidateIdCommand(Receiver receiver) {
        this.receiver = receiver;
    }

    @Override
    public void execute(String arg) {
        receiver.validateId(arg);
    }
}