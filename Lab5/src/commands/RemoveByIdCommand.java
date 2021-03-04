package commands;


import data.Vehicle;
import exceptions.VehicleNotFoundException;
import exceptions.WrongAmountOfElementsException;
import exceptions.CollectionIsEmptyException;
import utility.CollectionManager;
import utility.Console;

/**
 * Command 'remove_by_id'. Removes the element by its ID.
 */
public class RemoveByIdCommand extends AbstractCommand {
    private final CollectionManager collectionManager;

    public RemoveByIdCommand(CollectionManager collectionManager) {
        super("remove_by_id <ID>", "удалить элемент из коллекции по ID");
        this.collectionManager = collectionManager;
    }

    /**
     * Executes the command.
     *
     * @return Command exit status.
     */
    @Override
    public boolean execute(String argument) {
        try {
            if (argument.isEmpty()) {
                Console.println("использование: '" + getName() + "'");
                return false;
            }
            if (collectionManager.collectionSize() == 0) {
                Console.printerror("Коллекция пуста!");
                return false;
            }
            int id = Integer.parseInt(argument);
            Vehicle vehicleToRemove = collectionManager.getById(id);
            if (vehicleToRemove == null) {
                Console.printerror("Vehicle с таким ID в коллекции нет!");
                return false;
            } else {
                collectionManager.removeFromCollection(vehicleToRemove);
                Console.println("Vehicle успешно удален!");
                return true;
            }
        } catch (NumberFormatException exception) {
            Console.printerror("ID должен быть представлен числом!");
        }
        return false;
    }
}
