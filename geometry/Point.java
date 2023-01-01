// ID 208465096

package geometry;

/**
 * @author Ariel Ashkenazy
 * A point (x,y) in space.
 * this class has basic points methods - measure distance between points,
 * and check if one point is equal to another.
 */
public class Point {
    private double x;
    private double y;

    /**
     * @param x x coordinate of the point in space
     * @param y y coordinate of the point in space
     */
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * finds the distance between two points and returns it.
     * @param other the other geometry.Point data, that the method gets the distance to.
     * @return the distance between this point to the other point.
     */
    public double distance(Point other) {
        if (other == null) {
            return 0;
        }
        return Math.sqrt(Math.pow(this.x - other.x, 2) + Math.pow(this.y - other.y, 2));
    }

    /**
     * checks if two points are equal, and returns true or false accordingly.
     * two points are equal if they have the same (x,y) coordinates in space.
     * @param other the other point data.
     * @return true if the two points are equal, and false otherwise.
     */
    public boolean equals(Point other) {
        if (other == null) {
            return false;
        }
        return Util.areTheSame(this.x, other.x) && Util.areTheSame(this.y, other.y);
    }

    /**
     * gets the x coordinate of the point.
     * @return double, the x coordinate.
     */
    public double getX() {
        return x;
    }

    /**
     * gets the y coordinate of the point.
     * @return double, the y coordinate.
     */
    public double getY() {
        return y;
    }
}
