package commands;

import data.Vehicle;
import exceptions.WrongInputFormatException;
import utility.CollectionManager;
import utility.Console;
import utility.VehicleAsker;

import java.time.LocalDateTime;

/**
 * Command 'remove_greater'. Removes elements greater than user entered.
 */
public class RemoveGreaterCommand extends AbstractCommand {
    private final CollectionManager collectionManager;
    private final VehicleAsker vehicleAsker;

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
        if (!argument.isEmpty()) {
            Console.println("использование: '" + getName() + "'");
            return false;
        }
        if (collectionManager.collectionSize() == 0) {
            Console.printerror("Коллекция пуста!");
            return false;
        }
        Vehicle vehicleToFind;
        try {
            vehicleToFind = new Vehicle(
                    collectionManager.generateNextId(),
                    vehicleAsker.inputVehicleName(),
                    vehicleAsker.getCoordinates(),
                    LocalDateTime.now(),
                    vehicleAsker.inputEnPower(),
                    vehicleAsker.inputDistTravel(),
                    vehicleAsker.inputVType(),
                    vehicleAsker.inputFType()
            );
            collectionManager.removeGreater(vehicleToFind);
            Console.println("vehicles успешно удалены!");
            return true;
        } catch (WrongInputFormatException e) {
            Console.printerror("Неверный формат ввода");
        }
        return false;
    }
}