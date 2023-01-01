// ID 208465096

package settings;
import animations.Animation;
import animations.AnimationRunner;
import animations.EndScreen;
import animations.KeyPressStoppableAnimation;
import biuoop.KeyboardSensor;
import levels.LevelInformation;
import java.util.List;

/**
 * @author Ariel Ashkenazy
 * this class is in charge of the flow of the game.
 */
public class GameFlow {
    private AnimationRunner runner;
    private KeyboardSensor keyboardSensor;
    private Counter score;
    private boolean isGameLost;

    /**
     * constructor.
     * @param runner the animation runner.
     * @param keyboardSensor the sensor.
     */
    public GameFlow(AnimationRunner runner, KeyboardSensor keyboardSensor) {
        this.runner = runner;
        this.keyboardSensor = keyboardSensor;
        score = new Counter(0);
        this.isGameLost = false;
    }

    /**
     * runs the game levels until stopped.
     * @param levels the list of our game levels.
     */
    public void runLevels(List<LevelInformation> levels) {
        for (LevelInformation levelInfo : levels) {
            GameLevel level = new GameLevel(levelInfo, keyboardSensor, runner, score);
            level.initialize();
            level.run();
            if (level.getRemainingBalls() == 0) {
                isGameLost = true;
                break;
            }
        }
        Animation endScreen = new EndScreen(!isGameLost, score);
        // if we lost so isGameLost is true and we send !true=false to isGameWon.
        // if we won so isGameLost is false and send !false=true to isGameWon.
        runner.run(new KeyPressStoppableAnimation(keyboardSensor, KeyboardSensor.SPACE_KEY, endScreen));
    }
}