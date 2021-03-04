package commands;

import java.time.LocalDateTime;

import data.Vehicle;
import exceptions.IncorrectInputInScriptException;
import utility.CollectionManager;
import utility.Console;
import utility.VehicleAsker;

/**
 * Command 'add'. Adds a new element to collection.
 */
public class AddCommand extends AbstractCommand {
    private final CollectionManager collectionManager;
    private final VehicleAsker vehicleAsker;

    public AddCommand(CollectionManager collectionManager, VehicleAsker vehicleAsker) {
        super("add {element}", "добавить новый элемент в коллекцию");
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
            collectionManager.addToCollection(new Vehicle(
                    collectionManager.generateNextId(),
                    vehicleAsker.askName(),
                    vehicleAsker.askCoordinates(),
                    LocalDateTime.now(),
                    vehicleAsker.askEnginePower(),
                    vehicleAsker.askDistanceTravelled(),
                    vehicleAsker.askVehicleType(),
                    vehicleAsker.askFuelType()
            ));
            Console.println("Vehicle успешно добавлен!");
            return true;
        } catch (IncorrectInputInScriptException exception) {
            Console.printerror("Ошибка исполнения скрипта!");
        }
        return false;
    }
}
