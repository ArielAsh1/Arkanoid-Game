// ID 208465096

package levels;
import drawables.Block;
import drawables.Sprite;
import physics.Velocity;
import java.util.List;

/**
 * @author Ariel Ashkenazy
 * this interface of level informatoin. holdes all the required info for creating a new level.
 */
public interface LevelInformation {
    /**
     * the number of balls in the level.
     * @return the number of balls (int).
     */
    int numberOfBalls();

    /**
     * a list that holds the initial velocity of each ball.
     * @return the list.
     */
    List<Velocity> initialBallVelocities();

    /**
     * the speed of the paddle.
     * @return the paddle speed.
     */
    int paddleSpeed();

    /**
     * the width of the paddle.
     * @return the paddle width.
     */
    int paddleWidth();

    /**
     * The name of the level. Displayed at the top of the screen.
     * @return the name.
     */
    String levelName();

    /**
     * gets the sprite with the background of the level.
     * @return the background.
     */
    Sprite getBackground();

    /**
     * a list of tll the blocks in the level.
     * @return the list of blocks.
     */
    List<Block> blocks();

    /**
     * Number of blocks that should be removed before the level is considered to be "cleared".
     * @return the number.
     */
    int numberOfBlocksToRemove();
}