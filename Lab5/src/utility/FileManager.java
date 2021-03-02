package utility;

import java.io.FileOutputStream;
import java.io.File;
import java.io.PrintWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.io.FileNotFoundException;
import java.util.Collection;
import java.util.NoSuchElementException;
import java.util.Stack;
import data.*;
import exceptions.IncorrectInputInScriptException;
import exceptions.NotInDeclaredLimitsException;
import run.App;

/**
 * Operates the file for saving/loading collection.
 */
public class FileManager {
    private String envVariable;
    private final float MAX_Y = 420;
    private final int MIN_enginePower = 0;
    private final int MIN_distanceTravelled = 0;
    private final float MAX_X = 252;
    public FileManager(String envVariable) {
        this.envVariable = envVariable;
    }

    /**
     * Writes collection to a file.
     * @param collection Collection to write.
     */
    public void writeCollection(Collection<Vehicle> collection) {
                String output;
        try (PrintWriter pw = new PrintWriter(new FileOutputStream(new File("vehicleCollection.csv")))) {
            for (Vehicle vehicle : collection) {
                output = "" + vehicle.getId() + "," + vehicle.getName() + "," + vehicle.getCoordinates().getX() + "," +
                        vehicle.getCoordinates().getY() + "," + vehicle.getCreationDate() + "," +
                        vehicle.getEnginePower() + "," + vehicle.getDistanceTravelled() + "," + vehicle.getType() +
                        "," + (vehicle.getFuelType() == null ? "" : vehicle.getFuelType());

                pw.write(output);
                pw.flush();
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found!)");
        }
    }
    /**
     * Reads collection from a file.
     * @return vehicle1 readed collection.
     */
    public Stack<Vehicle> ReadFile() {
        Stack<Vehicle> vehicle1 = new Stack<>();
        Parser pars = new Parser();
        ArrayList<String> fileLines;
        File path = new File(System.getenv().get(envVariable));
        try {
            fileLines = pars.parseFromFile(path);
        } catch(FileNotFoundException e) {
            System.out.println("File not found! Try again");
            return new Stack<Vehicle>();
        } catch(IOException e) {
            System.out.println("Reading error!");
            return new Stack<Vehicle>();
        }

        ArrayList<String> columnList= new ArrayList<>();
        for (String fileLine : fileLines) {
            columnList = pars.getItems(fileLine);
            Integer id = Integer.parseInt(columnList.get(0));
            String name = columnList.get(1);
            float cordX = Float.parseFloat(columnList.get(2));
            float cordY = Float.parseFloat(columnList.get(3));
            String creationDate = columnList.get(4);
            Integer enginePower = Integer.parseInt(columnList.get(5));
            int distanceTravelled = Integer.parseInt(columnList.get(6));
            VehicleType type = VehicleType.valueOf(columnList.get(7));
            FuelType fuelType = columnList.get(8).equals("") ? null : FuelType.valueOf(columnList.get(8).trim());
            while (true) {
                try {
                    if (cordX > MAX_X) {cordX = MAX_X;throw new NotInDeclaredLimitsException();
                    }
                    break;
                }
                catch (NotInDeclaredLimitsException exception) {
                    Console.printerror("координата X не может превышать " + MAX_X + "!");
                    break;
                }}
            while (true) {
                try {
                    if (cordY > MAX_Y){ cordY = MAX_Y;throw new NotInDeclaredLimitsException();}
                    break;
                }
                catch (NotInDeclaredLimitsException exception) {
                    Console.printerror("координата Y не может превышать " + MAX_Y + "!");
                    break;
                }}
            while (true) {
                try {
                    if (distanceTravelled < MIN_distanceTravelled) {distanceTravelled = 0;throw new NotInDeclaredLimitsException();
                    }
                    break;
                }
                catch (NotInDeclaredLimitsException exception) {
                    Console.printerror("Расстояние поездки не может быть меньше нуля!");
                    break;
                }}
            while (true) {
                try {
                    if (enginePower < MIN_enginePower) {enginePower = MIN_enginePower;throw new NotInDeclaredLimitsException();
                    }
                    break;
                }
                catch (NotInDeclaredLimitsException exception) {
                    Console.printerror("Сила двигателя не может быть меньше нуля!");
                    break;
                }}


            vehicle1.add( new Vehicle(id, name, new Coordinates(cordX, cordY), LocalDateTime.parse(creationDate),
                    enginePower, distanceTravelled, type, fuelType));


        }
        return vehicle1;
    }

    @Override
    public String toString() {
        String string = "FileManager (класс для работы с загрузочным файлом)";
        return string;
    }
}