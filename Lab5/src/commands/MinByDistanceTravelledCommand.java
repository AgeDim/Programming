
 package commands;

import exceptions.CollectionIsEmptyException;
import exceptions.WrongAmountOfElementsException;
import utility.CollectionManager;
import utility.Console;

    /**
     * Command 'min_by_DistanceTravelled'. Prints element with low argument of distance travelled.
     */
    public class MinByDistanceTravelledCommand extends AbstractCommand {
        private CollectionManager collectionManager;

        public MinByDistanceTravelledCommand(CollectionManager collectionManager) {
            super("minByDistanceTravelled", "вывести элемент, значение поля Distance Travelled которого минимально");
            this.collectionManager = collectionManager;
        }

        /**
         * Executes the command.
         * @return Command exit status.
         */
        @Override
        public boolean execute(String argument) {
            try {
                if (!argument.isEmpty()) {Console.println("использование: '" + getName() + "'");return false;}
                Console.println(collectionManager.minByDistanceTravelled());
                return true;
            } catch (CollectionIsEmptyException exception) {
                Console.printerror("Коллекция пуста!");
            }
            return true;
        }
    }