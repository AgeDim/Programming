package data;

import java.io.Serializable;

/**
 * X-Y coordinates.
 */


public class Coordinates implements Serializable {
    private static final long serialVersionUID = -1934063929652699131L;
    private float x;
    private float y;


    public Coordinates(float x, float y) {
        this.x = x;
        this.y = y;
    }

    /**
     * @return X-coordinate.
     */
    public float getX() {
        return x;
    }

    /**
     * @return Y-coordinate.
     */
    public float getY() {
        return y;
    }

    public void setX(float x) {
        this.x = x;
    }

    public void setY(float y) {
        this.y = y;
    }

    @Override
    public String toString() {
        return "X:" + x + " Y:" + y;
    }

    @Override
    public int hashCode() {
        return (int) y + (int) x;
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
}
