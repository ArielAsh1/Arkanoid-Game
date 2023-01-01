// ID 208465096

package listeners;
import drawables.Ball;
import drawables.Block;
import settings.Counter;

/**
 * @author Ariel Ashkenazy
 * this class is in charge of the game score.
 */
public class ScoreTrackingListener implements HitListener {
    private Counter currentScore;

    /**
     * constructor.
     * @param scoreCounter the score.
     */
    public ScoreTrackingListener(Counter scoreCounter) {
        this.currentScore = scoreCounter;
    }

    /**
     * updates the score when a hit occurs.
     * @param beingHit the object that is being hit.
     * @param hitter the hitting object.
     */
    public void hitEvent(Block beingHit, Ball hitter) {
        currentScore.increase(5);
    }
}