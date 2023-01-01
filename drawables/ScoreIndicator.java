// ID 208465096

package drawables;
import biuoop.DrawSurface;
import geometry.Point;
import settings.Counter;
import settings.GameLevel;
import java.awt.Color;

/**
 * @author Ariel Ashkenazy
 * this calss is in charge of displaying the game score.
 */
public class ScoreIndicator implements Sprite {
    public static final int FONT = 16;

    private Counter score;
    private Color color;
    private String levelName;

    /**
     * constructor.
     * @param score game score.
     * @param color score background color.
     * @param levelName the level name.
     */
    public ScoreIndicator(Counter score, Color color, String levelName) {
        this.score = score;
        this.color = color;
        this.levelName = levelName;
    }

    @Override
    public void drawOn(DrawSurface d) {
        Point topLeft = new Point(0, 0);
        d.setColor(this.color);
        // score background rectangle
        d.fillRectangle((int) topLeft.getX(), (int) topLeft.getY(), GameLevel.WIDTH, GameLevel.CONST);
        d.setColor(Color.BLACK);
        // rectangle score frame
        d.drawRectangle((int) topLeft.getX(), (int) topLeft.getY(), GameLevel.WIDTH, GameLevel.CONST);
        int middleX = GameLevel.WIDTH / 3;
        int middleY = GameLevel.CONST - 5;
        String scoreStr = "Score: " + this.score.getValue();
        d.drawText(middleX, middleY, scoreStr, FONT);
        d.drawText(middleX + 150, middleY, "Level Name: " + levelName, FONT);
    }

    /**
     * adds the score to the game.
     * @param game the game.
     */
    public void addToGame(GameLevel game) {
        game.addSprite(this);
    }

    @Override
    public void timePassed() {

    }
}