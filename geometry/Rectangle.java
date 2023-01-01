// ID 208465096

package geometry;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Ariel Ashkenazy
 * this class represents a rectangle.
 * a geometry.Rectangle is constructed with a point, width and height.
 */
public class Rectangle {

    private Point upperLeft;
    private Point upperRight;
    private Point lowerLeft;
    private Point lowerRight;
    private double width;
    private double height;

    /**
     * constructor. Creates a new rectangle using given location, width and height.
     * @param upperLeft the upper left point of the rectangle.
     * @param width the width of the rectangle.
     * @param height the height of the rectangle.
     */
    public Rectangle(Point upperLeft, double width, double height) {
        this.width = width;
        this.height = height;
        this.upperLeft = upperLeft;
        this.upperRight = new Point(upperLeft.getX() + width, upperLeft.getY());
        this.lowerLeft = new Point(upperLeft.getX(), upperLeft.getY() + height);
        this.lowerRight = new Point(upperLeft.getX() + width, upperLeft.getY() + height);
    }

    /**
    * creates a List of the intersection points with a given line.
    * if no intersection points were found the list will return empty.
    * @param line the given line we check intersection with.
    * @return List<geometry.Point>, a list of all the intersection points.
    */
    public List<Point> intersectionPoints(Line line) {
        // creates a List collection to hold the intersection Points
        List<Point> intersectionPointsList = new ArrayList<>();
        // creates 4 lines from a the rectangle
        Line leftLine = new Line(lowerLeft, upperLeft);
        Line rightLine = new Line(lowerRight, upperRight);
        Line bottomLine = new Line(lowerLeft, lowerRight);
        Line topLine = new Line(upperLeft, upperRight);

        // finding the intersection points and adding them to the list.
        // the max possible list size is 2.
        if (line.intersectionWith(leftLine) != null) {
            intersectionPointsList.add(line.intersectionWith(leftLine));
        }
        if (line.intersectionWith(rightLine) != null) {
            intersectionPointsList.add(line.intersectionWith(rightLine));
        }
        if (line.intersectionWith(bottomLine) != null) {
            intersectionPointsList.add(line.intersectionWith(bottomLine));
        }
        if (line.intersectionWith(topLine) != null) {
            intersectionPointsList.add(line.intersectionWith(topLine));
        }
        return intersectionPointsList;
    }

    /**
     * gets the width of the rectangle.
     * @return the rectangle's width as a double.
     */
    public double getWidth() {
        return this.width;
    }

    /**
     * gets the height of the rectangle.
     * @return the rectangle's height as a double.
     */
    public double getHeight() {
        return this.height;
    }

    /**
     * gets the upper left point of the rectangle.
     * @return the rectangle's upper left point.
     */
    public Point getUpperLeft() {
       return this.upperLeft;
    }

    /**
     * gets the upper right point of the rectangle.
     * @return the rectangle's upper right point.
     */
    public Point getUpperRight() {
        return this.upperRight;
    }

    /**
     * gets the lower left point of the rectangle.
     * @return the rectangle's lower left point.
     */
    public Point getLowerLeft() {
        return this.lowerLeft;
    }
}