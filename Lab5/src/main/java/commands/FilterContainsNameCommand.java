package commands;

import utility.CollectionManager;
import utility.Console;

/**
 * Command 'filter_by_name'. Filters the collection by name.
 */
public class FilterContainsNameCommand extends AbstractCommand {
    private final CollectionManager collectionManager;

    public FilterContainsNameCommand(CollectionManager collectionManager) {
        super("filter_by_name <name>", "output element, values of name have this line");
        this.collectionManager = collectionManager;
    }

    /**
     * Executes the command.
     *
     * @return Command exit status.
     */
    @Override
    public boolean execute(String argument) {
        if (argument.isEmpty()) {
            Console.println("using: '" + getName() + "'");
            return false;
        }
        if (collectionManager.collectionSize() == 0) {
            Console.printerror("collection is empty!");
            return false;
        } else {
            String filteredInfo = collectionManager.vehicleFilteredInfo(argument);
            if (!filteredInfo.isEmpty()) {
                Console.println(filteredInfo);
                return true;
            } else {
                Console.println("In collection not found vehicle with this line!!");
                return false;
            }
        }
    }
}