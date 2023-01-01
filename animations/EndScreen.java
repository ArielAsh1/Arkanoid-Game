// ID 208465096

package animations;
import biuoop.DrawSurface;
import settings.Counter;
import settings.GameLevel;
import java.awt.Color;

/**
 * @author Ariel Ashkenazy
 * this class represetns the end screen for the game (win of loss).
 */
public class EndScreen implements Animation {
    private boolean isGameWon;
    private boolean stop;
    private Counter score;

    /**
     * constructor.
     * @param isGameWon true if win false if loss.
     * @param score current score.
     */
    public EndScreen(boolean isGameWon, Counter score) {
        this.score = score;
        this.isGameWon = isGameWon;
        this.stop = false;
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        d.setColor(Color.LIGHT_GRAY);
        d.fillRectangle(0, 0, GameLevel.WIDTH, GameLevel.HEIGHT);
        d.setColor(Color.BLACK);
        if (isGameWon) {
            d.drawText(10, d.getHeight() / 2, "You Win! Your score is " + score.getValue(), 32);
        } else {
            d.drawText(10, d.getHeight() / 2, "Game Over. Your score is " + score.getValue(), 32);
        }
    }

    @Override
    public boolean shouldStop() {
        return this.stop;
    }
}