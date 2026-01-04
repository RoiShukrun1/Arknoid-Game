package Physics;

import Geometric.Point;

/**
 * Velocity.
 * @author Roi Shukrun <address>roee.shukrun@live.biu.ac.il</address>
 * @version 1.5
 * @since 2023 -03-30
 * The Velocity class contains 2 fields and 5 methods that specifies
 * the change in position on the `x` and the `y` axes.
 */

public class Velocity {
    // Magic number:
    // Because in the instructions the 0 angle is top
    public static final int ANGLE_CHANGE = 90;
    // Fields:
    private final double dx;
    private final double dy;


    // Constructors:

    /**
     * Instantiates a new Velocity.
     * @param dx the dx
     * @param dy the dy
     */
    public Velocity(double dx, double dy) {
        this.dx = dx;
        this.dy = dy;
    }

    /**
     * From angle and speed velocity.
     * This method creates a new velocity object based on a given angle and speed.
     * The method first calculates the horizontal component of the velocity (dx)
     * using the cosine of the angle in radians,
     * and then calculates the vertical component of the velocity (dy) using the sine of the angle in radians.
     * Finally, the method returns a new Velocity object with the calculated dx and dy values.
     * @param angle the angle
     * @param speed the speed
     * @return the velocity specified by dx and dy
     */
    public static Velocity fromAngleAndSpeed(double angle, double speed) {
        double dx = speed * Math.cos(Math.toRadians(angle - ANGLE_CHANGE));
        double dy = speed * Math.sin(Math.toRadians(angle - ANGLE_CHANGE));
        return new Velocity(dx, dy);
    }

    // Accessors:

    /**
     * Gets dx.
     * @return dx the change in position on the `x` axes.
     */
    public double getDx() {
        return this.dx;
    }

    /**
     * Gets dy.
     * @return dy the change in position on the `y` axes.
     */
    public double getDy() {
        return this.dy;
    }


    /**
     * Get Speed.
     * @return the speed vector of the current Dx and Dy.
     */
    public double getSpeed() {
        if (dx < 0 || dy < 0) {
            return -Math.sqrt(this.dx * this.dx + this.dy * this.dy);
        } else {
            return Math.sqrt(this.dx * this.dx + this.dy * this.dy);
        }
    }

    /**
     * Get Angle.
     * @return the angle of the speed vector of the current Dx and Dy.
     */
    public double getAngle() {
        return Math.toDegrees(Math.atan2(dy, dx)) + ANGLE_CHANGE;
    }

    // Methods:

    /**
     * Apply to point.
     * This method Take a point with position (x,y) and return a new point
     * with position (x+dx, y+dy)
     * @param p the given point
     * @return the new point calculated
     */
    public Point applyToPoint(Point p) {
        return new Point(p.getX() + this.getDx(), p.getY() + this.getDy());
    }
}

