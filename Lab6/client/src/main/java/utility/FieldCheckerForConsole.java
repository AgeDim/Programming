package utility;

import data.FuelType;
import data.VehicleType;
import exceptions.IncorrectValueException;
import exceptions.NullFieldException;
import exceptions.WrongInputFormatException;

public class FieldCheckerForConsole {
    private final Console console;

    public FieldCheckerForConsole(Console console) {
        this.console = console;
    }

    public <T> T readAndCheckField(String question, String errorMessage, FieldCheckerHelp<T> rule) {
        T result = null;
        while (true) {
            System.out.print(question);
            String data = console.readln();
            try {
                result = rule.check(data);
            } catch (NumberFormatException | WrongInputFormatException e) {
                System.out.println(errorMessage);
                continue;
            } catch (IncorrectValueException | NullFieldException e) {
                System.out.println(errorMessage);
            }
            return result;
        }
    }

    public String readAndCheckName() {

        FieldCheckerHelp<String> interfc = (str) -> {
            if(str == null){throw new WrongInputFormatException();}
            if (str.equals("") || str.equals("\n")) {
                throw new WrongInputFormatException();
            } else {
                return str;
            }
        };

        return readAndCheckField("Input name: ", "Wrong name! Try again: ", interfc);
    }

    /**
     * Method asks to input X coordinate.
     *
     * @return vehicle's X coordinate.
     */
    public Integer readAndCheckX() {
        FieldCheckerHelp<Integer> interfc = (str) -> {
            int res = Integer.parseInt(str);
            if (res > 252)
                throw new WrongInputFormatException();
            return res;
        };
        return readAndCheckField("input X coordinate: (x <= 252) ", "Wrong Coordinate! Try again: ", interfc);
    }

    /**
     * Method asks to input Y coordinate.
     *
     * @return vehicle's Y coordinate.
     */
    public Integer readAndCheckY() {
        FieldCheckerHelp<Integer> interfc = (str) -> {
            int res = Integer.parseInt(str);
            if (res > 420) {
                throw new WrongInputFormatException();
            }
            return res;
        };
        return readAndCheckField("Input Y coordinate: (y <= 420) ", "Wrong Coordinate! Try again: ", interfc);
    }

    /**
     * Method asks to input vehicle's Engine Power.
     *
     * @return engine power of the vehicle.
     */
    public int readAndCheckEnginePower() {
        FieldCheckerHelp<Integer> interfc = (str) -> {
            int res = Integer.parseInt(str);
            if (0 >= res) {
                throw new WrongInputFormatException();

            }
            return res;
        };
        return readAndCheckField("Input engine power", "Wrong Power Format! Try again: ", interfc);
    }

    /**
     * Method asks to input vehicle's distance travel.
     *
     * @return distance travel of the vehicle.
     */
    public int readAndCheckDisTravelled() {
        FieldCheckerHelp<Integer> iterfc = (str) -> {
            int span = Integer.parseInt(str);
            if (span <= 0) {
                throw new WrongInputFormatException();
            }
            return span;
        };

        return readAndCheckField("Input distance travelled: ", "Wrong distance! Try again: ", iterfc);
    }

    /**
     * Method asks to input vehicle's type.
     *
     * @return type of vehicle.
     */
    public VehicleType readAndCheckVType() throws WrongInputFormatException {
        FieldCheckerHelp<VehicleType> interfc = (str) -> {
            if(str == null){throw new WrongInputFormatException();}
            if (str.toUpperCase().matches("HELICOPTER|DRONE|CHOPPER|SPACESHIP")) {
                return VehicleType.valueOf(str.toUpperCase());
            } else {
                throw new WrongInputFormatException();
            }
        };
        return readAndCheckField("Enter the vehicle type: (HELICOPTER,DRONE,CHOPPER,SPACESHIP) ",
                "Wrong type format! Try again: ", interfc);
    }

    /**
     * Method asks to input vehicle's fuel type.
     *
     * @return fuel type of vehicle.
     */
    public FuelType readAndCheckFType() throws WrongInputFormatException {
        FieldCheckerHelp<FuelType> interfc = (str) -> {
            if (str == null)  {
                return null;
            } else if (str.toUpperCase().matches("GASOLINE|ALCOHOL|NUCLEAR|PLASMA")){
                return FuelType.valueOf(str.toUpperCase());
            } else {
                throw new WrongInputFormatException();
            }
        };
        return readAndCheckField("Enter the fuel type: (GASOLINE,ALCOHOL,NUCLEAR,PLASMA) ",
                "Wrong type format! Try again: ", interfc);
    }}