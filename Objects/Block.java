package Objects;

import biuoop.DrawSurface;
import Levels.GameLevel;
import Game.Sprite;
import Listeners.HitNotifier;
import Listeners.HitListener;
import Game.GameEnvironment;
import Geometric.Line;
import Geometric.Point;
import Geometric.Rectangle;
import Physics.Collidable;
import Physics.Velocity;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * Block.
 * @author Roi Shukrun <address>roee.shukrun@live.biu.ac.il</address>
 * @version 1.5
 * @since 2023 -03-30
 * Represents a block object in the game. Implements the Collidable and Sprite interfaces.
 */

public class Block implements Collidable, Sprite, HitNotifier {
    // Magic numbers:
    // Magic numbers used as indices for block's lines and points
    static final int UPPER_LINE_INDEX = 0;
    static final int LOWER_LINE_INDEX = 1;
    static final int RIGHT_LINE_INDEX = 2;
    static final int LEFT_LINE_INDEX = 3;
    static final int UPPER_LEFT_POINT_INDEX = 0;
    static final int UPPER_RIGHT_POINT_INDEX = 1;
    static final int BOTTOM_RIGHT_POINT_INDEX = 2;
    static final int BOTTOM_LEFT_POINT_INDEX = 3;
    static final int INSIDE_RECT_COORDINATE_DIFF = 1;
    static final int INSIDE_RECT_SIZE_DIFF = 2;

    // Fields:
    private final Rectangle rectangle;
    private Rectangle insideRectangle;
    private final java.awt.Color color;
    private GameEnvironment game;
    private Rectangle secondHit;
    private final List<HitListener> hitListeners;

    /**
     * Instantiates a new Block.
     * the block which the ball collide with.
     * @param rectangle the rectangle
     * @param color the color
     * @param game the current game environment
     */
    public Block(Rectangle rectangle, java.awt.Color color, GameEnvironment game) {
        this.rectangle = rectangle;
        this.color = color;
        this.game = game;
        this.hitListeners = new ArrayList<>();
        setInsideRectangle();
    }

    /**
     * Instantiates a new Block.
     * the block which the ball collide with.
     * @param rectangle the rectangle
     * @param color the color
     */
    public Block(Rectangle rectangle, java.awt.Color color) {
        this.rectangle = rectangle;
        this.color = color;
        this.hitListeners = new ArrayList<>();
        setInsideRectangle();
    }

    /**
     * set Game Environment.
     * Sets the block's current game environment.
     * @param gameEnvironment the current gameEnvironment
     */
    public void setGameEnvironment(GameEnvironment gameEnvironment) {
        this.game = gameEnvironment;
    }

    /**
     * set Inside Rectangle.
     * Sets the block's inner rectangle.
     */
    public void setInsideRectangle() {
        this.insideRectangle = new Rectangle(new Point(this.rectangle.getUpperLeft().getX()
                + INSIDE_RECT_COORDINATE_DIFF, this.rectangle.getUpperLeft().getY()
                + INSIDE_RECT_COORDINATE_DIFF), this.rectangle.getWidth() - INSIDE_RECT_SIZE_DIFF,
                this.rectangle.getHeight() - INSIDE_RECT_SIZE_DIFF);
    }

    /**
     * Gets color.
     * @return the color of the block
     */
    public java.awt.Color getColor() {
        return this.color;
    }

    // Collidable:
    /**
     * Gets Collision Rectangle.
     * @return the rectangle of the block which the ball collided with
     */
    @Override
    public Rectangle getCollisionRectangle() {
        return this.rectangle;
    }

