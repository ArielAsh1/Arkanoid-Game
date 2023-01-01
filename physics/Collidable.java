// ID 208465096

package physics;
import drawables.Ball;
import geometry.Point;
import geometry.Rectangle;

/**
 * @author Ariel Ashkenazy
 * the physics.Collidable intersface.
 */
public interface Collidable {

    /**
     * gets the collision rectangle.
     * @return the collision rectangle.
     */
    Rectangle getCollisionRectangle();

    /**
     * calculates the new velocity of the ball after hitting a collidable
     * and notifies all listeners.
     * @param currentVelocity the current velocity of the ball.
     * @param collisionPoint the point of collision.
     * @param hitter the hitting object.
     * @return the updated velocity.
     */
    Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity);
}