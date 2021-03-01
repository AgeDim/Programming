package data;

/**
 * Enumeration with vehicle's fuel type category constants.
 */
public enum FuelType {
    GASOLINE,
    ALCOHOL,
    NUCLEAR,
    PLASMA;
    /**
     * Generates a beautiful list of enum string values.
     * @return String with all enum values splitted by comma.
     */
    public static String nameList() {
        String nameList = "";
        for (FuelType fuelType : values()) {
            nameList += fuelType.name() + ", ";
        }
        return nameList.substring(0, nameList.length()-2);
    }
}
