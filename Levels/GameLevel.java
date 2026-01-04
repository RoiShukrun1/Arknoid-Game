package Levels;

import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.KeyboardSensor;
import Animations.Animation;
import Animations.AnimationRunner;
import Removers.BallRemover;
import Removers.BlockRemover;
import Animations.CountdownAnimation;
import Game.Counter;
import Game.GameEnvironment;
import Animations.GameOverAnimation;
import Animations.KeyPressStoppableAnimation;
import Game.LevelIndicator;
import Animations.PauseScreen;
import Game.ScoreIndicator;
import Listeners.ScoreTrackingListener;
import Game.Sprite;
import Game.SpriteCollection;
import Geometric.Point;
import Geometric.Rectangle;
import Objects.Ball;
import Objects.Block;
import Objects.Paddle;
import Physics.Collidable;

import java.awt.Color;
import java.util.List;

/**
 * GameLevel.
 *
 * @author Roi Shukrun <address>roee.shukrun@live.biu.ac.il</address>
 * @version 1.5
 * @since 2023 -03-30
 * This class represents the game itself,
 * it contains the GUI, Sleeper, SpriteCollection, and GameEnvironment objects.
 * It is responsible for initializing the game by creating Blocks, Balls, and Paddles,
 * and adding them to the game. It also runs the game by starting the animation loop.
 */
public class GameLevel implements Animation {
    /**
     * The constant DEFAULT_UPPER_LEFT_GUI_RECTANGLE.
     */
// Magic numbers:
    // These are constants used throughout the class for various values like sizes and positions.
    // The x and y coordinates of the upper left corner of the game area:
    static final int DEFAULT_UPPER_LEFT_GUI_RECTANGLE = 25;
    /**
     * The Default bottom right gui rectangle.
     */
    static final int DEFAULT_BOTTOM_RIGHT_GUI_RECTANGLE = 750; // The x coordinate of the right edge of the game area.
    /**
     * The Default left x border.
     */
    static final int DEFAULT_LEFT_X_BORDER = 0; // The x coordinate of the left edge of the game area.
    /**
     * The Default right x border.
     */
    static final int DEFAULT_RIGHT_X_BORDER = 775; // The x coordinate of the right edge of the game area.
    /**
     * The Default upper y border.
     */
    static final int DEFAULT_UPPER_Y_BORDER = 0; // The y coordinate of the top edge of the game area.
    /**
     * The Default bottom y border.
     */
    static final int DEFAULT_BOTTOM_Y_BORDER = 600; // The y coordinate of the bottom edge of the game area.
    /**
     * The Default vertical border rectangle height.
     */
    static final int DEFAULT_VERTICAL_BORDER_RECTANGLE_HEIGHT = 570; // The height of the vertical border rectangles.
    /**
     * The Default vertical border rectangle width.
     */
    static final int DEFAULT_VERTICAL_BORDER_RECTANGLE_WIDTH = 25; // The width of the vertical border rectangles.
    /**
     * The Default vertical border rectangle y.
     */
    static final int DEFAULT_VERTICAL_BORDER_RECTANGLE_Y = 30; // The y coordinate of the vertical border rectangles.
    /**
     * The Default horizontal border rectangle height.
     */
    static final int DEFAULT_HORIZONTAL_BORDER_RECTANGLE_HEIGHT = 30; // The height of the horizontal border rectangle.
    /**
     * The constant DEFAULT_BOTTOM_HORIZONTAL_BORDER_RECTANGLE_WIDTH.
     */
// The width of the bottom horizontal border rectangle:
    static final int DEFAULT_BOTTOM_HORIZONTAL_BORDER_RECTANGLE_WIDTH = 775;
    /**
     * The constant DEFAULT_BOTTOM_VERTICAL_BORDER_RECTANGLE_X.
     */
// The x coordinate of the bottom vertical border rectangle:
    static final int DEFAULT_BOTTOM_VERTICAL_BORDER_RECTANGLE_X = 25;
    /**
     * The constant DEFAULT_UPPER_HORIZONTAL_BORDER_RECTANGLE_WIDTH.
     */
// The width of the top horizontal border rectangle:
    static final int DEFAULT_UPPER_HORIZONTAL_BORDER_RECTANGLE_WIDTH = 800;
    /**
     * The constant DEFAULT_PADDLE_X_START_COORDINATE.
     */
// The x coordinate of the center of the paddle at the start of the game:
    static final int DEFAULT_PADDLE_X_START_COORDINATE = 335;
    /**
     * The constant DEFAULT_PADDLE_Y_COORDINATE.
     */
// The y coordinate of the center of the paddle at the start of the game:
    static final int DEFAULT_PADDLE_Y_COORDINATE = 580;
    /**
     * The Default paddle height.
     */
    static final int DEFAULT_PADDLE_HEIGHT = 20; // The default height of the paddle.
    /**
     * The Default ball radius.
     */
    static final int DEFAULT_BALL_RADIUS = 5; // The default radius of the balls.
    /**
     * The Default ball spacing.
     */
    static final int DEFAULT_BALL_SPACING = 30; // The default spacing between the balls.
    /**
     * The constant SCORE_INDICATOR_X.
     */
// The x coordinate of the score indicator upper left rectangle point
    static final int SCORE_INDICATOR_X = 350;
    /**
     * The constant SCORE_INDICATOR_Y.
     */
// The y coordinate of the score indicator upper left rectangle point
    static final int SCORE_INDICATOR_Y = 20;
    /**
     * The constant SCORE_INDICATOR_HEIGHT.
     */
// The height the score indicator rectangle
    static final int SCORE_INDICATOR_HEIGHT = 10;
    /**
     * The constant SCORE_INDICATOR_WIDTH.
     */
// The width the score indicator rectangle
    static final int SCORE_INDICATOR_WIDTH = 100;
    /**
     * The constant SCORE_INCREASE_OF_ENDING_LEVEL.
     */
// The increase in the score for completing a level
    static final int SCORE_INCREASE_OF_ENDING_LEVEL = 100;
    /**
     * The Direct hit x ball.
     */
    static final int DIRECT_HIT_X_BALL = 385;
    /**
     * The Direct hit y ball.
     */
    static final int DIRECT_HIT_Y_BALL = 500;
    /**
     * The Wide easy y ball.
     */
    static final int WIDE_EASY_Y_BALL = 500;
    /**
     * The Wide easy x paddle.
     */
    static final int WIDE_EASY_X_PADDLE = 100;
    /**
     * The Green 3 x ball.
     */
    static final int GREEN3_X_BALL = 375;
    /**
     * The Green 3 y ball.
     */
    static final int GREEN3_Y_BALL = 500;
    // Fields:
    private final SpriteCollection sprites = new SpriteCollection();
    private final GameEnvironment environment = new GameEnvironment();
    private final Counter remainingBlocksCounter = new Counter(0);
    private final Counter remainingBallsCounter = new Counter(0);
    private final Counter currentScore;
    private final AnimationRunner runner;
    private boolean running;
    private KeyboardSensor keyboard;
    private GUI gui;


