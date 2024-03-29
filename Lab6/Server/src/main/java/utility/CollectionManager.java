package utility;

import data.Vehicle;

import java.time.ZonedDateTime;
import java.util.Comparator;
import java.util.Stack;


/**
 * This class is used to do all operations with collection
 */

public class CollectionManager {
    private Stack<Vehicle> vehicleCollection = new Stack<>();
    private boolean ExeDone;
    private final ZonedDateTime InitTime = ZonedDateTime.now();

    public CollectionManager() {
    }

    /**
     * Adds new vehicle to the collection
     *
     * @param vehicle vehicle instance to be add
     */
    public void add(Vehicle vehicle) {
        ExeDone = true;
        vehicleCollection.add(vehicle);
    }

    public Vehicle getVehicle() {
        Vehicle vehicle;
        vehicle = vehicleCollection.peek();
        return vehicle;
    }

    public Vehicle getFirst() {
        if (vehicleCollection.isEmpty()) return null;
        return vehicleCollection.firstElement();
    }

    public boolean removeFromCollection(Vehicle vehicle) {
        vehicleCollection.remove(vehicle);
        return true;
    }

    public boolean removeFromCollectionForStack(Stack<Vehicle> vehicles) {
        boolean temp = false;
        for (Vehicle vehicle : vehicles) {
            if (vehicleCollection.remove(vehicle)) {
                temp = true;
            }
        }
        return temp;
    }

    /**
     * @param name filtered vehicle.
     * @return compare name with string.
     */

    public Stack<Vehicle> vehicleFilteredInfo(String name) {
        return vehicleCollection.stream().filter(vehicle -> vehicle.getName().contains(name)).collect(Stack::new, Stack::add, Stack::addAll);
    }

    public Stack<Vehicle> getByEnginePower(Integer enginePower) {
        return vehicleCollection.stream().filter(vehicle -> vehicle.getEnginePower().equals(enginePower)).collect(Stack::new, Stack::add, Stack::addAll);
    }

    public void removeById(Integer id) {
        vehicleCollection.removeIf(vehicle -> vehicle.getId().equals(id));
    }

    public boolean removeGreater(Vehicle vehicleToCompare) {
        return vehicleCollection.removeIf(vehicle -> vehicle.getName().compareTo(vehicleToCompare.getName()) > 0);
    }

    public Stack<Vehicle> show() {
        return vehicleCollection.stream().sorted(Comparator.comparing(Vehicle::getCoordinates)).collect(Stack::new, Stack::add, Stack::addAll);
    }

    public void update(Integer id, Vehicle vehicle) {
        vehicleCollection.forEach(vehicle1 -> {
            if (vehicle1.getId().equals(id)) {
                vehicle1.setName(vehicle.getName());
                vehicle1.setCoordinates(vehicle.getCoordinates());
                vehicle1.setEnginePower(vehicle.getEnginePower());
                vehicle1.setDistanceTravelled(vehicle.getDistanceTravelled());
                vehicle1.setFuelType(vehicle.getFuelType());
                vehicle1.setType(vehicle.getType());
            }
        });
    }


    /**
     * Remove all elements from collection
     */
    public void clear() {
        ExeDone = true;
        vehicleCollection.clear();
    }

    /**
     * @return true if collection have unsaved changes
     */
    public boolean exeDone() {
        return ExeDone;
    }

    /**
     * @return string array with information about collection
     */
    public String getInfo() {
        String Type = "Type: Collection of vehicle's type objects\n";
        String Init = "Initialization time: " + InitTime.toString() + "\n";
        String Size = "Number of elements: " + vehicleCollection.size() + "\n";
        String State;
        if (exeDone()) {
            State = "Collection has been modified.";
        } else {
            State = "Collection hasn't been modified yet.";
        }
        return Type + Init + Size + State;
    }

    public Stack<Vehicle> minByDistanceTravelled() {
        Stack<Vehicle> stack = new Stack<>();
        stack.push(vehicleCollection.stream().min(Comparator.comparing(Vehicle::getDistanceTravelled)).get());
        return stack;
    }

    public Vehicle GetMax() {
        Vehicle maxVehicle = getFirst();
        for (Vehicle vehicle : vehicleCollection) {
            if (vehicle.compareTo(maxVehicle) > 0) {
                maxVehicle = vehicle;
            }
        }
        return maxVehicle;
    }

    /**
     * @return copy collection with vehicles
     */
    public Stack<Vehicle> getCollection() {
        return vehicleCollection;
    }

    public void loadCollection(String envVariable) {
        vehicleCollection = FileVehicle.ReadFile(envVariable);
    }

    public int collectionSize() {
        return vehicleCollection.size();
    }

    public Integer getLastId() {
        Integer lastId = 0;
        for (Vehicle v : vehicleCollection) {
            if (v.getId() > lastId) {
                lastId = v.getId();
            }
        }
        return lastId;
    }

}
