package utility;

import data.Coordinates;
import data.FuelType;
import data.VehicleType;
import exceptions.IncorrectInputInScriptException;
import exceptions.NotInDeclaredLimitsException;
import exceptions.WrongInputFormatException;
import run.App;

import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * Class {@code Input} defines methods to work with an input from various sources.
 */

public class VehicleAsker {
    private Scanner userScanner;
    private boolean fileMode;

    public VehicleAsker(Scanner userScanner) {
        this.userScanner = userScanner;
        fileMode = false;
    }

    public <T> T input(String question, String errorMessage, Func<T> rule) {
        T result;
        while (true) {
            System.out.print(question);
            String data = userScanner.nextLine();
            try {
                result = rule.Func(data);
            } catch (NumberFormatException | WrongInputFormatException e) {
                System.out.println(errorMessage);
                continue;
            }
            return result;

        }
    }

    /**
     * @return Scanner, which uses for user input.
     */
    public Scanner getUserScanner() {
        return userScanner;
    }

    /**
     * Sets a scanner to scan user input.
     *
     * @param userScanner Scanner to set.
     */
    public void setUserScanner(Scanner userScanner) {
        this.userScanner = userScanner;
    }

    /**
     * Sets vehicle asker mode to 'File Mode'.
     */
    public void setFileMode() {
        fileMode = true;
    }

    /**
     * Sets vehicle asker mode to 'User Mode'.
     */
    public void setUserMode() {
        fileMode = false;
    }

    /**
     * /**
     * Method asks to input name of vehicle.
     *
     * @return name of the vehicle.
     */
    public String inputVehicleName() {

        Func<String> interfc = (str) -> {
            if (str.equals("") || str.equals("\n")) {
                throw new WrongInputFormatException();
            } else {
                return str;
            }
        };

        return input("Введите имя: ", "Wrong name! Try again: ", interfc);
    }

    /**
     * Method asks to input X coordinate.
     *
     * @return vehicle's X coordinate.
     */
    public Float inputX() {
        Func<Float> interfc = (str) -> {
            float res = Float.parseFloat(str);
            if (res > 252)
                throw new WrongInputFormatException();
            return res;
        };
        return input("Введите координату X: (x <= 252) ", "Wrong Coordinate! Try again: ", interfc);
    }

    /**
     * Method asks to input Y coordinate.
     *
     * @return vehicle's Y coordinate.
     */
    public Float inputY() {
        Func<Float> interfc = (str) -> {
            float res = Float.parseFloat(str);
            if (res > 420) {
                throw new WrongInputFormatException();
            }
            return res;
        };
        return input("Введите координату Y: (y <= 420) ", "Wrong Coordinate! Try again: ", interfc);
    }

    /**
     * Method asks to input coordinates.
     *
     * @return vehicle's coordinates.
     */
    public Coordinates getCoordinates() {
        return new Coordinates(inputX(), inputY());
    }

    /**
     * Method asks to input vehicle's Engine Power.
     *
     * @return engine power of the vehicle.
     */
    public int inputEnPower() {
        Func<Integer> interfc = (str) -> {
            int res = Integer.parseInt(str);
            if (0 >= res) {
                throw new WrongInputFormatException();

            }
            return res;
        };
        return input("Введите силу двигателя ", "Wrong Power Format! Try again: ", interfc);
    }

    /**
     * Method asks to input vehicle's distance travel.
     *
     * @return distance travel of the vehicle.
     */
    public int inputDistTravel() {
        Func<Integer> iterfc = (str) -> {
            int span = Integer.parseInt(str);
            if (span <= 0) {
                throw new WrongInputFormatException();
            }
            return span;
        };

        return input("Введите дистанцию путешествия: ", "Wrong distance! Try again: ", iterfc);
    }

    /**
     * Method asks to input vehicle's type.
     *
     * @return type of vehicle.
     */
    public VehicleType inputVType() throws WrongInputFormatException {
        Func<VehicleType> interfc = (str) -> {
            if (str.toUpperCase().matches("HELICOPTER|DRONE|CHOPPER|SPACESHIP")) {
                return VehicleType.valueOf(str.toUpperCase());
            } else {
                throw new WrongInputFormatException();
            }
        };
        return input("Enter the vehicle type: (HELICOPTER,DRONE,CHOPPER,SPACESHIP) ",
                "Wrong type format! Try again: ", interfc);
    }

    /**
     * Method asks to input vehicle's fuel type.
     *
     * @return fuel type of vehicle.
     */
    public FuelType inputFType() throws WrongInputFormatException {
        Func<FuelType> interfc = (str) -> {
            if (str.toUpperCase().matches("GASOLINE|ALCOHOL|NUCLEAR|PLASMA|NULL")) {
                return FuelType.valueOf(str.toUpperCase());
            } else {
                throw new WrongInputFormatException();
            }
        };
        return input("Enter the fuel type: (GASOLINE,ALCOHOL,NUCLEAR,PLASMA) ",
                "Wrong type format! Try again: ", interfc);
    }

    /**
     * Finds and returns the next complete token from this input stream.
     *
     * @return the next token
     */
    public String next() {
        return userScanner.next();
    }

    /**
     * Asks a user a question.
     *
     * @param question A question.
     * @return Answer (true/false).
     * @throws IncorrectInputInScriptException If script is running and something goes wrong.
     */
    public boolean askQuestion(String question) throws IncorrectInputInScriptException {
        String finalQuestion = question + " (+/-):";
        String answer;
        while (true) {
            try {
                Console.println(finalQuestion);
                Console.print(App.PS2);
                answer = userScanner.nextLine().trim();
                if (fileMode) Console.println(answer);
                if (!answer.equals("+") && !answer.equals("-")) throw new NotInDeclaredLimitsException();
                break;
            } catch (NoSuchElementException exception) {
                Console.printerror("ответ не распознан!");
                if (fileMode) throw new IncorrectInputInScriptException();
            } catch (NotInDeclaredLimitsException exception) {
                Console.printerror("ответ должен быть представлен знаками '+' или '-'!");
                if (fileMode) throw new IncorrectInputInScriptException();
            }
        }
        return answer.equals("+");
    }
}