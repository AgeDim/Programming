package commands;


import data.Vehicle;
import exceptions.VehicleNotFoundException;
import exceptions.WrongAmountOfElementsException;
import exceptions.CollectionIsEmptyException;
import utility.CollectionManager;
import utility.Console;

/**
 * Command 'remove_by_EnPow'. Removes the element by its Engine Power.
 */
public class RemoveByEnPower extends AbstractCommand {
    private CollectionManager collectionManager;

    public RemoveByEnPower(CollectionManager collectionManager) {
        super("remove_by_EnPow <enginePower>", "удалить элемент из коллекции по engine power");
        this.collectionManager = collectionManager;
    }

    /**
     * Executes the command.
     * @return Command exit status.
     */
    @Override
    public boolean execute(String argument) {
        try {
            if (argument.isEmpty()) throw new WrongAmountOfElementsException();
            if (collectionManager.collectionSize() == 0) throw new CollectionIsEmptyException();
            int enginePower = Integer.parseInt(argument);
            Vehicle vehicleToRemove = collectionManager.getByEnginePower(enginePower);
            if (vehicleToRemove == null) throw new VehicleNotFoundException();
            collectionManager.removeFromCollection(vehicleToRemove);
            Console.println("Vehicle успешно удален!");
            return true;
        } catch (WrongAmountOfElementsException exception) {
            Console.println("использование: '" + getName() + "'");
        } catch (CollectionIsEmptyException exception) {
            Console.printerror("коллекция пуста!");
        } catch (NumberFormatException exception) {
            Console.printerror("Engine power должен быть представлен числом!");
        } catch (VehicleNotFoundException exception) {
            Console.printerror("Vehicle с таким engine power в коллекции нет!");
        }
        return false;
    }
}
