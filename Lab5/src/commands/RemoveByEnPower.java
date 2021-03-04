package commands;


import data.Vehicle;
import exceptions.VehicleNotFoundException;
import exceptions.WrongAmountOfElementsException;
import exceptions.CollectionIsEmptyException;
import utility.CollectionManager;
import utility.Console;

/**
 * Command 'remove_by_EnPow'. Removes the element by its Engine Power.
 */
public class RemoveByEnPower extends AbstractCommand {
    private final CollectionManager collectionManager;

    public RemoveByEnPower(CollectionManager collectionManager) {
        super("remove_by_EnPow <enginePower>", "удалить элемент из коллекции по engine power");
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
            int enginePower = Integer.parseInt(argument);
            Vehicle vehicleToRemove = collectionManager.getByEnginePower(enginePower);
            if (vehicleToRemove == null) {
                Console.printerror("Vehicle с таким engine power в коллекции нет!");
                return false;
            }
            collectionManager.removeFromCollection(vehicleToRemove);
            Console.println("Vehicle успешно удален!");
            return true;
        } catch (NumberFormatException exception) {
            Console.printerror("Engine power должен быть представлен числом!");
        }
        return false;
    }
}
