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
 * this class represent the Final Four level.
 */
public class FinalFour implements LevelInformation {
    @Override
    public int numberOfBalls() {
        return 3;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        List<Velocity> velocities = new ArrayList<>();
        velocities.add(Velocity.fromAngleAndSpeed(0, 5));
        velocities.add(Velocity.fromAngleAndSpeed(45, 5));
        velocities.add(Velocity.fromAngleAndSpeed(-45, 5));
        return velocities;    }

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
        return "Final Four";
    }

    @Override
    public Sprite getBackground() {
        return new Sprite() {
            private final Color backgroundColor = new Color(0, 50, 220);
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
        final int upperGap = 70;
        final int blockHeight = 25;
        final int numOfRows = 7;
        final int blocksPerRow = 15;
        final int rowWidth = GameLevel.WIDTH - GameLevel.CONST * 2;
        final int blockWidth = rowWidth / blocksPerRow;
        // compensate for lack of precision in blockWidth (integer division)
        final int firstBlockX = GameLevel.WIDTH - GameLevel.CONST - blockWidth;
        final int firstBlockY = GameLevel.CONST + upperGap;
        final Color[] colors = {
                Color.GRAY, Color.RED, Color.YELLOW,
                Color.GREEN, Color.WHITE, Color.PINK, Color.CYAN};
        for (int row = 0; row < numOfRows; row++) {
            int missingPixels = rowWidth - blockWidth * blocksPerRow;
            double y = firstBlockY + row * blockHeight;
            double x = firstBlockX;
            // the modulo prevents a crush in case we change the numOfRows
            Color color = colors[row % colors.length];
            for (int i = 0; i < blocksPerRow; i++) {
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
        }
        return blocksList;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return blocks().size();
    }
}