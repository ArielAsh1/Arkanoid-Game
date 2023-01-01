// ID 208465096

package geometry;
import java.util.List;

/**
 * @author Ariel Ashkenazy
 * a line segment in space.
 * the class can create different lines and preform some functions on them.
 * each line connects two points - a start point (x,y) and an end point (x,y).
 * both the start point and the end point are private.
 *
 */
public class Line {
    private Point start;
    private Point end;

    /**
     * constructor. creates a new line from 2 given points.
     * @param start the start point of the line.
     * @param end the end point of the line.
     */
    public Line(Point start, Point end) {
        this.start = start;
        this.end = end;
    }

    /**
     * constructor. creates a new line using 4 given doubles, which are the coordinates of two different points.
     * @param x1 x coordinate of the start point.
     * @param y1 y coordinate of the start point.
     * @param x2 x coordinate of the end point.
     * @param y2 y coordinate of the end point.
     */
    public Line(double x1, double y1, double x2, double y2) {
        this.start = new Point(x1, y1);
        this.end = new Point(x2, y2);
    }

    /**
     * gets the length of the line by calculating the distance between it's start point and end point.
     * @return double, the length of the line.
     */
    public double length() {
        return start.distance(end);
    }

    /**
     * finds the (x,y) coordinates of the middle point of the line.
     * @return point, the middle point of the line.
     */
    public Point middle() {
        double xAvg = (start.getX() + end.getX()) / 2;
        double yAvg = (start.getY() + end.getY()) / 2;
        return new Point(xAvg, yAvg);
    }

    /**
     * gets the (x,y) coordinates of the start point of the line.
     * @return point, the start point of the line.
     */
    public Point start() {
        return start;
    }

    /**
     * gets the (x,y) coordinates of the end point of the line.
     * @return point, the end point of the line.
     */
    public Point end() {
        return end;
    }
    /**
     * checks if two lines have one or more intersection points.
     * @param other the other line we check intersection with.
     * @return boolean true if the lines intersect at least once, false otherwise.
     */
    public boolean isIntersecting(Line other) {
        return this.intersectionWith(other) != null || !noIntersection(other);
        // !noIntersection(other) will return true if the lines intersect 1 or more times
    }

    /**
     * checks if a given point is inside the line.
     * @param p the point that we want to check.
     * @return boolean true if the point is on the line, false otherwise.
     */
    public boolean pointInsideLine(Point p) {
        return Util.areTheSame(length(), start.distance(p) + p.distance(end));
    }

    /**
     * checks if two lines have an intersection point.
     * @param other the other line we check intersection with.
     * @return boolean true if no intersection between the lines, false if there is.
     */
    public boolean noIntersection(Line other) {
        return !this.pointInsideLine(other.start) && !this.pointInsideLine(other.end)
                && !other.pointInsideLine(this.start) && !other.pointInsideLine(this.end);
    }

    /**
     * finds the intersection point between two lines with equal slopes.
     * @param other the other line we check intersection with.
     * @return point if the lines have a single intersection point, null otherwise.
     */
    public Point equalSlopeIntersection(Line other) {
        // if one line is completely inside the other
        // 'other' is inside 'this'
        if (this.pointInsideLine(other.start) && this.pointInsideLine(other.end)) {
            return null;
        }
        // 'this' is inside 'other'
        if (other.pointInsideLine(this.start) && other.pointInsideLine(this.end)) {
            return null;
        }
        // if the two lines don't share any mutual points
        if (noIntersection(other)) {
            return null;
        }
        // if the lines have the same edge points
        if (this.start.equals(other.start) || this.start.equals(other.end)) {
            return this.start;
        }
        if (this.end.equals(other.start) || this.end.equals(other.end)) {
            return this.end;
        }
        // if lines have more than one intersection point
        return null;
    }

