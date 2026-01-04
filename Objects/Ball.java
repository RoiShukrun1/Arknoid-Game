package Objects;

import biuoop.DrawSurface;
import Game.GameEnvironment;
import Levels.GameLevel;
import Game.Sprite;
import Geometric.Line;
import Geometric.Point;
import Physics.CollisionInfo;
import Physics.Velocity;

import java.awt.Color;


/**
 * Ball.
 * @author Roi Shukrun <address>roee.shukrun@live.biu.ac.il</address>
 * @version 1.5
 * @since 2023 -03-30
 * The Ball class contains 6 fields and 11 methods for describing ball object
 */

public class Ball implements Sprite {
    // Magic numbers:
    // The default borders of the ball movement
    public static final int DEFAULT_X_RIGHT_BORDER = 775;
    public static final int DEFAULT_X_LEFT_BORDER = 25;
    public static final int DEFAULT_Y_TOP_BORDER = 31;
    public static final int DEFAULT_Y_BOTTOM_BORDER = 569;

    // Fields:
    private Point center;
    private final int r;
    private final java.awt.Color color;
    private Velocity v;
    private GameEnvironment currentGame;
    // The left border of the ball movement (top left point)
    private Point leftBorder;
    // The right border of the ball movement (right bottom point)
    private Point rightBorder;


    // Constructors:

    /**
     * Instantiates a new Ball.
     * @param x the x coordinate of the ball center
     * @param y the y coordinate of the ball center
     * @param r the radius of the ball
     * @param color the color of the ball
     * @param currentGame the current game environment
     */
    public Ball(int x, int y, int r, java.awt.Color color, GameEnvironment currentGame) {
        // If the radius entered is negative we get his absolute value.
        if (r < 0) {
            r = Math.abs(r);
        }
        this.center = new Point(x, y);
        this.r = r;
        this.color = color;
        this.currentGame = currentGame;
        this.setBorders();
    }

    /**
     * Instantiates a new Ball by Point.
     * @param point the x and y coordinates of the ball center
     * @param r the radius of the ball
     * @param color the color of the ball
     * @param currentGame the current game environment
     */
    public Ball(Point point, int r, java.awt.Color color, GameEnvironment currentGame) {
        // If the radius entered is negative we get his absolute value.
        if (r < 0) {
            r = Math.abs(r);
        }
        this.center = point;
        this.r = r;
        this.color = color;
        this.currentGame = currentGame;
        this.setBorders();
    }

    /**
     * Set the game environment for the ball.
     * @param gameEnvironment the game environment to set for the ball.
     */
    public void setGameEnvironment(GameEnvironment gameEnvironment) {
        this.currentGame = gameEnvironment;
    }

    /**
     * Sets borders default.
     * This method set the borders of the ball movement
     */
    public void setBorders() {
        this.leftBorder = new Point(DEFAULT_X_LEFT_BORDER, DEFAULT_Y_TOP_BORDER);
        this.rightBorder = new Point(DEFAULT_X_RIGHT_BORDER, DEFAULT_Y_BOTTOM_BORDER);
    }

    /**
     * Sets borders.
     * This method set the borders of the ball movement
     * @param leftBorder  of the ball movement
     * @param rightBorder of the ball movement
     */
    public void setBorders(Point leftBorder, Point rightBorder) {
        this.leftBorder = leftBorder;
        this.rightBorder = rightBorder;
    }

    // Accessors:

    /**
     * Gets x.
     * @return the x coordinate of the ball center
     */
    public int getX() {
        return (int) this.center.getX();
    }

    /**
     * Gets y.
     * @return the y coordinate of the ball center
     */
    public int getY() {
        return (int) this.center.getY();
    }

    /**
     * Gets center.
     * @return the x,y coordinates of the ball center
     */
    public Point getCenter() {
        return this.center;
    }

    /**
     * Gets size.
     * @return the size of the ball (radius)
     */
    public int getSize() {
        return this.r;
    }

    /**
     * Gets color.
     * @return the color of the ball
     */
    public java.awt.Color getColor() {
        return this.color;
    }

    /**
     * Gets velocity.
     * @return the velocity of the ball
     */
    public Velocity getVelocity() {
        return this.v;
    }

    /**
     * Get Right Border.
     * @return right border of the ball movement
     */
    public Point getRightBorder() {
        return this.rightBorder;
    }

    /**
     * Get Left Border.
     * @return left border of the ball movement
     */
    public Point getLeftBorder() {
        return this.leftBorder;
    }

