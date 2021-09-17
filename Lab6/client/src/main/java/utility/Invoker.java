package utility;

import clientcommands.*;
import exceptions.IncorrectArgumentException;
import exceptions.UnknownCommandException;
import exceptions.ValidationException;

import java.io.BufferedReader;
import java.net.SocketAddress;
import java.nio.channels.DatagramChannel;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;


/**
 * This class contains map with commands which can be execute
 */
public class Invoker {
    private final HashMap<String, Command> command;
    private boolean isStopRequested = false;
    private final Object allowedToStop = ExitCommand.class;
    private HashSet<String> filePaths = new HashSet<>();
    private Deque<BufferedReader> readers = new ArrayDeque<>();

    public Invoker() {
        command = new HashMap<>();
        filePaths.clear();
    }

    /**
     * Initialize commands map
     */
    public void initMap(DatagramChannel datagramChannel, SocketAddress socketAddress, Console console, Invoker invoker) {
        command.put("info", new InfoCommand(datagramChannel, socketAddress, console, invoker));
        command.put("show", new ShowCommand(datagramChannel, socketAddress, console, invoker));
        command.put("add", new AddCommand(datagramChannel, socketAddress, console, invoker));
        command.put("update", new UpdateCommand(datagramChannel, socketAddress, console, invoker));
        command.put("remove_by_id", new RemoveByIdCommand(datagramChannel, socketAddress, console, invoker));
        command.put("clear", new ClearCommand(datagramChannel, socketAddress, console, invoker));
        command.put("add_if_max", new AddIfMaxCommand(datagramChannel, socketAddress, console, invoker));
        command.put("remove_greater", new RemoveGreaterCommand(datagramChannel, socketAddress, console, invoker));
        command.put("remove_first", new RemoveFirstCommand(datagramChannel, socketAddress, console, invoker));
        command.put("remove_by_engine_power", new RemoveByEnPower(datagramChannel, socketAddress, console, invoker));
        command.put("min_by_distance", new MinByDistanceTravelledCommand(datagramChannel, socketAddress, console, invoker));
        command.put("filter_by_name", new FilterContainsNameCommand(datagramChannel, socketAddress, console, invoker));
        command.put("execute_script", new ExecuteScriptCommand(datagramChannel, socketAddress, console, invoker));
        command.put("help", new HelpCommand(command, datagramChannel, socketAddress, console, invoker));
        command.put("exit", new ExitCommand(invoker));
    }

    public void exe(String name, String arg) throws UnknownCommandException,IncorrectArgumentException, ValidationException{
        if (command.containsKey(name)) {
            command.get(name).execute(arg);
        } else {
            throw new UnknownCommandException("Unknown command. Please, try again.");
        }
    }

    public boolean isStopRequested() {
        return isStopRequested;
    }

    /**
     * Request stop of the program
     *
     * @param requester - is true, when program stops
     */
    public void requestExit(Object requester) {
        if (requester.getClass().equals(allowedToStop)) {
            isStopRequested = true;
            System.out.println("Client has been stopped.");
            System.exit(0);
        }
    }

    public HashSet<String> getFilePaths(){
        return filePaths;
    }
    public void addPath(String path){
        filePaths.add(path);
    }
    public void deletePath(String path){
        filePaths.remove(path);
    }

    public void addBufferedReader(BufferedReader bufferedReader) {
        readers.push(bufferedReader);
    }

    public BufferedReader getBufferedReader() {
        return readers.pop();
    }

    public String writeBuffers(){
        return readers.toString();
    }

    public Deque<BufferedReader> getReaders(){
        return readers;
    }
}
