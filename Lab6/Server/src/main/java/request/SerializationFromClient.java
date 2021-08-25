package request;

import data.Vehicle;

import java.io.Serializable;
import java.util.Stack;


public class SerializationFromClient implements Serializable {
    private static final long serialVersionUID = -1934063929652699131L;
    private String command;
    private String arg;
    private Vehicle vehicle;

    public SerializationFromClient(String command, String arg, Vehicle vehicle) {
        this.command = command;
        this.arg = arg;
        this.vehicle = vehicle;
    }

    public String getCommand() {
        return command;
    }

    public String getArg() {
        return arg;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }


}
