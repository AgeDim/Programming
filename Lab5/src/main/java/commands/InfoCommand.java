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
        super("info", "output information about collection");
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
        String lastInitTimeString = (lastInitTime == null) ? "in this session initialization not found" :
                lastInitTime.toLocalDate().toString() + " " + lastInitTime.toLocalTime().toString();

        LocalDateTime lastSaveTime = collectionManager.getLastSaveTime();
        String lastSaveTimeString = (lastSaveTime == null) ? "in this session initialization not found" :
                lastSaveTime.toLocalDate().toString() + " " + lastSaveTime.toLocalTime().toString();

        Console.println("information about collection:");
        Console.println("type: " + collectionManager.collectionType());
        Console.println(" amount of elements: " + collectionManager.collectionSize());
        Console.println(" last save date: " + lastSaveTimeString);
        Console.println(" last initialization date: " + lastInitTimeString);
        return true;
    }
}
