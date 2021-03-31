package commands;

import utility.CollectionManager;
import utility.Console;

/**
 * Command 'min_by_DistanceTravelled'. Prints element with low argument of distance travelled.
 */
public class MinByDistanceTravelledCommand extends AbstractCommand {
    private final CollectionManager collectionManager;

    public MinByDistanceTravelledCommand(CollectionManager collectionManager) {
        super("minByDistanceTravelled", "input element, value of Distance Travelled is least");
        this.collectionManager = collectionManager;
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
        } else {
            Console.println(collectionManager.minByDistanceTravelled());
            return true;
        }

    }
}