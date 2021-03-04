package utility;

import run.App;

import java.time.LocalDateTime;
import java.util.NoSuchElementException;
import java.util.Scanner;

import data.Coordinates;
import data.FuelType;
import data.VehicleType;
import exceptions.IncorrectInputInScriptException;
import exceptions.MustBeNotEmptyException;
import exceptions.NotInDeclaredLimitsException;

/**
 * Asks a user a vehicle's value.
 */
public class VehicleAsker {
    private final float MAX_Y = 421;
    private final float MAX_X = 252;
    private final int MIN_enginePower = 0;
    private final int MIN_distanceTravelled = 0;
    private Scanner userScanner;
    private boolean fileMode;

    public VehicleAsker(Scanner userScanner) {
        this.userScanner = userScanner;
        fileMode = false;
    }

    public VehicleAsker(int generateNextId, String askName, Coordinates askCoordinates,
                        LocalDateTime now, VehicleType askVehicleType, long askDistanceTravelled, double askEnginePower, FuelType askFuelType) {

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
     * @return Scanner, which uses for user input.
     */
    public Scanner getUserScanner() {
        return userScanner;
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
     * Asks a user the vehicle's name.
     *
     * @return Marine's name.
     * @throws IncorrectInputInScriptException If script is running and something goes wrong.
     */
    public String askName() throws IncorrectInputInScriptException {
        String name;
        while (true) {
            try {
                Console.println("Введите имя:");
                Console.print(App.PS2);
                name = userScanner.nextLine().trim();
                if (fileMode) Console.println(name);
                if (name.equals("")) throw new MustBeNotEmptyException();
                break;
            } catch (NoSuchElementException exception) {
                Console.printerror("имя не распознано!");
                if (fileMode) throw new IncorrectInputInScriptException();
            } catch (MustBeNotEmptyException exception) {
                Console.printerror("имя не может быть пустым!");
                if (fileMode) throw new IncorrectInputInScriptException();
            } catch (IllegalStateException exception) {
                Console.printerror("непредвиденная ошибка!");
                System.exit(0);
            }
        }
        return name;
    }

    /**
     * Asks a user the vehicle's X coordinate.
     *
     * @return Vehicle's X coordinate.
     * @throws IncorrectInputInScriptException If script is running and something goes wrong.
     */
    public double askX() throws IncorrectInputInScriptException {
        String strX;
        float x;
        while (true) {
            try {
                Console.println("введите координату X < " + (MAX_X + 1) + ":");
                Console.print(App.PS2);
                strX = userScanner.nextLine().trim();
                if (fileMode) Console.println(strX);
                x = Float.parseFloat(strX);
                if (x > MAX_X) throw new NotInDeclaredLimitsException();
                break;
            } catch (NoSuchElementException exception) {
                Console.printerror("координата X не распознана!");
                if (fileMode) throw new IncorrectInputInScriptException();
            } catch (NotInDeclaredLimitsException exception) {
                Console.printerror("координата X не может превышать " + MAX_X + "!");
            } catch (NumberFormatException exception) {
                Console.printerror("координата X должна быть представлена числом!");
                if (fileMode) throw new IncorrectInputInScriptException();
            }
        }
        return x;
    }

    /**
     * Asks a user the vehicle's Y coordinate.
     *
     * @return Vehicle's Y coordinate.
     * @throws IncorrectInputInScriptException If script is running and something goes wrong.
     */
    public Float askY() throws IncorrectInputInScriptException {
        String strY;
        float y;
        while (true) {
            try {
                Console.println("введите координату Y < " + (MAX_Y + 1) + ":");
                Console.print(App.PS2);
                strY = userScanner.nextLine().trim();
                if (fileMode) Console.println(strY);
                y = Float.parseFloat(strY);
                if (y > MAX_Y) throw new NotInDeclaredLimitsException();
                break;
            } catch (NoSuchElementException exception) {
                Console.printerror("координата Y не распознана!");
                if (fileMode) throw new IncorrectInputInScriptException();
            } catch (NotInDeclaredLimitsException exception) {
                Console.printerror("координата Y не может превышать " + MAX_Y + "!");
                if (fileMode) throw new IncorrectInputInScriptException();
            } catch (NumberFormatException exception) {
                Console.printerror("координата Y должна быть представлена числом!");
                if (fileMode) throw new IncorrectInputInScriptException();
            }
        }
        return y;
    }

    /**
     * Asks a user the vehicle's coordinates.
     *
     * @return Vehicle's coordinates.
     * @throws IncorrectInputInScriptException If script is running and something goes wrong.
     */
    public Coordinates askCoordinates() throws IncorrectInputInScriptException {
        float x;
        float y;
        x = (float) askX();
        y = askY();
        return new Coordinates(x, y);
    }

    /**
     * Asks a user the vehicle's health.
     *
     * @return Vehicle's engine power.
     * @throws IncorrectInputInScriptException If script is running and something goes wrong.
     */
    public int askEnginePower() throws IncorrectInputInScriptException {
        String strEnginePower;
        int enginePower;
        while (true) {
            try {
                Console.println("введите силу двигателя:");
                Console.print(App.PS2);
                strEnginePower = userScanner.nextLine().trim();
                if (fileMode) Console.println(strEnginePower);
                enginePower = Integer.parseInt(strEnginePower);
                if (enginePower < MIN_enginePower) throw new NotInDeclaredLimitsException();
                break;
            } catch (NoSuchElementException exception) {
                Console.printerror("сила двигателя не распознана!");
                if (fileMode) throw new IncorrectInputInScriptException();
            } catch (NotInDeclaredLimitsException exception) {
                Console.printerror("сила двигателя должна быть больше нуля!");
                if (fileMode) throw new IncorrectInputInScriptException();
            } catch (NumberFormatException exception) {
                Console.printerror("сила двигателя должна быть числом!");
                if (fileMode) throw new IncorrectInputInScriptException();
            }
        }
        return enginePower;
    }

    /**
     * Asks a user the vehicle's category.
     *
     * @return Vehicle's fuel type.
     * @throws IncorrectInputInScriptException If script is running and something goes wrong.
     */
    public FuelType askFuelType() throws IncorrectInputInScriptException {
        String strFuelType;
        FuelType fuelType;
        while (true) {
            try {
                Console.println("список категорий - " + FuelType.nameList());
                Console.println("введите категорию используемого топлива:");
                Console.print(App.PS2);
                strFuelType = userScanner.nextLine().trim();
                if (fileMode) Console.println(strFuelType);
                fuelType = FuelType.valueOf(strFuelType.toUpperCase());
                break;
            } catch (NoSuchElementException exception) {
                Console.printerror("категория не распознана!");
                if (fileMode) throw new IncorrectInputInScriptException();
            } catch (IllegalArgumentException exception) {
                Console.printerror("категории нет в списке!");
                if (fileMode) throw new IncorrectInputInScriptException();
            } catch (IllegalStateException exception) {
                Console.printerror("непредвиденная ошибка!");
                System.exit(0);
            }
        }
        return fuelType;
    }

    /**
     * Asks a user the vehicle's type.
     *
     * @return Vehicle's type.
     * @throws IncorrectInputInScriptException If script is running and something goes wrong.
     */
    public VehicleType askVehicleType() throws IncorrectInputInScriptException {
        String strVehicleType;
        VehicleType type;
        while (true) {
            try {
                Console.println("список категорий - " + VehicleType.nameList());
                Console.println("введите категорию средства передвижения:");
                Console.print(App.PS2);
                strVehicleType = userScanner.nextLine().trim();
                if (fileMode) Console.println(strVehicleType);
                type = VehicleType.valueOf(strVehicleType.toUpperCase());
                break;
            } catch (NoSuchElementException exception) {
                Console.printerror("категория не распознана!");
                if (fileMode) throw new IncorrectInputInScriptException();
            } catch (IllegalArgumentException exception) {
                Console.printerror("категории нет в списке!");
                if (fileMode) throw new IncorrectInputInScriptException();
            } catch (IllegalStateException exception) {
                Console.printerror("непредвиденная ошибка!");
                System.exit(0);
            }
        }
        return type;
    }

    /**
     * Asks a user the vehicle's distance of travelled.
     *
     * @return Distance travelled.
     * @throws IncorrectInputInScriptException If script is running and something goes wrong.
     */
    public int askDistanceTravelled() throws IncorrectInputInScriptException {
        String strDistanceTravelled;
        int distanceTravelled;
        while (true) {
            try {
                Console.println("введите дистанцию путешествия > " + (MIN_distanceTravelled) + ":");
                Console.print(App.PS2);
                strDistanceTravelled = userScanner.nextLine().trim();
                if (fileMode) Console.println(strDistanceTravelled);
                distanceTravelled = Integer.parseInt(strDistanceTravelled);
                if (distanceTravelled < MIN_distanceTravelled) throw new NotInDeclaredLimitsException();
                break;
            } catch (NoSuchElementException exception) {
                Console.printerror("дистанция не распознанна!");
                if (fileMode) throw new IncorrectInputInScriptException();
            } catch (NotInDeclaredLimitsException exception) {
                Console.printerror("дистанция должна быть положительным числом и не меньше " + MIN_distanceTravelled + " !");
                if (fileMode) throw new IncorrectInputInScriptException();
            } catch (NumberFormatException exception) {
                Console.printerror("дистанция должна быть числом!");
                if (fileMode) throw new IncorrectInputInScriptException();
            }
        }
        return distanceTravelled;
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

    @Override
    public String toString() {
        return "Vehicle (вспомогательный класс для запросов пользователю)";
    }
}
