// ID 208465096

package settings;
import geometry.Line;
import geometry.Point;
import physics.Collidable;
import physics.CollisionInfo;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Ariel Ashkenazy
 * this class is in charge of holding all the collidables that are in the game.
 */
public class GameEnvironment {
    // a List collection of all the collidables in the environment
    private List<Collidable> collidablesList;

    /**
     * constructor. creates a list of Collidables.
     */
    public GameEnvironment() {
        this.collidablesList = new ArrayList<>();
    }

    /**
     * adds the given collidable to the game environment.
     * @param c the collidable we add to the game environment.
     */
    public void addCollidable(Collidable c) {
        collidablesList.add(c);
    }

    /**
     * removes the given collidable from the game environment.
     * @param c the collidable we remove.
     */
    public void removeCollidable(Collidable c) {
        collidablesList.remove(c);
    }

    /**
     * finds the closest collision information (point and object).
     * @param trajectory a line, the trajectory of the ball.
     * @return the closest collision info if there is one, null otherwise.
     */
    public CollisionInfo getClosestCollision(Line trajectory) {
        CollisionInfo collisionInfo = null; // stays null if there's no intersection point
        double minDist = Double.MAX_VALUE;
        // going through all the collidables in our game environment
        for (Collidable c : collidablesList) {
            Point closestCollision = trajectory.closestIntersectionToStartOfLine(c.getCollisionRectangle());
            // if there is a collision
            if (closestCollision != null) {
                double distance = closestCollision.distance(trajectory.start());
                if (distance < minDist) {
                    minDist = distance;
                    collisionInfo = new CollisionInfo(closestCollision, c);
                }
            }
        }
        return collisionInfo;
    }
}