    /**
     * Instantiates a new Game level.
     *
     * @param keyboardSensor  the keyboard sensor
     * @param animationRunner the animation runner
     * @param currenScore     the curren score
     */
    public GameLevel(KeyboardSensor keyboardSensor, AnimationRunner animationRunner,
                     Counter currenScore) {
        this.keyboard = keyboardSensor;
        this.runner = animationRunner;
        this.currentScore = currenScore;
        this.running = true;
    }

    /**
     * Gets remaining blocks counter.
     *
     * @return the remaining blocks counter
     */
    public Counter getRemainingBlocksCounter() {
        return remainingBlocksCounter;
    }

    /**
     * Gets current score.
     *
     * @return the current score
     */
    public Counter getCurrentScore() {
        return currentScore;
    }

    /**
     * Add collidable.
     *
     * @param c the new collidable object
     */
    public void addCollidable(Collidable c) {
        this.environment.addCollidable(c);
    }

    /**
     * Add sprite.
     *
     * @param s the new sprite object
     */
    public void addSprite(Sprite s) {
        this.sprites.addSprite(s);
    }

    /**
     * Remove collidable.
     *
     * @param c the collidable object
     */
    public void removeCollidable(Collidable c) {
        this.environment.removeCollidable(c);
    }

    /**
     * Remove sprite.
     *
     * @param s the sprite object
     */
    public void removeSprite(Sprite s) {
        this.sprites.removeSprite(s);
    }

