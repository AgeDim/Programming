package commands;

import utility.CollectionManager;
import utility.Console;

/**
 * Command 'show'. Shows information about all elements of the collection.
 */
public class ShowCommand extends AbstractCommand {
    private final CollectionManager collectionManager;

    public ShowCommand(CollectionManager collectionManager) {
        super("show", "show the element of collection");
        this.collectionManager = collectionManager;
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
            Console.println(collectionManager);
            return true;
        }
    }
}
