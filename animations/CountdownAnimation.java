// ID 208465096

package animations;
import biuoop.DrawSurface;
import settings.GameLevel;
import settings.SpriteCollection;
import java.awt.Color;

/**
 * @author Ariel Ashkenazy
 * this class represents the Countdown Animation at the start of each level.
 */
public class CountdownAnimation implements Animation {
    private double numOfSeconds;
    private int countFrom;
    private SpriteCollection gameScreen;
    private boolean stop;
    private long prevTime;
    private double updateTimeFrame;

    /**
     * constructor.
     * @param numOfSeconds duration of total countdown.
     * @param countFrom the number we count down from.
     * @param gameScreen our game screen.
     */
    public CountdownAnimation(double numOfSeconds, int countFrom, SpriteCollection gameScreen) {
        this.numOfSeconds = numOfSeconds;
        this.countFrom = countFrom;
        this.gameScreen = gameScreen;
        this.stop = false;
        updateTimeFrame = 1000 * numOfSeconds / countFrom;
        prevTime = System.currentTimeMillis();
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        gameScreen.drawAllOn(d);
        if (countFrom <= 0) {
            this.stop = true;
            return;
        }
        d.setColor(Color.RED);
        d.drawText(GameLevel.WIDTH / 2, GameLevel.HEIGHT / 2 + 50, "" + countFrom, 50);
        if (System.currentTimeMillis() - prevTime >= updateTimeFrame) {
            countFrom--;
            prevTime = System.currentTimeMillis();
        }
    }
    @Override
    public boolean shouldStop() {
        return this.stop;
    }
}