    /**
     * Initialize with level parameter.
     * Initialize a new game: create the Blocks and Ball (and Paddle)
     * and add them to the game.
     *
     * @param levelInformation the level information
     */
    public void initialize(LevelInformation levelInformation) {
        // create a new GUI
        // create a new KeyboardSensor from the GUI
        gui = runner.getGui();
        keyboard = gui.getKeyboardSensor();
        BallRemover ballRemover1 = new BallRemover(this, remainingBallsCounter);
        ScoreTrackingListener scoreTrackingListener1 = new ScoreTrackingListener(currentScore);

        // create balls and add them to the game, each level has different balls
        switch (levelInformation.levelName()) {
            case "Direct Hit" -> {
                this.addSprite(levelInformation.getBackground());
                for (int i = 0; i < levelInformation.numberOfBalls(); i++) {
                    int spaceBalls = i * DEFAULT_BALL_SPACING;
                    // create a new ball with default values and add it to the game
                    Ball ball = new Ball(new Point(DIRECT_HIT_X_BALL + spaceBalls,
                            DIRECT_HIT_Y_BALL), DEFAULT_BALL_RADIUS, Color.white, this.environment);
                    ball.setVelocity(levelInformation.initialBallVelocities().get(i).getDx(),
                            levelInformation.initialBallVelocities().get(i).getDy());
                    ball.addToGame(this);
                    remainingBallsCounter.increase(1);
                }
                // create a new paddle with default values and add it to the game
                Paddle paddle = new Paddle(new Rectangle(new Point(DEFAULT_PADDLE_X_START_COORDINATE,
                        DEFAULT_PADDLE_Y_COORDINATE), levelInformation.paddleWidth(), DEFAULT_PADDLE_HEIGHT),
                        Color.orange, keyboard);
                paddle.setSpeed(levelInformation.paddleSpeed());
                paddle.addToGame(this);
            }
            case "Wide Easy" -> {
                this.addSprite(levelInformation.getBackground());
                int startBallX = 375; // Center of the picture
                for (int i = 0; i < levelInformation.numberOfBalls(); i++) {
                    // Create the first ball of the pair
                    Ball ball1 = new Ball(new Point(startBallX, WIDE_EASY_Y_BALL),
                            DEFAULT_BALL_RADIUS, Color.white, this.environment);
                    ball1.setVelocity(levelInformation.initialBallVelocities().get(i).getDx(),
                            levelInformation.initialBallVelocities().get(i).getDy());
                    ball1.addToGame(this);

                    remainingBallsCounter.increase(1);
                }
                // create a new paddle with default values and add it to the game
                Paddle paddle2 = new Paddle(new Rectangle(new Point(WIDE_EASY_X_PADDLE,
                        DEFAULT_PADDLE_Y_COORDINATE), levelInformation.paddleWidth(), DEFAULT_PADDLE_HEIGHT),
                        Color.orange, keyboard);
                paddle2.setSpeed(levelInformation.paddleSpeed());
                paddle2.addToGame(this);
            }
            case "Green 3" -> {
                this.addSprite(levelInformation.getBackground());
                for (int i = 0; i < levelInformation.numberOfBalls(); i++) {
                    // create two new ball with default values and add it to the game
                    Ball ball = new Ball(new Point(GREEN3_X_BALL, GREEN3_Y_BALL),
                            DEFAULT_BALL_RADIUS, Color.white, this.environment);
                    ball.setVelocity(levelInformation.initialBallVelocities().get(i).getDx(),
                            levelInformation.initialBallVelocities().get(i).getDy());
                    ball.addToGame(this);
                    remainingBallsCounter.increase(1);
                }
                // create a new paddle with default values and add it to the game
                Paddle paddle3 = new Paddle(new Rectangle(new Point(DEFAULT_PADDLE_X_START_COORDINATE,
                        DEFAULT_PADDLE_Y_COORDINATE), levelInformation.paddleWidth(), DEFAULT_PADDLE_HEIGHT),
                        Color.orange, keyboard);
                paddle3.setSpeed(levelInformation.paddleSpeed());
                paddle3.addToGame(this);
            }
            default -> { }
        }

        // create blocks for the borders of the game and add them to the game
        Block upBlock = new Block(new Rectangle(new Point(DEFAULT_LEFT_X_BORDER, DEFAULT_UPPER_Y_BORDER),
                DEFAULT_UPPER_HORIZONTAL_BORDER_RECTANGLE_WIDTH,
                DEFAULT_HORIZONTAL_BORDER_RECTANGLE_HEIGHT), Color.GRAY, this.environment);
        // "death-zone block":
        Block downBlock = new Block(new Rectangle(new Point(DEFAULT_BOTTOM_VERTICAL_BORDER_RECTANGLE_X,
                DEFAULT_BOTTOM_Y_BORDER),
                DEFAULT_BOTTOM_HORIZONTAL_BORDER_RECTANGLE_WIDTH,
                DEFAULT_HORIZONTAL_BORDER_RECTANGLE_HEIGHT), Color.GRAY, this.environment);
        downBlock.addHitListener(ballRemover1);

        Block rightBlock = new Block(new Rectangle(new Point(DEFAULT_RIGHT_X_BORDER,
                DEFAULT_VERTICAL_BORDER_RECTANGLE_Y),
                DEFAULT_VERTICAL_BORDER_RECTANGLE_WIDTH,
                DEFAULT_VERTICAL_BORDER_RECTANGLE_HEIGHT), Color.GRAY, this.environment);
        Block leftBlock = new Block(new Rectangle(new Point(DEFAULT_LEFT_X_BORDER,
                DEFAULT_VERTICAL_BORDER_RECTANGLE_Y),
                DEFAULT_VERTICAL_BORDER_RECTANGLE_WIDTH,
                DEFAULT_VERTICAL_BORDER_RECTANGLE_HEIGHT), Color.GRAY, this.environment);
        upBlock.addToGame(this);
        downBlock.addToGame(this);
        rightBlock.addToGame(this);
        leftBlock.addToGame(this);

        BlockRemover blockRemover1 = new BlockRemover(this, remainingBlocksCounter);
        List<Block> blocks;
        blocks = levelInformation.blocks();
        // create blocks and add them to the game
        for (int i = 0; i < levelInformation.numberOfBlocksToRemove(); i++) {
          blocks.get(i).setGameEnvironment(this.environment);
          blocks.get(i).addToGame(this);
          blocks.get(i).addHitListener(blockRemover1);
          blocks.get(i).addHitListener(scoreTrackingListener1);
          remainingBlocksCounter.increase(1);
        }

        // Create the score indicator
        ScoreIndicator scoreIndicator = new ScoreIndicator(new Rectangle(new Point(SCORE_INDICATOR_X,
                SCORE_INDICATOR_Y), SCORE_INDICATOR_WIDTH, SCORE_INDICATOR_HEIGHT),
                Color.BLACK, currentScore);
            scoreIndicator.addToGame(this);

        // Create the level indicator
        LevelIndicator levelIndicator = new LevelIndicator(new Rectangle(new Point(SCORE_INDICATOR_X + 150,
                SCORE_INDICATOR_Y), SCORE_INDICATOR_WIDTH, SCORE_INDICATOR_HEIGHT),
                Color.BLACK, levelInformation.levelName());
        levelIndicator.addToGame(this);
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        // Draw the sprites on the draw surface and show it on the GUI
        d.fillRectangle(DEFAULT_UPPER_LEFT_GUI_RECTANGLE, DEFAULT_UPPER_LEFT_GUI_RECTANGLE,
                DEFAULT_BOTTOM_RIGHT_GUI_RECTANGLE, DEFAULT_BOTTOM_RIGHT_GUI_RECTANGLE);
        d.setColor(Color.darkGray);
        this.sprites.drawAllOn(d);

        // Notify all sprites that time has passed
        this.sprites.notifyAllTimePassed();

        keyboard = gui.getKeyboardSensor();
        if (this.keyboard.isPressed("p") || this.keyboard.isPressed("P")) {
            Animation pauseScreen = new KeyPressStoppableAnimation(this.keyboard, KeyboardSensor.SPACE_KEY,
                    new PauseScreen());
            this.runner.run(pauseScreen);
        }
        // Case: player breaks all the blocks, finish the current level
        if (remainingBlocksCounter.getValue() == 0) {
            currentScore.increase(SCORE_INCREASE_OF_ENDING_LEVEL);
            running = false; // Stop the game
        } else if (remainingBallsCounter.getValue() == 0) { // Case: player loses all the balls
            running = false; // Stop the game
            // Display "GAME OVER" message
            Animation gameOverScreen = new KeyPressStoppableAnimation(this.keyboard, KeyboardSensor.SPACE_KEY,
                    new GameOverAnimation(currentScore));
            this.runner.run(gameOverScreen);
            // Close the GUI
            gui.close();
        }
    }

    @Override
    public boolean shouldStop() {
        return !this.running;
    }

    /**
     * Run.
     */
    public void run() {
        CountdownAnimation countdownAnimation = new CountdownAnimation(2, 3, this.sprites);
        this.runner.run(countdownAnimation); // Run the countdown animation
        this.running = true;
        runner.run(this); // Pass the current instance of the GameLevel class
    }
}
