// ID 208465096

package animations;
import biuoop.DrawSurface;
import settings.GameLevel;
import java.awt.Color;

/**
 * @author Ariel Ashkenazy
 * this class represetns the pause screen for the game.
 */
public class PauseScreen implements Animation {
    @Override
    public void doOneFrame(DrawSurface d) {
        d.setColor(Color.LIGHT_GRAY);
        d.fillRectangle(0, 0, GameLevel.WIDTH, GameLevel.HEIGHT);
        d.setColor(Color.BLACK);
        d.drawText(10, d.getHeight() / 2, "paused -- press space to continue", 32);
    }
    @Override
    public boolean shouldStop() {
        return false;
    }
}