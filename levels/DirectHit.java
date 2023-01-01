// ID 208465096

package levels;
import biuoop.DrawSurface;
import drawables.Block;
import drawables.Sprite;
import geometry.Point;
import geometry.Rectangle;
import physics.Velocity;
import settings.GameLevel;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Ariel Ashkenazy
 * this class represent the Direct Hit level.
 */
public class DirectHit implements LevelInformation {
    @Override
    public int numberOfBalls() {
        return 1;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        List<Velocity> velocities = new ArrayList<>();
        velocities.add(Velocity.fromAngleAndSpeed(0, 5));
        return velocities;
    }

    @Override
    public int paddleSpeed() {
        return 5;
    }

    @Override
    public int paddleWidth() {
        return 100;
    }

    @Override
    public String levelName() {
        return "Direct Hit";
    }

    @Override
    public Sprite getBackground() {
        return new Sprite() {
            private final Color backgroundColor = new Color(0, 0, 0);
            @Override
            public void drawOn(DrawSurface d) {
                d.setColor(backgroundColor);
                d.fillRectangle(0, 0, GameLevel.WIDTH, GameLevel.HEIGHT);
            }

            @Override
            public void timePassed() {

            }
        };
    }

    @Override
    public List<Block> blocks() {
        final int size = GameLevel.CONST * 2;
        List<Block> blocksList = new ArrayList<>();
        Point point = new Point((GameLevel.WIDTH - size) / 2.0, 150);
        Rectangle rect = new Rectangle(point, size, size);
        Block block = new Block(rect);
        block.setBlockColor(Color.RED);
        blocksList.add(block);
        return blocksList;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return 1;
    }
}