    /**
     * hit Velocity.
     * This method calculates the new velocity of the ball that collides with the block.
     * @param collisionPoint the point of collision with the ball
     * @param currentVelocity the current velocity of the ball
     * @return the updated velocity of the ball after the collision
     */
    @Override
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        // Get the four lines of the rectangle
        Line upperLine = this.rectangle.getLines().get(UPPER_LINE_INDEX);
        Line lowerLine = this.rectangle.getLines().get(LOWER_LINE_INDEX);
        Line rightLine = this.rectangle.getLines().get(RIGHT_LINE_INDEX);
        Line leftLine = this.rectangle.getLines().get(LEFT_LINE_INDEX);
        // Get the four points of the rectangle
        Point upperLeft = this.rectangle.getPoints().get(UPPER_LEFT_POINT_INDEX);
        Point upperRight = this.rectangle.getPoints().get(UPPER_RIGHT_POINT_INDEX);
        Point bottomRight = this.rectangle.getPoints().get(BOTTOM_RIGHT_POINT_INDEX);
        Point bottomLeft = this.rectangle.getPoints().get(BOTTOM_LEFT_POINT_INDEX);
        // Initialize the new velocity with the current velocity
        double newDx = currentVelocity.getDx();
        double newDy = currentVelocity.getDy();
        int multipleHit = 1;
        this.notifyHit(hitter);
        // Check for collision with other collidables in the game
        for (Collidable collidable: this.game.getCollidables()) {
            // If the other collidable is not this block
            if (collidable.getCollisionRectangle() != this.rectangle) {
                Point upperLeft2 = collidable.getCollisionRectangle().getPoints().get(UPPER_LEFT_POINT_INDEX);
                Point upperRight2 = collidable.getCollisionRectangle().getPoints().get(UPPER_RIGHT_POINT_INDEX);
                Point bottomRight2 = collidable.getCollisionRectangle().getPoints().get(BOTTOM_RIGHT_POINT_INDEX);
                Point bottomLeft2 = collidable.getCollisionRectangle().getPoints().get(BOTTOM_LEFT_POINT_INDEX);
                // If there is a collision with a point on the other collectible's rectangle
                if (upperLeft2.equals(collisionPoint) || upperRight2.equals(collisionPoint)
                        || bottomRight2.equals(collisionPoint) || bottomLeft2.equals(collisionPoint)) {
                    // Count the number of collisions
                    multipleHit++;
                    // Set the secondHit variable to the other collectible's rectangle
                    secondHit = collidable.getCollisionRectangle();
                }
            }
        }
        // If there were multiple hits, change the velocity according to the number of hits
        // If there is 3 blocks involved the new velocity is in the opposite direction
        if (multipleHit == 3) {
            return new Velocity(-newDx, -newDy);
        } else if (multipleHit == 2) {
            Point upperLeft2 = secondHit.getPoints().get(UPPER_LEFT_POINT_INDEX);
            Point upperRight2 = secondHit.getPoints().get(UPPER_RIGHT_POINT_INDEX);
            Point bottomRight2 = secondHit.getPoints().get(BOTTOM_RIGHT_POINT_INDEX);
            Point bottomLeft2 = secondHit.getPoints().get(BOTTOM_LEFT_POINT_INDEX);
            // Check if the collision occurred on one of the sides of the two rectangles
            if ((bottomRight.equals(bottomLeft2) && bottomRight.equals(collisionPoint))
                    || (bottomLeft.equals(bottomRight2) && bottomLeft.equals(collisionPoint))
                    || (upperRight.equals(upperLeft2) && upperRight.equals(collisionPoint))
                    || (upperLeft.equals(upperRight2) && upperLeft.equals(collisionPoint))) {
                return new Velocity(newDx, -newDy);
                // Check if the collision occurred on one of the sides of the two rectangles
            } else if ((bottomRight.equals(upperRight2) && bottomRight.equals(collisionPoint))
                    || (upperRight.equals(bottomRight2) && upperRight.equals(collisionPoint))
                    || (bottomLeft.equals(upperLeft2) && bottomLeft.equals(collisionPoint))
                    || (upperLeft.equals(bottomLeft2) && upperLeft.equals(collisionPoint))) {
                return new Velocity(-newDx, newDy);
                // Check if the collision occurred on diagonal point of the two rectangles
            } else if ((bottomRight.equals(upperLeft2) && bottomRight.equals(collisionPoint))
                    || (upperLeft.equals(bottomRight2) && upperLeft.equals(collisionPoint))
                    || (bottomLeft.equals(upperRight2) && bottomLeft.equals(collisionPoint))
                    || (upperRight.equals(bottomLeft2) && upperRight.equals(collisionPoint))) {
                return new Velocity(-newDx, -newDy);
            } else {
                return new Velocity(-newDx, -newDy);
            }
        }
        // If there is only one block that the ball hit
        // checking the angle of the collision in horizontal collision
        if (bottomLeft.equals(collisionPoint) && currentVelocity.getAngle() > 90) {
            return new Velocity(-newDx, newDy);
        } else if (bottomRight.equals(collisionPoint) && currentVelocity.getAngle() > 0) {
            return new Velocity(-newDx, newDy);
        } else if (upperRight.equals(collisionPoint) && currentVelocity.getAngle() < 0) {
            return new Velocity(-newDx, newDy);
        // If the collision occurs on top/bottom of the block
        } else if (upperLine.contains(collisionPoint) || lowerLine.contains(collisionPoint)) {
            newDy = -currentVelocity.getDy();
        } else if (rightLine.contains(collisionPoint) || leftLine.contains(collisionPoint)) {
            // if the collision occurs on the sides of the block
            newDx = -currentVelocity.getDx();
        }
        // if nun of the above is true, there isn't collision, so we return the current velocity
        return new Velocity(newDx, newDy);
    }

    /**
     * Draw on.
     * This method draw the block on the given draw surface
     * @param surface the given DrawSurface
     */
    @Override
    public void drawOn(DrawSurface surface) {
        // Set the drawing color to black
        surface.setColor(Color.black);
        // Draw the outer rectangle of the block
        surface.fillRectangle((int) this.rectangle.getUpperLeft().getX(),
                (int) this.rectangle.getUpperLeft().getY(),
                (int) this.rectangle.getWidth(), (int) this.rectangle.getHeight());
        // Set the drawing color to the block's color
        surface.setColor(this.getColor());
        // Draw the inner rectangle of the block
        surface.fillRectangle((int) this.insideRectangle.getUpperLeft().getX(),
                (int) this.insideRectangle.getUpperLeft().getY(),
                (int) this.insideRectangle.getWidth(), (int) this.insideRectangle.getHeight());
    }

    /**
     * time Passed.
     * This method notify the block that the time of the session passed
     */
    @Override
    public void timePassed() {
    }

    /**
     * Add to gameLevel.
     * This method add the block to the current gameLevel by adding him to the collidables and sprites
     * @param gameLevel the current gameLevel
     */
    public void addToGame(GameLevel gameLevel) {
        gameLevel.addCollidable(this);
        gameLevel.addSprite(this);
    }

    /**
     * Remove from gameLevel.
     * This method remove the block from the current gameLevel by removing him from the collidables and sprites
     * @param gameLevel the current gameLevel
     */
    public void removeFromGame(GameLevel gameLevel) {
        gameLevel.removeCollidable(this);
        gameLevel.removeSprite(this);
    }

    /**
     * Add Hit Listener.
     * This method add given hit listener
     * @param hl the given hit listener
     */
    @Override
    public void addHitListener(HitListener hl) {
        this.hitListeners.add(hl);
    }

    /**
     * remove Hit Listener.
     * This method remove given hit listener
     * @param hl the given hit listener
     */
    @Override
    public void removeHitListener(HitListener hl) {
        this.hitListeners.remove(hl);
    }

    /**
     * Notify hit.
     * This method notify when hit committed
     * @param hitter the ball that created the hit with the block
     */
    public void notifyHit(Ball hitter) {
        // Make a copy of the hitListeners before iterating over them.
        List<HitListener> listeners = new ArrayList<>(this.hitListeners);
        // Notify all listeners about a hit event:
        for (HitListener hl : listeners) {
            hl.hitEvent(this, hitter);
        }
    }
}
