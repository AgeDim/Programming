package commands;

import data.Coordinates;
import data.FuelType;
import data.Vehicle;
import data.VehicleType;
import exceptions.IncorrectInputInScriptException;
import exceptions.WrongInputFormatException;
import utility.CollectionManager;
import utility.Console;
import utility.VehicleAsker;

import java.time.LocalDateTime;

/**
 * Command 'update'. Updates the information about selected marine.
 */
public class UpdateCommand extends AbstractCommand {
    private final CollectionManager collectionManager;
    private final VehicleAsker vehicleAsker;

    public UpdateCommand(CollectionManager collectionManager, VehicleAsker vehicleAsker) {
        super("update <ID> {element}", "update value of element for ID");
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
            if (argument.isEmpty()) {
                Console.println("using: '" + getName() + "'");
                return false;
            }
            if (collectionManager.collectionSize() == 0) {
                Console.printerror("Collection is empty!");
            }

            int id = Integer.parseInt(argument);
            Vehicle oldVehicle = collectionManager.getById(id);
            if (oldVehicle == null) {
                Console.printerror("Vehicle with this ID not found!");
                return false;
            }

            String name = oldVehicle.getName();
            Coordinates coordinates = oldVehicle.getCoordinates();
            LocalDateTime creationDate = oldVehicle.getCreationDate();
            int enginePower = oldVehicle.getEnginePower();
            int distanceTravelled = oldVehicle.getDistanceTravelled();
            VehicleType type = oldVehicle.getType();
            FuelType fuelType = oldVehicle.getFuelType();

            collectionManager.removeFromCollection(oldVehicle);

            if (vehicleAsker.askQuestion("Do you want to change a name of vehicle?"))
                name = vehicleAsker.inputVehicleName();
            if (vehicleAsker.askQuestion("Do you want to change a coordinates of vehicle?"))
                coordinates = vehicleAsker.getCoordinates();
            if (vehicleAsker.askQuestion("Do you want to change a engine power of vehicle?"))
                enginePower = vehicleAsker.inputEnPower();
            if (vehicleAsker.askQuestion("Do you want to change a distance travelled of vehicle?"))
                distanceTravelled = vehicleAsker.inputDistTravel();
            if (vehicleAsker.askQuestion("Do you want to change a type of vehicle?"))
                type = vehicleAsker.inputVType();
            if (vehicleAsker.askQuestion("Do you want to change a fuel type of vehicle?"))
                fuelType = vehicleAsker.inputFType();

            collectionManager.addToCollection(new Vehicle(
                    id,
                    name,
                    coordinates,
                    creationDate,
                    enginePower,
                    distanceTravelled,
                    type,
                    fuelType
            ));
            Console.println("Vehicle is updated!");
            return true;
        } catch (NumberFormatException exception) {
            Console.printerror("ID must be a number!");
        } catch (IncorrectInputInScriptException exception) {
            Console.printerror("Script error!");
        } catch (WrongInputFormatException exception) {
            Console.printerror("Incorrect input format");
        }
        return false;
    }
}