    /**
     * finds the intersection point between two line segments.
     * @param other the other line we want to find intersection with.
     * @return point if the two lines intersect, and null if they don't.
     */
    public Point intersectionWith(Line other) {
        if (other == null) { // input check
            return null;
        }
        // gets slopes, start and end points for both lines.
        double m1 = this.getSlope();
        double m2 = other.getSlope();
        double x1 = this.end.getX();
        double y1 = this.end.getY();
        double x2 = other.end.getX();
        double y2 = other.end.getY();
        double x, y; // (x,y) will be the final coordinates of the intersection point.

        // when at least one line is actually a single point:
        if (this.start.equals(this.end) && other.start.equals(other.end)
                && this.start.equals(other.start)) {
            // so both lines are single points and they are the same point
            return this.start;
        } else if (this.start.equals(this.end) && other.start.equals(other.end)
                && !this.start.equals(other.start)) {
            // so both are points but no intersection
            return null;
        } else if (this.start.equals(this.end) && other.pointInsideLine(this.start)) {
            // so the first line is a single point and it's inside the other line
            return this.start;
        } else if (this.start.equals(this.end) && !other.pointInsideLine(this.start)) {
            return null;
            // so the first line is a single point but no intersection
        } else if (other.start.equals(other.end) && this.pointInsideLine(other.start)) {
            // so the other line is a point and there is intersection
            return other.start;
        } else if (other.start.equals(other.end) && !this.pointInsideLine(other.start)) {
            // so the other line is a point but no intersection
            return null;
        }

        // from here we deal only with actual line segments
        if (Util.areTheSame(this.start.getX(), this.end.getX())
                && Util.areTheSame(other.start.getX(), other.end.getX())) {
            // so both lines are vertical to y axis (slopes are undefined)
            return equalSlopeIntersection(other);
        } else if (Util.areTheSame(this.start.getX(), this.end.getX())) {
            // so 'this' is vertical to y axis and 'other' is not
            x = x1;
            y = m2 * (x1 - x2) + y2;
        } else if (Util.areTheSame(other.start.getX(), other.end.getX())) {
            // so 'other' is vertical to y axis and 'this' is not
            x = x2;
            y = m1 * (x2 - x1) + y1;
        } else if (Util.areTheSame(m1, m2)) {
            // so the slopes are equal
            return equalSlopeIntersection(other);
        } else { // m1 != m2
            // finds (x,y) of a theoretical intersection point
            x = (-x2 * m2 + y2 + x1 * m1 - y1) / (m1 - m2);
            y = m1 * (x - x1) + y1;
        }

        Point ip = new Point(x, y);
        // checks if the ip is located on both line segments,
        // to make sure it is indeed a valid intersection point:
        if (other.pointInsideLine(ip) && this.pointInsideLine(ip))  {
            return ip;
        }
        // so not a valid intersection point.
        return null;
    }

    /**
     * checks if two lines are equal, and returns true or false accordingly.
     * two lines are equal if they have the same start and end point.
     * @param other the other line data.
     * @return boolean, true if the two lines are equal, and false otherwise.
     */
    public boolean equals(Line other) {
        if (other == null) { // input check
            return false;
        }
        return (this.start.equals(other.start) && this.end.equals(other.end))
                || (this.start.equals(other.end) && this.end.equals(other.start));
    }

    /**
     * finds the slope of a given line.
     * @return double, the slope of the line.
     */
    public double getSlope() {
        return (end.getY() - start.getY()) / (end.getX() - start.getX());
    }

    /**
     * finds the closest intersection point of the given rectangle and the start of the line.
     * if they don't intersect we return null.
     * @param rect the given rectangle.
     * @return the closest intersection geometry.Point if they intersect, null otherwise.
     */
    public Point closestIntersectionToStartOfLine(Rectangle rect) {
        if (rect == null) {
            return null;
        }
        List<Point> intersectionPointsList = rect.intersectionPoints(this);
        if (intersectionPointsList.size() == 1) {
          // so there is only one intersection point, thus it's also the closest one
          return intersectionPointsList.get(0);
        } else if (intersectionPointsList.size() == 2) {
            // so we need to find the closest intersection point between the two
            double d1 = intersectionPointsList.get(0).distance(this.start);
            double d2 = intersectionPointsList.get(1).distance(this.start);
            if (d1 > d2) {
                return intersectionPointsList.get(1);
            } else {
                return intersectionPointsList.get(0);
            }
        }
        // if the line and the rectangle don't intersect (intersectionPointList is empty)
        return null;
    }
}