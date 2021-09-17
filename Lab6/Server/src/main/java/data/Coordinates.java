package data;

import java.io.Serializable;

/**
 * X-Y coordinates.
 */


public class Coordinates implements Comparable<Coordinates>, Serializable {
    private static final long serialVersionUID = -1934063929652699131L;
    private Float x;
    private Float y;


    public Coordinates(Float x, Float y) {
        this.x = x;
        this.y = y;
    }

    /**
     * @return X-coordinate.
     */
    public Float getX() {
        return x;
    }

    /**
     * @return Y-coordinate.
     */
    public Float getY() {
        return y;
    }

    public void setX(Float x) {
        this.x = x;
    }

    public void setY(Float y) {
        this.y = y;
    }

    private float dist0(Coordinates coordinates) {
        return (float) Math.sqrt(Math.pow(coordinates.getX(),2) + Math.pow(coordinates.getY(),2));
    }

    @Override
    public String toString() {
        return "X:" + x + " Y:" + y;
    }

    @Override
    public int hashCode() {
        return Integer.parseInt(String.valueOf(x)) + Integer.parseInt(String.valueOf(y));
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj instanceof Coordinates) {
            Coordinates coordinatesObj = (Coordinates) obj;
            return (x == coordinatesObj.getX()) && (y == coordinatesObj.getY());
        }
        return false;
    }

    @Override
    public int compareTo(Coordinates o) {
        double dist = dist0(this) - dist0(o);
        if (dist > 0) return 1;
        else if (dist ==0) return 0;
        else return -1;
    }
}
