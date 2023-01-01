// ID 208465096

package listeners;
import drawables.Ball;
import drawables.Block;
import settings.Counter;
import settings.GameLevel;

/**
 * @author Ariel Ashkenazy
 * this class is in charge of removing blocks from the game.
 * and keeping count of the remaining blocks.
 */
public class BlockRemover implements HitListener {
    private GameLevel game;
    private Counter remainingBlocks;

    /**
     * constructor.
     * @param game our game.
     * @param remainingBlocks number of remaining blocks.
     */
    public BlockRemover(GameLevel game, Counter remainingBlocks) {
        this.game = game;
        this.remainingBlocks = remainingBlocks;
    }

    /**
     * removes blocks that are hit from the game and their listener.
     * updates remaining blocks count accordingly.
     * @param beingHit the object that is being hit.
     * @param hitter the hitting object.
     */
    public void hitEvent(Block beingHit, Ball hitter) {
        beingHit.removeFromGame(game);
        beingHit.removeHitListener(this); //removes from listeners list
        remainingBlocks.decrease(1);
    }
}