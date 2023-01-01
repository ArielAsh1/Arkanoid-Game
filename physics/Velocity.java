// ID 208465096

package physics;
import geometry.Point;

/**
 * @author Ariel Ashkenazy
 * this class represetns the physics.Velocity of an object.
 * the velocity specifies the change in position on the `x` and the `y` axes.
 */
public class Velocity {
    private double dx;
    private double dy;

    /**
     * constructor. velocity is the change in position on the `x` and the `y` axes.
     * @param dx the change on the x-axis.
     * @param dy the change on the y-axis.
     */
    public Velocity(double dx, double dy) {
        this.dx = dx;
        this.dy = dy;
    }

    /**
     * gets the dx of the velocity.
     * @return double, the value of dx.
     */
    public double getDx() {
        return this.dx;
    }

    /**
     * gets the dy of the velocity.
     * @return double, the value of dy.
     */
    public double getDy() {
        return this.dy;
    }

    /**
     * adds velocity (dx,dy) to a given point (x,y) and returns the new point.
     * @param p given point.
     * @return geometry.Point, new point that the velocity was added to.
     */
    public Point applyToPoint(Point p) {
        double x = p.getX();
        double y = p.getY();
        Point newPoint = new Point(x + dx, y + dy);
        return newPoint;
    }

    /**
     * gets the speed of the object.
     * @return the speed of the object.
     */
    public double getSpeed() {
        return Math.sqrt(dx * dx + dy * dy);
    }

    /**
     * creates a new velocity using given angle and speed.
     * @param angle is the direction in space to which the object is moving.
     * @param speed is the movement speed of the object.
     * @return physics.Velocity, the new velocity that was created.
     */
    public static Velocity fromAngleAndSpeed(double angle, double speed) {
        // converting Degrees to Radians and adjusting the x-y axis
        // (we assume up is angle 0).
        double radAngle = Math.toRadians(angle - 90);
        double dx = speed * Math.cos(radAngle);
        double dy = speed * Math.sin(radAngle);
        return new Velocity(dx, dy);
    }
}