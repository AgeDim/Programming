package data;

/**
 * Enumeration with Vehicle category constants.
 */
public enum VehicleType {
    HELICOPTER,
    DRONE,
    CHOPPER,
    SPACESHIP;

    /**
     * Generates a beautiful list of enum string values.
     * @return String with all enum values splitted by comma.
     */
    public static String nameList() {
        String nameList = "";
        for (VehicleType type : values()) {
            nameList += type.name() + ", ";
        }
        return nameList.substring(0, nameList.length()-2);
    }
}
