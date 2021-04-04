package commands;

import data.Vehicle;
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
        if (!argument.isEmpty()) {
            Console.println("использование: '" + getName() + "'");
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
                Console.printerror("Неверный ввод!");
            }
            Console.println("Vehicle успешно добавлен!");
            return true;
        }
    }
}
