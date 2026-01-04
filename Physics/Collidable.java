package Physics;

import Geometric.Point;
import Geometric.Rectangle;
import Objects.Ball;

/**
 * The interface Collidable.
 * @author Roi Shukrun <address>roee.shukrun@live.biu.ac.il</address>
 * @version 1.5
 * @since 2023 -03-30
 */

public interface Collidable {

    /**
     * Gets collision rectangle.
     * Return the "collision shape" of the object.
     * @return the collision rectangle
     */
    Rectangle getCollisionRectangle();

    /**
     * Hit velocity.
     * Notify the object that we collided with it at collisionPoint with
     * a given velocity.
     * The return is the new velocity expected after the hit (based on
     * the force the object inflicted on us).
     * @param hitter  the hitter ball
     * @param collisionPoint  the collision point
     * @param currentVelocity the current velocity of the ball
     * @return the updated velocity of the ball after the collision
     */
    Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity);
}