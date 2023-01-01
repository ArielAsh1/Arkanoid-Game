// ID 208465096

package listeners;
import drawables.Ball;
import drawables.Block;

/**
 * @author Ariel Ashkenazy
 * the interface listeners.HitListener.
 */
public interface HitListener {

    /**
     * This method is called whenever the beingHit object is hit.
     * @param beingHit the object that is being hit.
     * @param hitter the hitting object.
     */
    void hitEvent(Block beingHit, Ball hitter);
}