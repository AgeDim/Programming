package commands;

import java.time.LocalDateTime;

import data.Vehicle;
import exceptions.IncorrectInputInScriptException;
import utility.CollectionManager;
import utility.Console;
import utility.VehicleAsker;

/**
 * Command 'remove_greater'. Removes elements greater than user entered.
 */
public class RemoveGreaterCommand extends AbstractCommand {
    private CollectionManager collectionManager;
    private VehicleAsker vehicleAsker;

    public RemoveGreaterCommand(CollectionManager collectionManager, VehicleAsker vehicleAsker) {
        super("remove_greater {element}", "удалить из коллекции все элементы, превышающие заданный");
        this.collectionManager = collectionManager;
        this.vehicleAsker = vehicleAsker;
    }

    /**
     * Executes the command.
     *
     * @return Command exit status.
     */
    @Override
    public boolean execute(String argument) {
        try {
            if (!argument.isEmpty()) {
                Console.println("использование: '" + getName() + "'");
                return false;
            }
            if (collectionManager.collectionSize() == 0) {
                Console.printerror("Коллекция пуста!");
                return false;
            }
            Vehicle vehicleToFind = new Vehicle(
                    collectionManager.generateNextId(),
                    vehicleAsker.askName(),
                    vehicleAsker.askCoordinates(),
                    LocalDateTime.now(),
                    vehicleAsker.askEnginePower(),
                    vehicleAsker.askDistanceTravelled(),
                    vehicleAsker.askVehicleType(),
                    vehicleAsker.askFuelType()
            );
            Vehicle vehicleFromCollection = collectionManager.getByValue(vehicleToFind);
            if (vehicleFromCollection == null) {
                Console.printerror("vehicle с такими характеристиками в коллекции нет!");
                return false;
            }
            collectionManager.removeGreater(vehicleFromCollection);
            Console.println("vehicles успешно удалены!");
            return true;
        } catch (IncorrectInputInScriptException exception) {
            Console.printerror("Ошибка исполнения скрипта!");
        }
        return false;
    }
}
