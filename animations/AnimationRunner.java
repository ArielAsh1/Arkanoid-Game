// ID 208465096

package animations;
import biuoop.Sleeper;
import biuoop.DrawSurface;
import biuoop.GUI;

/**
 * @author Ariel Ashkenazy
 * this class represents the animation runner.
 */
public class AnimationRunner {
    private GUI gui;
    private int framesPerSecond;
    private Sleeper sleeper;

    /**
     * constructor.
     * @param gui the main screen.
     */
    public AnimationRunner(GUI gui) {
        this.framesPerSecond = 60;
        this.sleeper = new Sleeper();
        this.gui = gui;
    }

    /**
     * one turn of the game.
     * @param animation the level.
     */
    public void run(Animation animation) {
        int millisecondsPerFrame = 1000 / framesPerSecond;
        while (!animation.shouldStop()) {
            long startTime = System.currentTimeMillis(); // timing
            DrawSurface d = gui.getDrawSurface();
            animation.doOneFrame(d);
            gui.show(d);
            long usedTime = System.currentTimeMillis() - startTime;
            long milliSecondLeftToSleep = millisecondsPerFrame - usedTime;
            if (milliSecondLeftToSleep > 0) {
                this.sleeper.sleepFor(milliSecondLeftToSleep);
            }
        }
    }
}