    // Methods:

    /**
     * Draw on.
     * This method draw the ball on the given draw surface
     * @param surface the given DrawSurface
     */
    @Override
    public void drawOn(DrawSurface surface) {
        // If the ball is bigger than the screen size we don't print him
        if ((this.getSize() + this.getSize() <= this.getRightBorder().getX() - this.getLeftBorder().getX())
                || (this.getSize() + this.getSize() <= this.getRightBorder().getY() - this.getLeftBorder().getY())) {
            surface.setColor(getColor());
            surface.fillCircle(this.getX(), this.getY(), r);
            surface.setColor(Color.red);
            surface.fillCircle(this.getX(), this.getY(), 1);
        }
    }

    @Override
    public void timePassed() {
        this.moveOneStep();
    }

    /**
     * Sets velocity.
     * This method set the velocity of the ball with given velocity
     * @param v the given velocity
     */
    public void setVelocity(Velocity v) {
        this.v = v;
    }

    /**
     * Sets velocity.
     * This method set the velocity of the ball by dx and dy
     * @param dx the dx
     * @param dy the dy
     */
    public void setVelocity(double dx, double dy) {
        this.v = new Velocity(dx, dy);
    }

    /**
     * Trajectory line.
     * This method calculate the line of the next movement of the ball depending on his velocity
     * @return the trajectory line
     */
    public Line trajectory() {
        // Calculate the end point of the trajectory line based on the current ball position and velocity
        Point end = this.getVelocity().applyToPoint(this.center);
        // Create a new line object from the ball's current position to the end point of the trajectory
        return new Line(this.center, end);
    }

    /**
     * Add to gameLevel.
     * Add the ball to the sprite collection
     * @param gameLevel the current gameLevel
     */
    public void addToGame(GameLevel gameLevel) {
        gameLevel.addSprite(this);
    }

    /**
     * Remove from gameLevel.
     * Add the ball to the sprite collection
     * @param gameLevel the current gameLevel
     */
    public void removeFromGame(GameLevel gameLevel) {
        gameLevel.removeSprite(this);
//        gameLevel.remainingBallsCounter.decrease(1);
    }

    /**
     * Move the ball center point by the given amounts.
     * @param dx the amount to move in the x axes
     * @param dy the amount to move in the y axes
     */
    public void moveBy(double dx, double dy) {
        this.center = new Point(this.center.getX() + dx, this.center.getY() + dy);
    }

    /**
     * Move one step.
     * This method makes the ball move based on its velocity.
     * If the ball is approaching a collidable object on the draw surface,
     * we change its movement to the opposite direction with the opposite speed.
     */
    public void moveOneStep() {
        // If the velocity isn't set, the ball can't move.
        if (this.getVelocity() != null) {
            CollisionInfo collisionInfo = this.currentGame.getClosestCollision(this.trajectory());
            if (collisionInfo == null) {
            // No collision, move the ball to the end of the trajectory.
                this.center = this.v.applyToPoint(this.center);
                this.trajectory();
            } else {
            // Collision detected.
                // Set the new velocity of the ball after the collision.
                this.v = collisionInfo.collisionObject().hit(this, collisionInfo.collisionPoint(), this.v);
                // Move the ball to the collision point.
                this.center = new Point(collisionInfo.collisionPoint().getX() + Math.signum(this.v.getDx()),
                        collisionInfo.collisionPoint().getY() + Math.signum(this.v.getDy()));
                this.trajectory();
                // If the ball hit the left or right borders of the draw surface, change its direction horizontally.
                if (this.center.getX() > DEFAULT_X_RIGHT_BORDER || this.center.getX() < DEFAULT_X_LEFT_BORDER) {
                    this.v = new Velocity(-this.v.getDx(), this.v.getDy());
                    if (this.center.getX() < DEFAULT_X_LEFT_BORDER) {
                        this.center = new Point(DEFAULT_X_LEFT_BORDER, this.center.getY());
                    } else {
                        this.center = new Point(DEFAULT_X_RIGHT_BORDER, this.center.getY());
                    }
                // If the ball hit the top or bottom borders of the draw surface, change its direction vertically.
                } else if (this.center.getY() > DEFAULT_Y_BOTTOM_BORDER || this.center.getY() < DEFAULT_Y_TOP_BORDER) {
                    if (this.center.getY() < DEFAULT_Y_TOP_BORDER) {
                        this.center = new Point(this.center.getX(), DEFAULT_Y_TOP_BORDER);
                    }
                }
            }
        }
    }
}

