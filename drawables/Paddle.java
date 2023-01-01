// ID 208465096

package drawables;
import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import geometry.Point;
import geometry.Rectangle;
import physics.Collidable;
import physics.Velocity;
import settings.GameLevel;
import java.awt.Color;

/**
 * @author Ariel Ashkenazy
 * this class represetns the paddle of the game.
 */
public class Paddle implements Sprite, Collidable {
    private int speed;
    private Rectangle rectangle;
    private Color color;
    private biuoop.KeyboardSensor keyboard;

    /**
     * constructor. Creates a new paddle using a given KeyboardSensor.
     * @param keyboard the keyboard.
     * @param speed the speed.
     */
    public Paddle(KeyboardSensor keyboard, int speed) {
        this.keyboard = keyboard;
        this.speed = speed;
    }

    /**
     * sets the Color of the paddle.
     * @param paddleColor the given color for the paddle.
     */
    public void setPaddleColor(Color paddleColor) {
        this.color = paddleColor;
    }

    /**
     * sets the rectangle of the paddle.
     * @param paddleRect the given rectangle for the paddle.
     */
    public void setPaddleRect(Rectangle paddleRect) {
        this.rectangle = paddleRect;
    }

    /**
     * moves the paddle to the left.
     */
    public void moveLeft() {
        double x = rectangle.getUpperLeft().getX();
        double newX = x - speed;
        // makes sure the paddle stays within the frame
        if (newX < GameLevel.CONST) {
            newX = GameLevel.CONST;
        }
        double y = rectangle.getUpperLeft().getY();
        Point upperLeft = new Point(newX, y);
        rectangle = new Rectangle(upperLeft, rectangle.getWidth(), rectangle.getHeight());
    }

    /**
     * moves the paddle to the right.
     */
    public void moveRight() {
        double x = rectangle.getUpperLeft().getX();
        double width = rectangle.getWidth();
        double newX = x + speed;
        // makes sure the paddle stays within the frame
        if (newX > GameLevel.WIDTH - GameLevel.CONST - width) {
            newX = GameLevel.WIDTH - GameLevel.CONST - width;
        }
        double y = rectangle.getUpperLeft().getY();
        Point upperLeft = new Point(newX, y);
        rectangle = new Rectangle(upperLeft, rectangle.getWidth(), rectangle.getHeight());
    }

    /**
     * connects the keyboards keys to the paddle movement in the game.
     */
    @Override
    public void timePassed() {
        if (keyboard.isPressed(KeyboardSensor.LEFT_KEY)) {
            moveLeft();
        } else if (keyboard.isPressed(KeyboardSensor.RIGHT_KEY)) {
            moveRight();
        }
    }

    /**
     * draws the paddle on the screen.
     * @param d the drawSurface.
     */
    @Override
    public void drawOn(DrawSurface d) {
        if (d == null) {
            return;
        }
        d.setColor(this.color);
        d.fillRectangle((int) rectangle.getUpperLeft().getX(),
                (int) rectangle.getUpperLeft().getY(),
                (int) rectangle.getWidth(), (int) rectangle.getHeight());
        d.setColor(Color.BLACK);
        d.drawRectangle((int) rectangle.getUpperLeft().getX(),
                (int) rectangle.getUpperLeft().getY(),
                (int) rectangle.getWidth(), (int) rectangle.getHeight());
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
     * gets the region index on the paddle of the collision point.
     * @param collision the point of collision between the paddle and the ball.
     * @return the region index.
     */
    public int getRegionIndex(Point collision) {
        double size = rectangle.getWidth() / 5;
        double paddleX = rectangle.getUpperLeft().getX();
        double x = collision.getX();
        for (int i = 1; i <= 4; i++) {
            if (x < paddleX + i * size) {
                return i;
            }
        }
        // if we didn't return in the for loop, we must be in region 5
        return 5;
    }

    /**
     * calculates the new velocity of the ball after hitting a collidable.
     * @param currentVelocity the current velocity of the ball.
     * @param collisionPoint the point of collision.
     * @param hitter no use here.
     * @return the updated velocity.
     */
    @Override
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        double vel = currentVelocity.getSpeed();
        Velocity newVelocity;
        switch (getRegionIndex(collisionPoint)) {
            case 1:
                newVelocity = Velocity.fromAngleAndSpeed(300, vel);
                break;
            case 2:
                newVelocity = Velocity.fromAngleAndSpeed(330, vel);
                break;
            case 3:
                newVelocity = new Velocity(currentVelocity.getDx(), -currentVelocity.getDy());
                break;
            case 4:
                newVelocity = Velocity.fromAngleAndSpeed(30, vel);
                break;
            default: // case "5"
                newVelocity = Velocity.fromAngleAndSpeed(60, vel);
                break;
        }
        return newVelocity;
    }

    /**
     * adds this paddle to the game.
     * @param g the game.
     */
    public void addToGame(GameLevel g) {
        g.addSprite(this);
        g.addCollidable(this);
    }
}