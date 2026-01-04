package Geometric;

/**
 * Point.
 * @author Roi Shukrun <address>roee.shukrun@live.biu.ac.il</address>
 * @version 1.5
 * @since 2023 -03-30
 * The Point class contains 5 methods and 3 fields for describing point on two dimension scale
 */

public class Point {
    // The constant THRESHOLD - for compare between doubles
    static final double THRESHOLD = 0.00001;
    // Fields:
    private final double x;
    private final double y;

    // Constructor:

    /**
     * Instantiates a new Point.
     * @param x the x coordinate
     * @param y the y coordinate
     */
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    // Accessors:

    /**
     * Get x.
     * This method returns the x coordinate of the point.
     * @return the x coordinate of the point
     */
    public double getX() {
        return this.x;
    }

    /**
     * Gets y.
     * This method returns the y coordinate of the point.
     * @return the y coordinate of the point
     */
    public double getY() {
        return this.y;
    }

    // Methods:

    /**
     * Distance double.
     * This method returns the distance of this point to the other point
     * @param other is the other point, we calculate the distance between this point to the current point
     * @return the distance between the two points
     */
//
    public double distance(Point other) {
        return (Math.sqrt(((this.x - other.x) * (this.x - other.x))
                + ((this.y - other.y) * (this.y - other.y))));
    }

    /**
     * Equals boolean.
     * This method checks if two points are equal by their coordinates.
     * The method return true if the points are equal, otherwise it returns false.
     * @param other the second point
     * @return true if the points are equal, otherwise it returns false
     */
    public boolean equals(Point other) {
        return ((Math.abs(this.x - other.x)) < THRESHOLD) && ((Math.abs(this.y - other.y)) < THRESHOLD);
    }
}