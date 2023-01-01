// ID 208465096

package animations;
import biuoop.DrawSurface;

/**
 * @author Ariel Ashkenazy
 * this animation interface.
 */
public interface Animation {

    /**
     * does one frame.
     * @param d the game drawSurface.
     */
    void doOneFrame(DrawSurface d);

    /**
     * in charge of stopping the game.
     * the game is over when no more balls (loss) or no more levels (win).
     * @return boolean true of false.
     */
    boolean shouldStop();
}