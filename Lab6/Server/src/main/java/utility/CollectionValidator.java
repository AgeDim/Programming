package utility;

import data.FuelType;
import data.Vehicle;
import data.VehicleType;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Stack;


public class CollectionValidator {
    private final CollectionManager collectionManager;

    public CollectionValidator(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    public boolean validateObject(Stack<Vehicle> collection, Vehicle vehicle) {
        boolean tempName;
        boolean tempCoordinates;
        boolean tempEngPower;
        boolean tempDisTravelled;
        boolean tempFType;
        boolean tempVType;
        if (!collection.isEmpty()) {
            for (Vehicle v : collection) {
                if (v.getId().equals(vehicle.getId())) {
                    return false;
                }
            }
            if (!vehicle.getName().equals("") && vehicle.getName() != null) {
                tempName = true;
            } else tempName = false;
            if (vehicle.getCoordinates().getX() <= 253) {
                if (vehicle.getCoordinates().getY() <= 422) {
                    tempCoordinates = true;
                } else tempCoordinates = false;
            } else tempCoordinates = false;
            if (vehicle.getEnginePower() > 0) {
                tempEngPower = true;
            } else tempEngPower = false;
            if (vehicle.getDistanceTravelled() > 0) {
                tempDisTravelled = true;
            } else tempDisTravelled = false;
            tempFType = checkExistFType(String.valueOf(vehicle.getFuelType()));
            tempVType = checkExistVType(String.valueOf(vehicle.getType()));

            return tempName && tempCoordinates && tempEngPower && tempDisTravelled && tempFType && tempVType;
        }
        return true;
    }


    private boolean checkExistVType(String type) {
        return Arrays.stream(VehicleType.values()).anyMatch(x -> x.name().equals(type));
    }

    private boolean checkExistFType(String fuelType) {
        return Arrays.stream(FuelType.values()).anyMatch(x -> x.name().equals(fuelType));
    }

    public boolean checkExistId(Integer id) {
        for (Vehicle vehicle : collectionManager.getCollection()) {
            if (vehicle.getId().equals(id)) {
                return true;
            }
        }
        return false;
    }
}
