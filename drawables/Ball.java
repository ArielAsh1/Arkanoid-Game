// ID 208465096

package drawables;
import biuoop.DrawSurface;
import geometry.Line;
import geometry.Point;
import physics.Collidable;
import physics.CollisionInfo;
import physics.Velocity;
import settings.GameEnvironment;
import settings.GameLevel;
import java.awt.Color;

/**
 * @author Ariel Ashkenazy
 * this class represents a ball.
 * each ball has a center point and a color and can move in space.
 */
public class Ball implements Sprite {
    private Point center;
    private int r;
    private Color color;
    private Velocity velocity;
    private GameEnvironment environment;
    private Paddle paddle;

    /**
     * constructor. creates a new ball using given point, radius, and color.
     * @param center the center point of the ball.
     * @param r the radius of the ball.
     * @param color the color of the ball.
     */
    public Ball(Point center, int r, java.awt.Color color) {
    this.center = center;
    this.r = r;
    this.color = color;
    this.velocity = new Velocity(0, 0);
    }

    /**
     * constructor. creates a new ball using (x,y), radius, and color.
     * calls the other constructor and reuses it.
     * @param x the x coordinate of the center point.
     * @param y the y coordinate of the center point.
     * @param r the radius of the ball.
     * @param color the color of the ball.
     */
    public Ball(int x, int y, int r, java.awt.Color color) {
    this(new Point(x, y), r, color);
    }

    /**
     * sets the game environment.
     * @param gameEnvironment the given game environment.
     */
    public void setGameEnvironment(GameEnvironment gameEnvironment) {
        this.environment = gameEnvironment;
    }

    /**
     * sets the game paddle.
     * @param gamePaddle the given game paddle.
     */
    public void setPaddle(Paddle gamePaddle) {
        this.paddle = gamePaddle;
    }

    /**
     * gets the x coordinate of the center point.
     * @return int, the x coordinate.
     */
    public int getX() {
        return (int) this.center.getX();
    }

    /**
     * gets the y coordinate of the center point.
     * @return int, the y coordinate.
     */
    public int getY() {
        return (int) this.center.getY();
    }

    /**
     * gets the size of the ball's radius.
     * @return int, the radius size.
     */
    public int getSize() {
        return this.r;
    }

    /**
     * gets the color of the ball.
     * @return Color, the color of the ball.
     */
    public java.awt.Color getColor() {
        return this.color;
    }

    @Override
    public void drawOn(DrawSurface surface) {
        if (surface == null) {
            return;
        }
        surface.setColor(this.color);
        surface.fillCircle((int) center.getX(), (int) center.getY(), this.r);
        surface.setColor(Color.BLACK); // for black outline of the ball
        surface.drawCircle((int) center.getX(), (int) center.getY(), this.r);
    }

    /**
     * in charge of moving the ball one step.
     */
    @Override
    public void timePassed() {
        moveOneStep();
    }

    /**
     * sets velocity to the ball.
     * velocity is the change in position on the `x` and the `y` axes.
     * @param v is the change in position on both x-y axis.
     */
    public void setVelocity(Velocity v) {
        this.velocity = v;
    }

    /**
     * sets velocity to the ball.
     * velocity is the change in position on the `x` and the `y` axes.
     * @param dx the change on the x-axis.
     * @param dy the change on the y-axis.
     */
    public void setVelocity(double dx, double dy) {
        this.velocity = new Velocity(dx, dy);
    }

    /**
     * gets the velocity of the ball.
     * @return physics.Velocity, the ball's velocity.
     */
    public Velocity getVelocity() {
        return this.velocity;
    }

    /**
     * moves the ball and one step at a time.
     * changes the ball's velocity and center point according to it's current position.
     */
    public void moveOneStep() {
        Point currentPosition = center;
        Point endingPosition = velocity.applyToPoint(center);
        Line trajectory = new Line(currentPosition, endingPosition);
        CollisionInfo collisionInfo = environment.getClosestCollision(trajectory);
        if (collisionInfo == null) { // no collision - so move this step
            center = endingPosition;
        } else { // if there is a collision
            Point collisionPosition = collisionInfo.collisionPoint();
            Collidable collisionObject = collisionInfo.collisionObject();
            center = collisionPosition; // we move to the actual collision
            // makes sure the ball doesn't get stuck inside the paddle
            if (collisionObject == paddle) {
                center = new Point(center.getX(),
                        paddle.getCollisionRectangle().getUpperLeft().getY() - 1);
            }
            // and then we move the ball to "almost" the hit point
            if (velocity.getDx() > 0) { // if the ball moves to the right
                center = new Point(center.getX() - 1, center.getY());
                // -1 pushes a little back to the left
            } else if (velocity.getDx() < 0) { // if the ball moves to the left
                center = new Point(center.getX() + 1, center.getY());
                // +1 pushes a little back to the right
            }
            if (velocity.getDy() > 0) { // if the ball moves to the bottom
                center = new Point(center.getX(), center.getY() - 1);
                // -1 pushes a little back to the top
            } else if (velocity.getDy() < 0) { // if the ball moves to the top
                center = new Point(center.getX(), center.getY() + 1);
                // +1 pushes a little back to the bottom
            }
            velocity = collisionObject.hit(this, collisionPosition, velocity); // updates the velocity
        }
    }

    /**
     * removes the ball from the game.
     * @param game the game.
     */
    public void removeFromGame(GameLevel game) {
        game.removeSprite(this);
    }

    /**
     * adds the ball to the game.
     * @param g the game.
     */
    public void addToGame(GameLevel g) {
        g.addSprite(this);
    }
}