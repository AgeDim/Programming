package commands;

import java.time.LocalDateTime;
import data.Vehicle;
import exceptions.CollectionIsEmptyException;
import exceptions.IncorrectInputInScriptException;
import exceptions.VehicleNotFoundException;
import exceptions.WrongAmountOfElementsException;
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
     * @return Command exit status.
     */
    @Override
    public boolean execute(String argument) {
        try {
            if (!argument.isEmpty()) throw new WrongAmountOfElementsException();
            if (collectionManager.collectionSize() == 0) throw new CollectionIsEmptyException();
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
            if (vehicleFromCollection == null) throw new VehicleNotFoundException();
            collectionManager.removeGreater(vehicleFromCollection);
            Console.println("vehicles успешно удалены!");
            return true;
        } catch (WrongAmountOfElementsException exception) {
            Console.println("использование: '" + getName() + "'");
        } catch (CollectionIsEmptyException exception) {
            Console.printerror("коллекция пуста!");
        } catch (VehicleNotFoundException exception) {
            Console.printerror("vehicle с такими характеристиками в коллекции нет!");
        } catch (IncorrectInputInScriptException exception) {}
        return false;
    }
}
