package commands;


import data.Vehicle;
import utility.CollectionManager;
import utility.Console;

/**
 * Command 'remove_by_EnPow'. Removes the element by its Engine Power.
 */
public class RemoveByEnPower extends AbstractCommand {
    private final CollectionManager collectionManager;

    public RemoveByEnPower(CollectionManager collectionManager) {
        super("remove_by_EnPow <enginePower>", "remove element for engine power");
        this.collectionManager = collectionManager;
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
                Console.printerror("Collections is empty!");
                return false;
            }
            int enginePower = Integer.parseInt(argument);
            Vehicle vehicleToRemove1 = collectionManager.getByEnginePower(enginePower);
            if (vehicleToRemove1 == null) {
                Console.printerror("Vehicle with this engine power not found in collection!");
                return false;
            }
            collectionManager.removeFromCollection(vehicleToRemove1);

            Console.println("Vehicle successfully remove!");
            return true;
        } catch (NumberFormatException exception) {
            Console.printerror("Engine power must be a number!");
        }
        return false;
    }
}
