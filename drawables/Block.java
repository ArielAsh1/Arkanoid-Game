// ID 208465096

package drawables;
import biuoop.DrawSurface;
import geometry.Point;
import geometry.Rectangle;
import geometry.Util;
import listeners.HitListener;
import listeners.HitNotifier;
import physics.Collidable;
import physics.Velocity;
import settings.GameLevel;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Ariel Ashkenazy
 * this class represetns a Sprites.drawables.Block.
 */
public class Block implements Collidable, Sprite, HitNotifier {
    private Rectangle rectangle;
    private Color color;
    private List<HitListener> hitListeners;

    /**
     * constructor. Creates a new block using a given rectangle.
     * @param rectangle the rectangle of the block.
     */
    public Block(Rectangle rectangle) {
        this.rectangle = rectangle;
        this.hitListeners = new ArrayList<>();
    }

    /**
     * sets the Color of the block.
     * @param blockColor the given color for the block.
     */
    public void setBlockColor(Color blockColor) {
        this.color = blockColor;
    }

    /**
     * gets the collision rectangle.
     * @return the collision rectangle.
     */
    @Override
    public Rectangle getCollisionRectangle() {
        return rectangle;
    }

    /**
     * calculates the new velocity of the ball after hitting a collidable.
     * @param collisionPoint the point of collision.
     * @param currentVelocity the current velocity of the ball.
     * @return the updated velocity.
     */
    public Velocity calculateNewVelocity(Point collisionPoint, Velocity currentVelocity) {
        double dx = currentVelocity.getDx();
        double dy = currentVelocity.getDy();

        if (Util.areTheSame(collisionPoint.getX(), rectangle.getUpperRight().getX())
                || Util.areTheSame(collisionPoint.getX(), rectangle.getUpperLeft().getX())) {
            // so we hit the right/left border of the block
            dx = -dx;
        }
        if (Util.areTheSame(collisionPoint.getY(), rectangle.getUpperRight().getY())
                || Util.areTheSame(collisionPoint.getY(), rectangle.getLowerLeft().getY())) {
            // so we hit the top/bottom border of the block
            dy = -dy;
        }
        return new Velocity(dx, dy);
    }

    /**
     * returns the new ball's velocity and notifies all listeners.
     * @param hitter the hitter ball object.
     * @param collisionPoint the point of collision.
     * @param currentVelocity the current velocity of the ball.
     * @return the updated velocity.
     */
    @Override
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        notifyHit(hitter);
        return calculateNewVelocity(collisionPoint, currentVelocity);
    }

    @Override
    public void drawOn(DrawSurface surface) {
        if (surface == null) {
            return;
        }
        surface.setColor(this.color);
        surface.fillRectangle((int) rectangle.getUpperLeft().getX(),
                (int) rectangle.getUpperLeft().getY(),
                (int) rectangle.getWidth(), (int) rectangle.getHeight());
        surface.setColor(Color.BLACK); // for black outline of the block
        surface.drawRectangle((int) rectangle.getUpperLeft().getX(),
                (int) rectangle.getUpperLeft().getY(),
                (int) rectangle.getWidth(), (int) rectangle.getHeight());
    }

    /**
     * does nothing at this point.
     */
    @Override
    public void timePassed() {

    }

    /**
     * adds the ball to the game.
     * @param g our game.
     */
    public void addToGame(GameLevel g) {
        g.addCollidable(this);
        g.addSprite(this);
    }

    /**
     * removes the ball from the game.
     * @param game our game.
     */
    public void removeFromGame(GameLevel game) {
        game.removeCollidable(this);
        game.removeSprite(this);
    }

    @Override
    public void addHitListener(HitListener hl) {
        hitListeners.add(hl);
    }

    @Override
    public void removeHitListener(HitListener hl) {
        hitListeners.remove(hl);
    }

    /**
     * notifies all listeners when a hit occurs.
     * @param hitter the hitting object.
     */
    private void notifyHit(Ball hitter) {
        // makes a copy of the listeners.HitListener list
        List<HitListener> listeners = new ArrayList<>(this.hitListeners);
        // Notifies all listeners about a hit event
        for (HitListener hl : listeners) {
            hl.hitEvent(this, hitter);
        }
    }
}