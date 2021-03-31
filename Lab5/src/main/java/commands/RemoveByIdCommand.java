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
        super("remove_by_id <ID>", "remove element for ID");
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
                Console.println("using: '" + getName() + "'");
                return false;
            }
            if (collectionManager.collectionSize() == 0) {
                Console.printerror("Collection is empty!");
                return false;
            }
            int id = Integer.parseInt(argument);
            Vehicle vehicleToRemove = collectionManager.getById(id);
            if (vehicleToRemove == null) {
                Console.printerror("Vehicle with this ID not found!");
                return false;
            } else {
                collectionManager.removeFromCollection(vehicleToRemove);
                Console.println("Vehicle successfully remove!");
                return true;
            }
        } catch (NumberFormatException exception) {
            Console.printerror("ID must be a number");
        }
        return false;
    }
}
