package utility;

import data.Coordinates;
import data.FuelType;
import data.Vehicle;
import data.VehicleType;
import exceptions.IncorrectArgumentException;
import exceptions.IncorrectValueException;
import exceptions.NullFieldException;
import exceptions.WrongInputFormatException;

import java.time.LocalDateTime;


/**
 * This class is for creating new instances of Vehicle class
 */
public class VehicleFactory {
    private Integer id;
    private Console console;

    /**
     * @param startId - start point for id counter
     */
    public VehicleFactory(Integer startId) {
        this.id = startId;
    }

    public void setConsole(Console console) {
        this.console = console;
    }

    public Console getConsole() {
        return console;
    }

    /**
     * Creates new Vehicle with new id and creationDate
     *
     * @param name              - vehicle's name
     * @param coordinates       - vehicle's coordinates
     * @param enginePower       - vehicle's engine power
     * @param distanceTravelled - vehicle's distance travelled
     * @param vehicleType       - vehicle's vehicle type
     * @param type              - vehicle's fuel type
     * @return vehicle instance
     * @throws NullFieldException      if field is null, when is shouldn't be null
     * @throws IncorrectValueException - if value of the field contains wrong data, which is not allowed in this field
     */
    public Vehicle createVehicle(String name, Coordinates coordinates, LocalDateTime creationDate, Integer enginePower, int distanceTravelled, VehicleType vehicleType, FuelType type) throws NullFieldException, IncorrectValueException, IncorrectArgumentException {
        return createVehicleWithIdAndCreationDate(++id, name, coordinates, LocalDateTime.now(), enginePower, distanceTravelled, vehicleType, type);
    }

    /**
     * Create vehicle with given id and creationDate
     *
     * @param _id               vehicle's id
     * @param name              - vehicle's name
     * @param coordinates       - vehicle's coordinates
     * @param creationDate      - vehicle's creation date
     * @param enginePower       - vehicle's engine power
     * @param distanceTravelled - vehicle's distance travelled
     * @param vehicleType       - vehicle's vehicle type
     * @param type              - vehicle's fuel type
     * @return vehicle instance
     * @throws NullFieldException      if field is null, when is shouldn't be null
     * @throws IncorrectValueException - if value of the field contains wrong data, which is not allowed in this field
     */
    public Vehicle createVehicleWithIdAndCreationDate(Integer _id, String name, Coordinates coordinates, LocalDateTime creationDate, Integer enginePower, int distanceTravelled, VehicleType vehicleType, FuelType type) throws NullFieldException, IncorrectArgumentException {
        if (name == null || name.length() == 0) {
            throw new IncorrectArgumentException();
        }
        if (coordinates == null) {
            throw new NullFieldException("Coordinates");
        }

        if (enginePower <= 0) {
            throw new IncorrectArgumentException();
        }
        if (distanceTravelled < 0) {
            throw new IncorrectArgumentException();
        }

        return new Vehicle(_id, name, coordinates, LocalDateTime.now(), enginePower, distanceTravelled, vehicleType, type);
    }

    /**
     * read vehicle from console
     *
     * @return vehicle instance
     * @throws NullFieldException      if field is null, when is shouldn't be null
     * @throws IncorrectValueException - if value of the field contains wrong data, which is not allowed in this field
     */
    public Vehicle getVehicleFromConsole() throws IncorrectValueException, NullFieldException, IncorrectArgumentException, WrongInputFormatException {
        String name = null;
        LocalDateTime creationDate = null;
        Float x = null;
        Float y = null;
        Integer enginePower = null;
        Integer distanceTravelled = null;
        VehicleType vehicleType = null;
        FuelType type;

        FieldCheckerForConsole fieldCheckerForConsole = new FieldCheckerForConsole(console);
        while (name == null) {
            name = fieldCheckerForConsole.readAndCheckName();
        }
        while (x == null) {
            x = fieldCheckerForConsole.readAndCheckX();
        }
        while (y == null) {
            y = fieldCheckerForConsole.readAndCheckY();
        }
        while (creationDate == null) {
            creationDate = LocalDateTime.now();
        }
        while (enginePower == null) {
            enginePower = fieldCheckerForConsole.readAndCheckEnginePower();
        }
        while (distanceTravelled == null) {
            distanceTravelled = fieldCheckerForConsole.readAndCheckDisTravelled();
        }
        while (vehicleType == null) {
            vehicleType = fieldCheckerForConsole.readAndCheckVType();
        }
        type = fieldCheckerForConsole.readAndCheckFType();
        return createVehicle(name, new Coordinates(x, y), creationDate, enginePower, distanceTravelled, vehicleType, type);
    }

    public Vehicle getVehicleFromScript(String[] parameters) throws IncorrectArgumentException, WrongInputFormatException {
        String name;
        LocalDateTime creationDate = LocalDateTime.now();
        Float x;
        Float y;
        Integer enginePower;
        int distanceTravelled;
        VehicleType vehicleType;
        FuelType type;
        Vehicle tempVehicle = null;
        FieldCheckerForScript fieldCheckerForScript = new FieldCheckerForScript();
        try {
            name = fieldCheckerForScript.readAndCheckName(parameters[0]);
        } catch (NullFieldException | IncorrectValueException e) {
            System.out.println(e.getMessage());
            return null;
        }
        try {
            x = Float.valueOf(fieldCheckerForScript.readAndCheckX(parameters[1]));
        } catch (IncorrectValueException | NullFieldException e) {
            System.out.println(e.getMessage());
            return null;
        } catch (NumberFormatException e) {
            System.out.println("Coordinate X can't be parsed.");
            return null;
        }
        try {
            y = Float.valueOf(fieldCheckerForScript.readAndCheckY(parameters[2]));
        } catch (IncorrectValueException | NullFieldException e) {
            System.out.println(e.getMessage());
            return null;
        } catch (NumberFormatException e) {
            System.out.println("Coordinate Y can't be parsed.");
            return null;
        }
        try {
            enginePower = fieldCheckerForScript.readAndCheckEnginePower(parameters[3]);
        } catch (IncorrectValueException | NullFieldException e) {
            System.out.println(e.getMessage());
            return null;
        } catch (NumberFormatException e) {
            System.out.println("Engine power can't be parsed.");
            return null;
        }
        try {
            distanceTravelled = fieldCheckerForScript.readAndCheckDisTravelled(parameters[4]);
        } catch (IncorrectValueException | NullFieldException e) {
            System.out.println(e.getMessage());
            return null;
        } catch (NumberFormatException e) {
            System.out.println("Distance travelled can't be parsed.");
            return null;
        }
        try {
            vehicleType = fieldCheckerForScript.readAndCheckVType(parameters[5]);
        } catch (IncorrectValueException | NullFieldException e) {
            System.out.println(e.getMessage());
            return null;
        }

        try {
            type = fieldCheckerForScript.readAndCheckFType(parameters[6]);
        } catch (IncorrectValueException | NullFieldException e) {
            System.out.println(e.getMessage());
            return null;
        }
        try {
            tempVehicle = createVehicle(name, new Coordinates(x, y), creationDate, enginePower, distanceTravelled, vehicleType, type);
        } catch (NullFieldException | IncorrectValueException e) {
            System.out.println(e.getMessage());
        }
        return tempVehicle;
    }
}