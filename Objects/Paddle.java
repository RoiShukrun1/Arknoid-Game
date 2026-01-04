package Objects;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import Levels.GameLevel;
import Game.Sprite;
import Geometric.Line;
import Geometric.Point;
import Geometric.Rectangle;
import Physics.Collidable;
import Physics.Velocity;

/**
 * Paddle.
 *
 * @author Roi Shukrun <address>roee.shukrun@live.biu.ac.il</address>
 * @version 1.5
 * @since 2023 -03-30
 */
public class Paddle implements Sprite, Collidable {
    /**
     * The constant PADDLE_X_MOVEMENT.
     */
// Magic numbers:
    static final int PADDLE_X_MOVEMENT = 5;
    /**
     * The Paddle default x left border.
     */
    static final int PADDLE_DEFAULT_X_LEFT_BORDER = 25;
    /**
     * The Paddle default x right border.
     */
    static final int PADDLE_DEFAULT_X_RIGHT_BORDER = 775;
    /**
     * The Upper line index.
     */
    static final int UPPER_LINE_INDEX = 0;
    /**
     * The Right line index.
     */
    static final int RIGHT_LINE_INDEX = 2;
    /**
     * The Left line index.
     */
    static final int LEFT_LINE_INDEX = 3;
    /**
     * The Upper left point index.
     */
    static final int UPPER_LEFT_POINT_INDEX = 0;
    /**
     * The Upper right point index.
     */
    static final int UPPER_RIGHT_POINT_INDEX = 1;
    /**
     * The Bottom right point index.
     */
    static final int BOTTOM_RIGHT_POINT_INDEX = 2;
    /**
     * The Bottom left point index.
     */
    static final int BOTTOM_LEFT_POINT_INDEX = 3;
    /**
     * The Number of regions.
     */
    static final int NUMBER_OF_REGIONS = 5;
    /**
     * The constant FIRST_REGION.
     */
// The regions of the paddle:
    static final int FIRST_REGION = 0;
    /**
     * The Second region.
     */
    static final int SECOND_REGION = 1;
    /**
     * The Third region.
     */
    static final int THIRD_REGION = 2;
    /**
     * The Forth region.
     */
    static final int FORTH_REGION = 3;
    /**
     * The Fifth region.
     */
    static final int FIFTH_REGION = 4;
    private final biuoop.KeyboardSensor keyboard;
    private Rectangle rectangle;
    private final java.awt.Color color;
    // Default borders of the paddle:
    private Point leftBorder = new Point(PADDLE_DEFAULT_X_LEFT_BORDER, 0);
    private Point rightBorder = new Point(PADDLE_DEFAULT_X_RIGHT_BORDER, 0);
    private int paddleSpeed;

    /**
     * Instantiates a new Paddle.
     *
     * @param rectangle the rectangle
     * @param color     the color
     * @param keyboard  the keyboard
     */
    public Paddle(Rectangle rectangle, java.awt.Color color, biuoop.KeyboardSensor keyboard) {
        this.keyboard = keyboard;
        this.rectangle = rectangle;
        this.color = color;
    }
    /**
     * Sets speed.
     * @param paddleSpeed the paddle speed
     */
    public void setSpeed(int paddleSpeed) {
        this.paddleSpeed = paddleSpeed;
    }

    /**
     * Gets color.
     * @return the color of the paddle.
     */
    public java.awt.Color getColor() {
        return this.color;
    }

    /**
     * Sets borders.
     * This method set the borders of the paddle movement.
     * @param leftBorder  of the paddle movement
     * @param rightBorder of the paddle movement
     */
    public void setBorders(Point leftBorder, Point rightBorder) {
        this.leftBorder = leftBorder;
        this.rightBorder = rightBorder;
        paddleSpeed = PADDLE_X_MOVEMENT;
    }

    /**
     * Get Right Border.
     * @return right border of the paddle movement
     */
    public Point getRightBorder() {
        return this.rightBorder;
    }

    /**
     * Get Left Border.
     * @return left border of the paddle movement
     */
    public Point getLeftBorder() {
        return this.leftBorder;
    }

    /**
     * Move left.
     * This method Move the paddle left by 5 pixels if the left arrow key is pressed
     */
    public void moveLeft() {
        // Checking if the left key pressed
        if (keyboard.isPressed(KeyboardSensor.LEFT_KEY)) {
            double newX = rectangle.getUpperLeft().getX() - paddleSpeed;
            double y = rectangle.getUpperLeft().getY();
            // Moving the paddle just if it doesn't get off borders
            if (newX >= this.leftBorder.getX()) {
                this.rectangle = new Rectangle(new Point(newX, y), rectangle.getWidth(), rectangle.getHeight());
            }
        }
    }

