package commands;

import utility.CollectionManager;
import utility.Console;

/**
 * Command 'save'. Saves the collection to a file.
 */
public class SaveCommand extends AbstractCommand {
    private final CollectionManager collectionManager;

    public SaveCommand(CollectionManager collectionManager) {
        super("save", "save collection in file");
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
            collectionManager.saveCollection();
            return true;
        }
    }
}