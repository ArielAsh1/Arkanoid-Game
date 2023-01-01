// ID 208465096

package animations;
import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

/**
 * @author Ariel Ashkenazy
 * this class represetns a KeyPress Stoppable Animation in the game.
 */
public class KeyPressStoppableAnimation implements Animation {
    private KeyboardSensor keyboardSensor;
    private String key;
    private Animation animation;
    private boolean stop;
    private boolean isAlreadyPressed;

    /**
     * constructor.
     * @param sensor the keyboard sensor.
     * @param key the key to press.
     * @param animation the animation.
     */
    public KeyPressStoppableAnimation(KeyboardSensor sensor, String key, Animation animation) {
        this.key = key;
        this.keyboardSensor = sensor;
        this.animation = animation;
        this.stop = false;
        this.isAlreadyPressed = true;
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        animation.doOneFrame(d);
        if (!keyboardSensor.isPressed(key)) {
            isAlreadyPressed = false;
        } else if (!isAlreadyPressed) {
            // if we got here so it means that the key was pressed now! so need to stop
            stop = true;
        }
    }

    @Override
    public boolean shouldStop() {
        return this.stop;
    }
}