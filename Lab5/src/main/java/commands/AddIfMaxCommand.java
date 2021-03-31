package commands;

import data.Vehicle;
import exceptions.WrongInputFormatException;
import utility.CollectionManager;
import utility.Console;
import utility.VehicleAsker;

import java.time.LocalDateTime;

/**
 * Command 'add_if_max'. Adds a new element to collection if it's more than the maximal one.
 */
public class AddIfMaxCommand extends AbstractCommand {
    private final CollectionManager collectionManager;
    private final VehicleAsker vehicleAsker;

    public AddIfMaxCommand(CollectionManager collectionManager, VehicleAsker vehicleAsker) {
        super("add_if_max {element}", "Adds a new element to collection if it's more than the maximal one");
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
        Vehicle vehicleToAdd = null;
        while (true) {
            try {
                vehicleToAdd = new Vehicle(
                        collectionManager.generateNextId(),
                        vehicleAsker.inputVehicleName(),
                        vehicleAsker.getCoordinates(),
                        LocalDateTime.now(),
                        vehicleAsker.inputEnPower(),
                        vehicleAsker.inputDistTravel(),
                        vehicleAsker.inputVType(),
                        vehicleAsker.inputFType()
                );
            } catch (WrongInputFormatException exception) {
                Console.printerror("Incorrect input");
            }
            if (collectionManager.collectionSize() == 0 || vehicleToAdd.compareTo(collectionManager.GetMax()) > 0) {
                collectionManager.addToCollection(vehicleToAdd);
                Console.println("Vehicle successfully added!");
                return true;
            } else Console.printerror("value og Vehicle less, than values highest Vehicle!");
            return false;
        }
    }
}
