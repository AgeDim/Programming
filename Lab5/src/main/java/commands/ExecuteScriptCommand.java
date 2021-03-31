package commands;

import utility.Console;

/**
 * Command 'execute_script'. Executes scripts from a file. Ectually only checks argument and prints messages.
 */
public class ExecuteScriptCommand extends AbstractCommand {
    public ExecuteScriptCommand() {
        super("execute_script <file_name>", "use a script from file");
    }

    /**
     * Executes the command, but partially.
     *
     * @return Command exit status.
     */
    @Override
    public boolean execute(String argument) {
        if (argument.isEmpty()) {
            Console.println("using: '" + getName() + "'");
            return false;
        } else {
            Console.println("implement script'" + argument + "'...");
            return true;
        }
    }
}
