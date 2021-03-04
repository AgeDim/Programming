package commands;

import data.Vehicle;
import utility.CollectionManager;
import utility.Console;

/**
 * Command 'remove_First'. Remove first object in collection.
 */
public class RemoveFirstCommand extends AbstractCommand {
    private CollectionManager collectionManager;

    public RemoveFirstCommand(CollectionManager collectionManager) {
        super("remove_First", "удалить первый элемент элемент из коллекции");
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
            Console.println("использование: '" + getName() + "'");
            return false;
        }
        if (collectionManager.collectionSize() == 0) {
            Console.printerror("Коллекция пуста!");
            return false;
        }
        Vehicle vehicleToRemove = collectionManager.getFirst();
        collectionManager.removeFromCollection(vehicleToRemove);
        Console.println("Vehicle успешно удален!");
        return true;

    }
}
