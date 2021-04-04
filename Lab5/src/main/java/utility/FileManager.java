package utility;

import data.Coordinates;
import data.FuelType;
import data.Vehicle;
import data.VehicleType;

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
        try (PrintWriter pw = new PrintWriter(new FileOutputStream(System.getenv(envVariable)))) {
            for (Vehicle vehicle : collection) {
                output = "\"" + vehicle.getId() + "\",\"" + vehicle.getName().replace("\"", "\"\"") + "\",\"" + vehicle.getCoordinates().getX()
                        + "\",\"" + vehicle.getCoordinates().getY() + "\",\"" + vehicle.getCreationDate() + "\",\"" +
                        vehicle.getEnginePower() + "\",\"" + vehicle.getDistanceTravelled() + "\",\"" + vehicle.getType() +
                        "\",\"" + (vehicle.getFuelType() == null ? "" : vehicle.getFuelType()) + "\"\n";

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
        ArrayList<ArrayList<String>> fileLines;
        try {
            fileLines = pars.parseCSV(new FileReader(System.getenv(envVariable)));
        } catch (FileNotFoundException e) {
            System.out.println("File not found! Try again");
            return new Stack<Vehicle>();
        } catch (IOException e) {
            System.out.println("Reading error!");
            return new Stack<Vehicle>();
        }
        for (ArrayList<String> fileLine : fileLines) {
            int id = Integer.parseInt(fileLine.get(0));
            String name = fileLine.get(1);
            float cordX = Float.parseFloat(fileLine.get(2));
            float cordY = Float.parseFloat(fileLine.get(3));
            String creationDate = fileLine.get(4);
            int enginePower = Integer.parseInt(fileLine.get(5));
            int distanceTravelled = Integer.parseInt(fileLine.get(6));
            VehicleType type = VehicleType.valueOf(fileLine.get(7));
            FuelType fuelType = fileLine.get(8).equals("") ? null : FuelType.valueOf(fileLine.get(8).trim());
            float MAX_X = 252;
            if (cordX > MAX_X) {
                cordX = MAX_X;
                Console.printerror("Coordinate x can't be more than " + MAX_X + "!");
            }

            float MAX_Y = 420;
            if (cordY > MAX_Y) {
                cordY = MAX_Y;
                Console.printerror("Coordinate y can't be more than " + MAX_Y + "!");
            }
            int MIN_distanceTravelled = 0;
            if (distanceTravelled < MIN_distanceTravelled) {
                distanceTravelled = 0;
                Console.printerror("travelled distance can't be less than zero!");
            }
            int MIN_enginePower = 0;
            if (enginePower < MIN_enginePower) {
                enginePower = MIN_enginePower;
                Console.printerror("Engine power can't be less than zero!");
            }
            vehicle1.add(new Vehicle(id, name, new Coordinates(cordX, cordY), LocalDateTime.parse(creationDate),
                    enginePower, distanceTravelled, type, fuelType));


        }

        return vehicle1;
    }

    @Override
    public String toString() {
        return "FileManager (class for processing file)";


    }
}