    /**
     * Move right.
     * This method Move the paddle right by 5 pixels if the right arrow key is pressed
     */
    public void moveRight() {
        // Checking if the right key pressed
        if (keyboard.isPressed(KeyboardSensor.RIGHT_KEY)) {
            double newX = rectangle.getUpperLeft().getX() + paddleSpeed;
            double y = rectangle.getUpperLeft().getY();
            // Moving the paddle just if it doesn't get off borders
            if (newX + this.rectangle.getWidth() <= this.rightBorder.getX()) {
                this.rectangle = new Rectangle(new Point(newX, y), rectangle.getWidth(), rectangle.getHeight());
            }
        }
    }


    // Sprite:

    /**
     * time passed.
     * This method activates the moveLeft and moveRight methods
     */
    @Override
    public void timePassed() {
        moveLeft();
        moveRight();
    }

    /**
     * draw On.
     * This method draw the paddle on a given surface.
     * @param surface the given surface
     */
    @Override
    public void drawOn(DrawSurface surface) {
        surface.setColor(this.getColor());
        surface.fillRectangle((int) this.rectangle.getUpperLeft().getX(), (int) this.rectangle.getUpperLeft().getY(),
                (int) this.rectangle.getWidth(), (int) this.rectangle.getHeight());
    }

    // Collidable:
    /**
     * Get Collision Rectangle.
     * @return the rectangle of the paddle which the ball collided with
     */
    @Override
    public Rectangle getCollisionRectangle() {
        return this.rectangle;
    }

    /**
     * hit Velocity.
     * This method calculates the new velocity of the ball that collides with the paddle.
     * The paddle is divided into five different regions, each region affecting the ball velocity differently.
     * @param collisionPoint the point of collision with the ball
     * @param currentVelocity the current velocity of the ball
     * @return the new velocity of the ball after the collision with the paddle
     */
    @Override
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        // Define the paddle's lines
        Line rightLine = rectangle.getLines().get(RIGHT_LINE_INDEX);
        Line leftLine = rectangle.getLines().get(LEFT_LINE_INDEX);
        Line upperLine = rectangle.getLines().get(UPPER_LINE_INDEX);
        Line[] lines;
        // Split the upper line to 5 equal parts
        lines = upperLine.splitLine(NUMBER_OF_REGIONS);
        // Define the paddle's corner points
        Point upperLeft = this.rectangle.getPoints().get(UPPER_LEFT_POINT_INDEX);
        Point upperRight = this.rectangle.getPoints().get(UPPER_RIGHT_POINT_INDEX);
        Point bottomRight = this.rectangle.getPoints().get(BOTTOM_RIGHT_POINT_INDEX);
        Point bottomLeft = this.rectangle.getPoints().get(BOTTOM_LEFT_POINT_INDEX);
        // Get the speed of the current velocity
        double speed = Math.abs(currentVelocity.getSpeed());
        // Check which region of the paddle was hit, and return the new velocity accordingly
        if (collisionPoint.equals(upperLeft)) {
            return Velocity.fromAngleAndSpeed(300, speed);
        } else if (collisionPoint.equals(upperRight)) {
            return Velocity.fromAngleAndSpeed(60, speed);
        } else if (rightLine.contains(collisionPoint) || leftLine.contains(collisionPoint)) {
            if (bottomLeft.equals(collisionPoint) && currentVelocity.getAngle() > 0) {
                return new Velocity(-currentVelocity.getDx(), -currentVelocity.getDy());
            } else if (bottomRight.equals(collisionPoint) && currentVelocity.getAngle() < 180) {
                return new Velocity(-currentVelocity.getDx(), -currentVelocity.getDy());
            }
            return new Velocity(-currentVelocity.getDx(), currentVelocity.getDy());
        } else if (lines[FIRST_REGION].contains(collisionPoint)) {
            return Velocity.fromAngleAndSpeed(300, speed);
        } else if (lines[SECOND_REGION].contains(collisionPoint)) {
            return Velocity.fromAngleAndSpeed(330, speed);
        } else if (lines[THIRD_REGION].contains(collisionPoint)) {
            return new Velocity(currentVelocity.getDx(), -currentVelocity.getDy());
        } else if (lines[FORTH_REGION].contains(collisionPoint)) {
            return Velocity.fromAngleAndSpeed(30, speed);
        } else if (lines[FIFTH_REGION].contains(collisionPoint)) {
            return Velocity.fromAngleAndSpeed(60, speed);
        } else {
        // If the collision point is not on the paddle, return the current velocity
            return currentVelocity;
        }
    }

    /**
     * Add to game.
     * Add this paddle to the game.
     *
     * @param g the current game
     */
    public void addToGame(GameLevel g) {
        g.addSprite(this);
        g.addCollidable(this);
    }
}