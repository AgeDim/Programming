package commands;

import java.time.LocalDateTime;

import utility.CollectionManager;
import utility.Console;

/**
 * Command 'info'. Prints information about the collection.
 */
public class InfoCommand extends AbstractCommand {
    private final CollectionManager collectionManager;

    public InfoCommand(CollectionManager collectionManager) {
        super("info", "вывести информацию о коллекции");
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
            Console.println("использование: '" + getName() + "'");
            return false;
        }
        LocalDateTime lastInitTime = collectionManager.getLastInitTime();
        String lastInitTimeString = (lastInitTime == null) ? "в данной сессии инициализации еще не происходило" :
                lastInitTime.toLocalDate().toString() + " " + lastInitTime.toLocalTime().toString();

        LocalDateTime lastSaveTime = collectionManager.getLastSaveTime();
        String lastSaveTimeString = (lastSaveTime == null) ? "в данной сессии сохранения еще не происходило" :
                lastSaveTime.toLocalDate().toString() + " " + lastSaveTime.toLocalTime().toString();

        Console.println("сведения о коллекции:");
        Console.println("тип: " + collectionManager.collectionType());
        Console.println(" количество элементов: " + collectionManager.collectionSize());
        Console.println(" дата последнего сохранения: " + lastSaveTimeString);
        Console.println(" дата последней инициализации: " + lastInitTimeString);
        return true;
    }
}
