package commands;

import data.Vehicle;
import exceptions.CollectionIsEmptyException;
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
     * @return Command exit status.
     */
    @Override
    public boolean execute(String argument) {
        try {
            if (collectionManager.collectionSize() == 0) throw new CollectionIsEmptyException();
            Vehicle vehicleToRemove = collectionManager.getFirst();
            collectionManager.removeFromCollection(vehicleToRemove);
            Console.println("Vehicle успешно удален!");
            return true;
        } catch (CollectionIsEmptyException exception) {
            Console.printerror("коллекция пуста!");
        }
        return false;
    }
}
