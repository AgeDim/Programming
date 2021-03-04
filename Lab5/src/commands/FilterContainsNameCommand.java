package commands;

import utility.CollectionManager;
import utility.Console;

/**
 * Command 'filter_by_name'. Filters the collection by name.
 */
public class FilterContainsNameCommand extends AbstractCommand {
    private CollectionManager collectionManager;

    public FilterContainsNameCommand(CollectionManager collectionManager) {
        super("filter_by_name <name>", "вывести элементы, значение поля name которых содержит заданную подстроку");
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
            Console.println("использование: '" + getName() + "'");
            return false;
        }
        if (collectionManager.collectionSize() == 0) {
            Console.printerror("Коллекция пуста!");
            return false;
        } else {
            String filteredInfo = collectionManager.vehicleFilteredInfo(argument);
            if (!filteredInfo.isEmpty()) {
                Console.println(filteredInfo);
                return true;
            } else {
                Console.println("В коллекции нет vehicle с заданным именем!");
                return false;
            }
        }
    }
}