// ID 208465096

package drawables;
import biuoop.DrawSurface;

/**
 * @author Ariel Ashkenazy
 * the drawables.Sprite intersface.
 */
public interface Sprite {

    /**
     * draws the drawables.Sprite on the screen.
     * @param d the given draw surface.
     */
    void drawOn(DrawSurface d);

    /**
     * notify the sprite that time has passed.
     */
    void timePassed();
}