package commands;

import exceptions.WrongAmountOfElementsException;
import utility.Console;

/**
 * Command 'help'. It's here just for logical structure.
 */
public class HelpCommand extends AbstractCommand {

    public HelpCommand() {
        super("help", "вывести справку по доступным командам");
    }

    /**
     * Executes the command.
     * @return Command exit status.
     */
    @Override
    public boolean execute(String argument) {
            if (!argument.isEmpty()) {Console.println("использование: '" + getName() + "'");return false;}
            else{return true;}
    }
}
