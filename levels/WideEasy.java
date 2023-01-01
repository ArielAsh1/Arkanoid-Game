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
 * this class represent the Wide Easy level.
 */
public class WideEasy implements LevelInformation {
    @Override
    public int numberOfBalls() {
        return 10;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        List<Velocity> velocities = new ArrayList<>();
        int halfOfBalls = numberOfBalls() / 2;
        int degrees = 60;
        for (int i = 1; i <= halfOfBalls; i++) {
            int currDegree = degrees * i / halfOfBalls;
            velocities.add(Velocity.fromAngleAndSpeed(currDegree, 5));
            velocities.add(Velocity.fromAngleAndSpeed(-currDegree, 5));
        }
        return velocities;
    }

    @Override
    public int paddleSpeed() {
        return 5;
    }

    @Override
    public int paddleWidth() {
        return 500;
    }

    @Override
    public String levelName() {
        return "Wide Easy";
    }

    @Override
    public Sprite getBackground() {
        return new Sprite() {
            private final Color backgroundColor = Color.WHITE;
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
        List<Block> blocksList = new ArrayList<>();
        // the gap between the top frame and the top row of blocks
        final int upperGap = 250;
        final int blockHeight = 25;
        final int blocksPerRow = 15;
        final int rowWidth = GameLevel.WIDTH - GameLevel.CONST * 2;
        final int blockWidth = rowWidth / blocksPerRow;
        // compensate for lack of precision in blockWidth (integer division)
        int missingPixels = rowWidth - blockWidth * blocksPerRow;
        final int firstBlockX = GameLevel.WIDTH - GameLevel.CONST - blockWidth;
        final int y = GameLevel.CONST + upperGap;
        double x = firstBlockX;
        for (int i = 0; i < blocksPerRow; i++) {
            Color color = Color.CYAN;
            if (i > 1) {
                color = Color.PINK;
            }
            if (i > 3) {
                color = Color.BLUE;
            }
            if (i > 5) {
                color = Color.GREEN;
            }
            if (i > 8) {
                color = Color.YELLOW;
            }
            if (i > 10) {
                color = Color.ORANGE;
            }
            if (i > 12) {
                color = Color.RED;
            }
            int width = blockWidth;
            // distribute the missing pixels evenly (until we compensated for all of them)
            if (missingPixels > 0) {
                width++;
                missingPixels--;
            }
            Point upperLeft = new Point(x, y);
            Rectangle rect = new Rectangle(upperLeft, width, blockHeight);
            Block block = new Block(rect);
            block.setBlockColor(color);
            blocksList.add(block);
            x -= width;
        }
        return blocksList;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return blocks().size();
    }
}