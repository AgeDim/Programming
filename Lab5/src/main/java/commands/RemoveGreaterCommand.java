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
        super("remove_greater {element}", "remove elements in collection more than the given");
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
        if (collectionManager.collectionSize() == 0) {
            Console.printerror("Collection is empty!");
            return false;
        }
        Vehicle vehicleToFind = null;
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

            if(collectionManager.removeGreater(vehicleToFind)){
                Console.println("Vehicle's removed!");
            }
            else{
                Console.printerror("This vehicle's not found in collection!");
            }
            return true;
        } catch (WrongInputFormatException e) {
            Console.printerror("Incorrect input format");
        }
        return false;
    }
}