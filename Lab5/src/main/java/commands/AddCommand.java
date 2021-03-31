package commands;

import data.Vehicle;
import exceptions.NotInDeclaredLimitsException;
import exceptions.WrongInputFormatException;
import utility.CollectionManager;
import utility.Console;
import utility.VehicleAsker;

import java.time.LocalDateTime;

/**
 * Command 'add'. Adds a new element to collection.
 */
public class AddCommand extends AbstractCommand {
    private final CollectionManager collectionManager;
    private final VehicleAsker vehicleAsker;

    public AddCommand(CollectionManager collectionManager, VehicleAsker vehicleAsker) {
        super("add {element}", "add new element in collection");
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
            Console.println("using: '" + getName() + "'");
            return false;
        }
        while (true) {
            try {
                collectionManager.addToCollection(new Vehicle(
                        collectionManager.generateNextId(),
                        vehicleAsker.inputVehicleName(),
                        vehicleAsker.getCoordinates(),
                        LocalDateTime.now(),
                        vehicleAsker.inputEnPower(),
                        vehicleAsker.inputDistTravel(),
                        vehicleAsker.inputVType(),
                        vehicleAsker.inputFType()
                ));
            } catch (WrongInputFormatException e) {
                Console.printerror("Incorrect input!");
            }
            Console.println("Vehicle successfully added!");
            return true;
        }
    }
    /**
     * Command "Add". Adds element to collection.
     */

}
