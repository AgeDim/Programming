package run;

import java.util.Scanner;
import commands.*;
import utility.CollectionManager;
import utility.CommandManager;
import utility.Console;
import utility.FileManager;
import utility.VehicleAsker;
/**
 * Main application class. Creates all instances and runs the program.
 * @author Ageev Dmitriy p3131 date now 28.02.2021 21:18
 */
public class App {
    public static final String PS1 = "$ ";
    public static final String PS2 = "> ";

    public static void main(String[] args) {
        try (Scanner userScanner = new Scanner(System.in)) {
            final String envVariable = "LABA";

            VehicleAsker vehicleAsker = new VehicleAsker(userScanner);
            FileManager fileManager = new FileManager(envVariable);
            CollectionManager collectionManager = new CollectionManager(fileManager);
            CommandManager commandManager = new CommandManager(
                new HelpCommand(),
                new InfoCommand(collectionManager),
                new ShowCommand(collectionManager),
                new AddCommand(collectionManager, vehicleAsker),
                new UpdateCommand(collectionManager, vehicleAsker),
                new RemoveByIdCommand(collectionManager),
                new ClearCommand(collectionManager),
                new SaveCommand(collectionManager),
                new ExitCommand(),
                new ExecuteScriptCommand(),
                new AddIfMaxCommand(collectionManager, vehicleAsker),
                new RemoveGreaterCommand(collectionManager, vehicleAsker),
                new RemoveFirstCommand(collectionManager),
                new RemoveByEnPower(collectionManager),
                new MinByDistanceTravelledCommand(collectionManager),
                new FilterContainsNameCommand(collectionManager)
            );
            Console console = new Console(commandManager, userScanner, vehicleAsker);

            console.interactiveMode();
        }

    }
}