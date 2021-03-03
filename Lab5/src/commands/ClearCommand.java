package commands;

import exceptions.WrongAmountOfElementsException;
import utility.CollectionManager;
import utility.Console;

/**
 * Command 'clear'. Clears the collection.
 */
public class ClearCommand extends AbstractCommand {
    private CollectionManager collectionManager;

    public ClearCommand(CollectionManager collectionManager) {
        super("clear", "очистить коллекцию");
        this.collectionManager = collectionManager;
    }

    /**
     * Executes the command.
     * @return Command exit status.
     */
    @Override
    public boolean execute(String argument) {
            if (!argument.isEmpty()) {Console.println("использование: '" + getName() + "'");return false;}
            else {
                collectionManager.clearCollection();
                Console.println("коллекция очищена!");
                return true;
            }

}}
