package utility;

import data.*;

import java.io.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Stack;

/**
 * Operates the file for saving/loading collection.
 */
public class FileManager {
    private final String envVariable;

    public FileManager(String envVariable) {
        this.envVariable = envVariable;
    }

    /**
     * Writes collection to a file.
     *
     * @param collection Collection to write.
     */
    public void writeCollection(Collection<Vehicle> collection) {
        String output;
        try (PrintWriter pw = new PrintWriter(new FileOutputStream("vehicleCollection.csv"))) {
            for (Vehicle vehicle : collection) {
                output = "" + vehicle.getId() + "," + vehicle.getName() + "," + vehicle.getCoordinates().getX() + "," +
                        vehicle.getCoordinates().getY() + "," + vehicle.getCreationDate() + "," +
                        vehicle.getEnginePower() + "," + vehicle.getDistanceTravelled() + "," + vehicle.getType() +
                        "," + (vehicle.getFuelType() == null ? "" : vehicle.getFuelType()) + "\n";

                pw.write(output);
                pw.flush();
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found!)");
        }
    }

    /**
     * Reads collection from a file.
     *
     * @return vehicle1 readed collection.
     */
    public Stack<Vehicle> ReadFile() {
        Stack<Vehicle> vehicle1 = new Stack<>();
        Parser pars = new Parser();
        ArrayList<String> fileLines;
        File path = new File(System.getenv().get(envVariable));
        try {
            fileLines = pars.parseFromFile(path);
        } catch (FileNotFoundException e) {
            System.out.println("File not found! Try again");
            return new Stack<Vehicle>();
        } catch (IOException e) {
            System.out.println("Reading error!");
            return new Stack<Vehicle>();
        }

        ArrayList<String> columnList;
        for (String fileLine : fileLines) {
            columnList = pars.getItems(fileLine);
            int id = Integer.parseInt(columnList.get(0));
            String name = columnList.get(1);
            float cordX = Float.parseFloat(columnList.get(2));
            float cordY = Float.parseFloat(columnList.get(3));
            String creationDate = columnList.get(4);
            int enginePower = Integer.parseInt(columnList.get(5));
            int distanceTravelled = Integer.parseInt(columnList.get(6));
            VehicleType type = VehicleType.valueOf(columnList.get(7));
            FuelType fuelType = columnList.get(8).equals("") ? null : FuelType.valueOf(columnList.get(8).trim());
            float MAX_X = 252;
            if (cordX > MAX_X) {
                cordX = MAX_X;
                Console.printerror("координата X не может превышать " + MAX_X + "!");
            }

            float MAX_Y = 420;
            if (cordY > MAX_Y) {
                cordY = MAX_Y;
                Console.printerror("координата Y не может превышать " + MAX_Y + "!");
            }
            int MIN_distanceTravelled = 0;
            if (distanceTravelled < MIN_distanceTravelled) {
                distanceTravelled = 0;
                Console.printerror("Расстояние поездки не может быть меньше нуля!");
            }
            int MIN_enginePower = 0;
            if (enginePower < MIN_enginePower) {
                enginePower = MIN_enginePower;
                Console.printerror("Сила двигателя не может быть меньше нуля!");
            }
            vehicle1.add(new Vehicle(id, name, new Coordinates(cordX, cordY), LocalDateTime.parse(creationDate),
                    enginePower, distanceTravelled, type, fuelType));


        }

        return vehicle1;
    }

    @Override
    public String toString() {
        return "FileManager (класс для работы с загрузочным файлом)";
    }
}
