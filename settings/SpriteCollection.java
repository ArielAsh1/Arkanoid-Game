// ID 208465096

package settings;
import biuoop.DrawSurface;
import drawables.Sprite;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Ariel Ashkenazy
 * this class is in charge of holding all the Sprites that are in the game.
 */
public class SpriteCollection {

    private List<Sprite> spritesList;

    /**
     * constructor. creates a list of Sprites.
     */
    public SpriteCollection() {
        spritesList = new ArrayList<>();
    }

    /**
     * adds the given drawables.Sprite to the List.
     * @param s is the drawables.Sprite we add to the List.
     */
    public void addSprite(Sprite s) {
        spritesList.add(s);
    }

    /**
     * removes the  drawables.Sprite from the List.
     * @param s the drawables.Sprite we remove from the List.
     */
    public void removeSprite(Sprite s) {
        spritesList.remove(s);
    }

    /**
     * calls timePassed() on all sprites.
     */
    public void notifyAllTimePassed() {
        List<Sprite> spritesCopy = new ArrayList<>(spritesList);
        for (Sprite s : spritesCopy) {
            s.timePassed();
        }
    }

    /**
     * calls drawOn(d) on all sprites.
     * @param d the given surface we draw the Sprites on.
     */
    public void drawAllOn(DrawSurface d) {
        for (Sprite s : spritesList) {
            s.drawOn(d);
        }
    }
}