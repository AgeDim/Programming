package commands;

import java.time.LocalDateTime;
import data.Vehicle;
import exceptions.IncorrectInputInScriptException;
import exceptions.WrongAmountOfElementsException;
import utility.CollectionManager;
import utility.Console;
import utility.VehicleAsker;

/**
 * Command 'add_if_max'. Adds a new element to collection if it's more than the maximal one.
 */
public class AddIfMaxCommand extends AbstractCommand {
    private CollectionManager collectionManager;
    private VehicleAsker vehicleAsker;

    public AddIfMaxCommand(CollectionManager collectionManager, VehicleAsker vehicleAsker) {
        super("add_if_max {element}", "добавить новый элемент, если его значение больше, чем у наибольшего");
        this.collectionManager = collectionManager;
        this.vehicleAsker = vehicleAsker;
    }

    /**
     * Executes the command.
     * @return Command exit status.
     */
    @Override
    public boolean execute(String argument) {
        try {
            if (!argument.isEmpty()) throw new WrongAmountOfElementsException();
            Vehicle vehicleToAdd = new Vehicle(
                    collectionManager.generateNextId(),
                    vehicleAsker.askName(),
                    vehicleAsker.askCoordinates(),
                    LocalDateTime.now(),
                    vehicleAsker.askEnginePower(),
                    vehicleAsker.askDistanceTravelled(),
                    vehicleAsker.askVehicleType(),
                    vehicleAsker.askFuelType()
            );
            if (collectionManager.collectionSize() == 0 || vehicleToAdd.compareTo(collectionManager.GetMax()) > 0) {
                collectionManager.addToCollection(vehicleToAdd);
                Console.println("Vehicle успешно добавлен!");
                return true;
            } else Console.printerror("значение Vehicle меньше, чем значение наибольшего из Vehicle!");
        } catch (WrongAmountOfElementsException exception) {
            Console.println("использование: '" + getName() + "'");
        } catch (IncorrectInputInScriptException exception) {}
        return false;
    }
}
