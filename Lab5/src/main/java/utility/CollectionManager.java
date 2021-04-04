package utility;

import data.Vehicle;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Comparator;
import java.util.Stack;


/**
 * Operates the collection itself.
 */
public class CollectionManager {
    private final FileManager fileManager;
    private Stack<Vehicle> vehicleCollection = new Stack<>();
    private LocalDateTime lastInitTime;
    private LocalDateTime lastSaveTime;


    public CollectionManager(FileManager fileManager) {
        this.lastInitTime = null;
        this.lastSaveTime = null;
        this.fileManager = fileManager;
        loadCollection();
    }

    /**
     * @return The collecton itself.
     */
    public Stack<Vehicle> getCollection() {
        return vehicleCollection;
    }

    /**
     * @return Last initialization time or null if there wasn't initialization.
     */
    public LocalDateTime getLastInitTime() {
        return lastInitTime;
    }

    /**
     * @return Last save time or null if there wasn't saving.
     */
    public LocalDateTime getLastSaveTime() {
        return lastSaveTime;
    }

    /**
     * @return Name of the collection's type.
     */
    public String collectionType() {
        return vehicleCollection.getClass().getName();
    }

    /**
     * @return Size of the collection.
     */
    public int collectionSize() {
        return vehicleCollection.size();
    }

    /**
     * @return The first element of the collection or null if collection is empty.
     */
    public Vehicle getFirst() {
        if (vehicleCollection.isEmpty()) return null;
        return vehicleCollection.firstElement();
    }

    /**
     * @param id ID of the vehicle.
     * @return A vehicle by his ID or null if vehicle isn't found.
     */
    public Vehicle getById(int id) {
        for (Vehicle vehicle : vehicleCollection) {
            if (vehicle.getId().equals(id)) return vehicle;
        }
        return null;
    }

    /**
     * @param enginePower EnginePower of the vehicle.
     * @return A vehicle by his enginePower or null if marine isn't found.
     */
    public Vehicle getByEnginePower(Integer enginePower) {
        for (Vehicle vehicle : vehicleCollection) {
            if (vehicle.getEnginePower().equals(enginePower)) return vehicle;
        }
        return null;
    }
    /**
     * @return Vehicle, who has max EnginePower.
     */
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
     * @return Vehicle, who has min distance travelled.
     */
    public Vehicle minByDistanceTravelled() {
        return Collections.min(vehicleCollection, Comparator.comparingInt(Vehicle::getDistanceTravelled));
    }

    /**
     * @param name filtered vehicle.
     * @return compare name with string.
     */

    public String vehicleFilteredInfo(String name) {
        StringBuilder info = new StringBuilder();
        for (Vehicle vehicle : vehicleCollection) {
            if (vehicle.getName().contains(name)) {
                info.append(vehicle).append("\n\n");
            }
        }
        return info.toString().trim();
    }

    /**
     * Adds a new vehicle to collection.
     *
     * @param vehicle to add.
     */
    public void addToCollection(Vehicle vehicle) {
        vehicleCollection.add(vehicle);
    }

    /**
     * Removes a new vehicle to collection.
     *
     * @param vehicle A vehicle to remove.
     */
    public void removeFromCollection(Vehicle vehicle) {
        vehicleCollection.remove(vehicle);
    }

    /**
     * Remove vehicles greater than the selected one.
     *
     * @param vehicleToCompare A vehicle to compare with.
     */
    public boolean removeGreater(Vehicle vehicleToCompare) {
        return vehicleCollection.removeIf(vehicle -> vehicle.getName().compareTo(vehicleToCompare.getName()) > 0);
    }

    /**
     * Clears the collection.
     */
    public void clearCollection() {
        vehicleCollection.clear();
    }

    /**
     * Generates next ID. It will be (the bigger one + 1).
     *
     * @return Next ID.
     */
    public int generateNextId() {
        if (vehicleCollection.isEmpty()) return 1;
        return vehicleCollection.lastElement().getId() + 1;
    }

    /**
     * Saves the collection to file.
     */
    public void saveCollection() {
        fileManager.writeCollection(vehicleCollection);
        lastSaveTime = LocalDateTime.now();
    }

    /**
     * Loads the collection from file.
     */
    private void loadCollection() {
        vehicleCollection = fileManager.ReadFile();
        lastInitTime = LocalDateTime.now();
    }

    @Override
    public String toString() {
        if (vehicleCollection.isEmpty()) return "Collection is empty!";
        StringBuilder info = new StringBuilder();
        for (Vehicle vehicle : vehicleCollection) {
            info.append(vehicle);
            if (vehicle != vehicleCollection.lastElement()) info.append("\n\n");
        }
        return info.toString();
    }
}
