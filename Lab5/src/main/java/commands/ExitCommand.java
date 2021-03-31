package commands;

import utility.Console;

/**
 * Command 'exit'. Checks for wrong arguments then do nothing.
 */
public class ExitCommand extends AbstractCommand {

    public ExitCommand() {
        super("exit", "exit from program");
    }

    /**
     * Executes the command.
     *
     * @return Command exit status.
     */
    @Override
    public boolean execute(String argument) {
        if (!argument.isEmpty()) {
            Console.println("using: '" + getName() + "'");
            return false;
        } else {
            return true;
        }
    }
}


