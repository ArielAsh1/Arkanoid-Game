// ID 208465096

package listeners;
import drawables.Ball;
import drawables.Block;
import settings.Counter;
import settings.GameLevel;

/**
 * @author Ariel Ashkenazy
 * this class is in charge of removing balls from the game.
 * and keeping count of the remaining balls.
 */
public class BallRemover implements HitListener {
    private GameLevel game;
    private Counter remainingBalls;

    /**
     * constructor.
     * @param game the game.
     * @param remainingBalls number of remaining balls.
     */
    public BallRemover(GameLevel game, Counter remainingBalls) {
        this.game = game;
        this.remainingBalls = remainingBalls;
    }

    /**
     * removes balls the hit the "ground" from the game
     * and updates remaining balls count accordingly.
     * @param beingHit the object the is being hit.
     * @param hitter the Sprites.drawables.Ball that's doing the hitting.
     */
    public void hitEvent(Block beingHit, Ball hitter) {
        hitter.removeFromGame(game);
        remainingBalls.decrease(1);
    }
}