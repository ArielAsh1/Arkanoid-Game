// ID 208465096

package settings;
import animations.AnimationRunner;
import animations.Animation;
import animations.CountdownAnimation;
import animations.KeyPressStoppableAnimation;
import animations.PauseScreen;
import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import drawables.Sprite;
import drawables.Block;
import drawables.Paddle;
import drawables.Ball;
import drawables.ScoreIndicator;
import geometry.Point;
import geometry.Rectangle;
import levels.LevelInformation;
import listeners.BallRemover;
import listeners.BlockRemover;
import listeners.ScoreTrackingListener;
import physics.Collidable;
import physics.Velocity;
import java.awt.Color;

/**
 * @author Ariel Ashkenazy
 * this class creates a level using given level information.
 */
public class GameLevel implements Animation {
    public static final int WIDTH = 800;
    public static final int HEIGHT = 600;
    // a constant value to help set up the blocks of the frame
    public static final int CONST = 20;

    private SpriteCollection sprites;
    private GameEnvironment environment;
    private Counter remainingBlocks;
    private Counter remainingBalls;
    private Counter score;
    private AnimationRunner runner;
    private KeyboardSensor keyboardSensor;
    private LevelInformation levelInformation;
    private boolean running;

    /**
     * constructor.
     * @param levelInformation holds the specific level information.
     * @param keyboardSensor the keyboard sensor.
     * @param runner the animation runner.
     * @param score the game score.
     */
    public GameLevel(LevelInformation levelInformation, KeyboardSensor keyboardSensor,
                     AnimationRunner runner, Counter score) {
        this.runner = runner;
        this.levelInformation = levelInformation;
        this.score = score;
        this.keyboardSensor = keyboardSensor;
        this.sprites = new SpriteCollection();
        this.environment = new GameEnvironment();
    }

    /**
     * adds a new physics.Collidable to the level.
     * @param c the collidable we add to the level.
     */
    public void addCollidable(Collidable c) {
        environment.addCollidable(c);
    }

    /**
     * adds a new drawables.Sprite to the level.
     * @param s the drawables.Sprite we add to the level.
     */
    public void addSprite(Sprite s) {
        sprites.addSprite(s);
    }

    /**
     * Initializes a new level.
     * creates the blocks, balls and a paddle, and adds them to the level.
     */
    public void initialize() {
        this.remainingBlocks = new Counter(0);
        this.remainingBalls = new Counter(0);
        this.addSprite(levelInformation.getBackground());
        this.createScoreIndicator();
        this.createFrameBlocks();
        this.createBlocks();
        Paddle paddle = this.createPaddle();
        this.createBalls(paddle);
    }

    /**
     * starts the animation loop and runs the level.
     */
    public void run() {
        this.runner.run(new CountdownAnimation(2.0, 3, sprites));
        this.running = true;
        this.runner.run(this);
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        if (remainingBalls.getValue() == 0 || remainingBlocks.getValue() == 0) {
            if (remainingBlocks.getValue() == 0) { // so bonus 100 points for clearing the level
                score.increase(100);
            }
            this.running = false;
        }
        if (this.keyboardSensor.isPressed("p")) {
            this.runner.run(
                    new KeyPressStoppableAnimation(this.keyboardSensor, KeyboardSensor.SPACE_KEY, new PauseScreen()));
        }

        this.sprites.drawAllOn(d);
        this.sprites.notifyAllTimePassed();
    }

    @Override
    public boolean shouldStop() {
        return !this.running;
    }

    /**
     * gets the current number of balls.
     * @return number of balls.
     */
    public int getRemainingBalls() {
        return remainingBalls.getValue();
    }

    /**
     * creates the score indicator of the game.
     */
    public void createScoreIndicator() {
        ScoreIndicator scoreIndicator = new ScoreIndicator(score, Color.WHITE, levelInformation.levelName());
        sprites.addSprite(scoreIndicator);
    }

