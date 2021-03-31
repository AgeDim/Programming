package commands;

import data.Vehicle;
import utility.CollectionManager;
import utility.Console;

/**
 * Command 'remove_First'. Remove first object in collection.
 */
public class RemoveFirstCommand extends AbstractCommand {
    private final CollectionManager collectionManager;

    public RemoveFirstCommand(CollectionManager collectionManager) {
        super("remove_First", "remove first element in collection");
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
        }
        if (collectionManager.collectionSize() == 0) {
            Console.printerror("Collection is empty!");
            return false;
        }
        Vehicle vehicleToRemove = collectionManager.getFirst();
        collectionManager.removeFromCollection(vehicleToRemove);
        Console.println("Vehicle successfully remove!");
        return true;

    }
}
