package Game;

import Geometric.Line;
import Geometric.Point;
import Physics.Collidable;
import Physics.CollisionInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * GameLevel environment.
 * @author Roi Shukrun <address>roee.shukrun@live.biu.ac.il</address>
 * @version 1.5
 * @since 2023 -03-30
 */

public class GameEnvironment {
    private final List<Collidable> collidables;

    /**
     * Instantiates a new GameLevel environment.
     */
    public GameEnvironment() {
        this.collidables = new ArrayList<>();
    }

    /**
     * Gets collidables.
     * @return the collidables list of the game
     */
    public List<Collidable> getCollidables() {
        return collidables;
    }

    /**
     * Add collidable.
     * add the given collidable to the environment.
     * @param c the given collidable
     */
    public void addCollidable(Collidable c) {
        this.collidables.add(c);
    }

    /**
     * Remove collidable.
     * remove the given collidable from the environment.
     * @param c the given collidable
     */
    public void removeCollidable(Collidable c) {
        this.collidables.remove(c);
    }


    /**
     * Gets closest collision.
     * Assume an object moving from line.start() to line.end().
     * If this object will not collide with any of the collidables
     * in this collection, return null. Else, return the information
     * about the closest collision that is going to occur.
     * @param trajectory the trajectory line of the ball
     * @return the closest collision of the ball
     */
    public CollisionInfo getClosestCollision(Line trajectory) {
        // Initialize variables to keep track of the closest collision point and collidable object
        Point closestPoint = null;
        Collidable closestCollidable = null;
        double minDistance = Double.POSITIVE_INFINITY;
        // Iterate over all the collidables in the environment and
        // find the closest collision point and collidable object
        // Make a copy of the sprites before iterating over them.
        List<Collidable> collidables = new ArrayList<>(this.collidables);
        for (Collidable c : collidables) {
            // Get the collision point between the trajectory and the current collidable object, if any
            Point collisionPoint = trajectory.closestIntersectionToStartOfLine(c.getCollisionRectangle());

            // If there is a collision point and it is closer than the current closest point, update the variables
            if (collisionPoint != null) {
                double distance = trajectory.start().distance(collisionPoint);
                if (distance < minDistance) {
                    closestPoint = collisionPoint;
                    closestCollidable = c;
                    minDistance = distance;
                }
            }
        }
        // If there was no collision, return null
        if (closestPoint == null) {
            return null;
        }
        // Return a new CollisionInfo object with the closest collision point and collidable object
        return new CollisionInfo(closestPoint, closestCollidable);
    }
}
