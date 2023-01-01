// ID 208465096

package physics;
import geometry.Point;

/**
 * @author Ariel Ashkenazy
 * simply unites the collision point and object under the same class.
 */
public class CollisionInfo {

    private Point pointOfCollision;
    private Collidable objectOfCollision;

    /**
     * constructor. creates new collision info using a given point and object.
     * @param pointOfCollision the collision point.
     * @param objectOfCollision the collision object.
     */
    public CollisionInfo(Point pointOfCollision, Collidable objectOfCollision) {
        this.pointOfCollision = pointOfCollision;
        this.objectOfCollision = objectOfCollision;
    }

    /**
     * gets the point at which the collision occurs.
     * @return the collision point.
     */
    public Point collisionPoint() {
        return this.pointOfCollision;
    }

    /**
     * gets the collidable object that is involved in the collision.
     * @return the collision object.
     */
    public Collidable collisionObject() {
        return this.objectOfCollision;
    }
}