    /**
     * removes the physics.Collidable from the level.
     * @param c the collidable we remove.
     */
    public void removeCollidable(Collidable c) {
    environment.removeCollidable(c);
    }

    /**
     * removes the drawables.Sprite from the level.
     * @param s the drawables.Sprite we remove.
     */
    public void removeSprite(Sprite s) {
        sprites.removeSprite(s);
    }

    /**
     * creates the blocks of the frame.
     */
    public void createFrameBlocks() {
        BallRemover ballRemover = new BallRemover(this, remainingBalls);
        // creates the upper left points of the frame blocks
        Point topBlockPoint = new Point(0, CONST);
        Point leftBlockPoint = new Point(0, CONST);
        Point bottomBlockPoint = new Point(0, HEIGHT);
        Point rightBlockPoint = new Point(WIDTH - CONST, CONST);
        // creates the frame rectangles and puts them in an array
        Rectangle[] rectArray = new Rectangle[4];
        rectArray[0] = new Rectangle(topBlockPoint, WIDTH, CONST); // top Rect
        rectArray[1] = new Rectangle(leftBlockPoint, CONST, HEIGHT - CONST); // left Rect
        rectArray[2] = new Rectangle(bottomBlockPoint, WIDTH, CONST); // bottom Rect ("death region")
        rectArray[3] = new Rectangle(rightBlockPoint, CONST, HEIGHT - CONST); // right Rect
        // creates the frame blocks and adds them to the game
        for (int i = 0; i < rectArray.length; i++) {
            Block frameBlock = new Block(rectArray[i]);
            // is bottom block
            if (i == 2) {
                frameBlock.addHitListener(ballRemover);
            }
            frameBlock.setBlockColor(Color.GRAY);
            frameBlock.addToGame(this);
        }
    }

    /**
     * creates the blocks of the level.
     */
    public void createBlocks() {
        BlockRemover blockRemover = new BlockRemover(this, remainingBlocks);
        ScoreTrackingListener scoreTracker = new ScoreTrackingListener(score);

        for (Block block : levelInformation.blocks()) {
            block.addHitListener(blockRemover);
            block.addHitListener(scoreTracker);
            block.addToGame(this);
        }
        remainingBlocks.increase(levelInformation.numberOfBlocksToRemove());
    }

    /**
     * creates the balls of the level.
     * @param paddle the paddle of the level.
     */
    public void createBalls(Paddle paddle) {
        // to set the ball in the middle of the paddle
        int paddleX = (int) paddle.getCollisionRectangle().getUpperLeft().getX();
        int x = paddleX + levelInformation.paddleWidth() / 2;
        int y = (int) paddle.getCollisionRectangle().getUpperLeft().getY() - 3;

        for (Velocity vel : levelInformation.initialBallVelocities()) {
            Ball ball = new Ball(x, y, 5, Color.WHITE);
            ball.setGameEnvironment(environment);
            ball.setPaddle(paddle);
            ball.setVelocity(vel);
            ball.addToGame(this);
        }
        remainingBalls.increase(levelInformation.numberOfBalls());
    }

    /**
     * creates the paddle of the level.
     * @return new paddle object.
     */
    public Paddle createPaddle() {
        final int speed = levelInformation.paddleSpeed();
        final int paddleWidth = levelInformation.paddleWidth();
        final int paddleHeight = CONST - 5;
        Color color = Color.ORANGE;
        final double x = (WIDTH - paddleWidth) / 2.0;
        final double y = HEIGHT - CONST - paddleHeight;
        Point upperLeft = new Point(x, y);
        Rectangle rectangle = new Rectangle(upperLeft, paddleWidth, paddleHeight);
        Paddle paddle = new Paddle(keyboardSensor, speed);
        paddle.setPaddleColor(color);
        paddle.setPaddleRect(rectangle);
        paddle.addToGame(this);
        return paddle;
    }
}