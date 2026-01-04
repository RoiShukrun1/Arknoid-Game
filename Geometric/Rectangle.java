package Geometric;

/**
 * Rectangle.
 * @author Roi Shukrun <address>roee.shukrun@live.biu.ac.il</address>
 * @version 1.5
 * @since 2023 -03-30
 * The Rectangle class contains fields and methods for describing rectangle object
 */

public class Rectangle {
    // Magic numbers:
    static final int UPPER_LEFT_POINT_INDEX = 0;
    static final int UPPER_RIGHT_POINT_INDEX = 1;
    static final int BOTTOM_RIGHT_POINT_INDEX = 2;
    static final int BOTTOM_LEFT_POINT_INDEX = 3;
    // Fields:
    // The upper left point of the rectangle
    private final Point upperLeft;
    // The width of the rectangle
    private final double width;
    // The height of the rectangle
    private final double height;


    /**
     * Instantiates a new Rectangle.
     * @param upperLeft the upper left point of the rectangle
     * @param width the width of the rectangle
     * @param height the height of the rectangle
     */
    public Rectangle(Point upperLeft, double width, double height) {
        this.upperLeft = upperLeft;
        this.width = width;
        this.height = height;
    }

    /**
     * Gets width.
     * @return the width of the rectangle
     */
    public double getWidth() {
        return this.width;
    }

    /**
     * Gets height.
     * @return the height of the rectangle
     */
    public double getHeight() {
        return this.height;
    }

    /**
     * Gets upper left.
     * @return the upper left point of the rectangle
     */
    public Point getUpperLeft() {
        return this.upperLeft;
    }

    /**
     * Gets points.
     * Returns the four points of the rectangle as a list, ordered clockwise starting from upper-left corner.
     * @return the points of the rectangle (list of them - first: upper left, second: upper right,
     * third: bottom right, fourth: bottom left)
     */
    public java.util.List<Point> getPoints() {
        // Create a new ArrayList to store the points.
        java.util.List<Point> points = new java.util.ArrayList<>();
        // Add the upper left point to the list.
        points.add(upperLeft);
        // Add the upper right point to the list, which is the upper left point shifted to the right by the width.
        points.add(new Point(upperLeft.getX() + width, upperLeft.getY()));
        // Add the bottom right point to the list, which is the upper right point shifted down by the height.
        points.add(new Point(upperLeft.getX() + width, upperLeft.getY() + height));
        // Add the bottom left point to the list, which is the upper left point shifted down by the height.
        points.add(new Point(upperLeft.getX(), upperLeft.getY() + height));
        // Return the list of points.
        return points;
    }

    /**
     * Gets lines.
     * Returns the four lines of the rectangle as a list, ordered clockwise starting from upper line.
     * @return the lines of the rectangle (list of them - first: upperLine, second: lowerLine,
     * third: right line, fourth: left line)
     */
    public java.util.List<Line> getLines() {
        // Create a new ArrayList to store the lines.
        java.util.List<Line> lines = new java.util.ArrayList<>();
        // Create a new Line for the upper line, which connects the upper left point to the upper right point.
        Line upperLine = new Line(this.getPoints().get(UPPER_LEFT_POINT_INDEX),
                this.getPoints().get(UPPER_RIGHT_POINT_INDEX));
        // Create a new Line for the lower line, which connects the bottom left point to the bottom right point.
        Line lowerLine = new Line(this.getPoints().get(BOTTOM_LEFT_POINT_INDEX),
                this.getPoints().get(BOTTOM_RIGHT_POINT_INDEX));
        // Create a new Line for the right line, which connects the upper right point to the bottom right point.
        Line rightLine = new Line(this.getPoints().get(UPPER_RIGHT_POINT_INDEX),
                this.getPoints().get(BOTTOM_RIGHT_POINT_INDEX));
        // Create a new Line for the left line, which connects the upper left point to the bottom left point.
        Line leftLine = new Line(this.getPoints().get(UPPER_LEFT_POINT_INDEX),
                this.getPoints().get(BOTTOM_LEFT_POINT_INDEX));
        // Add each line to the list of lines.
        lines.add(upperLine);
        lines.add(lowerLine);
        lines.add(rightLine);
        lines.add(leftLine);
        // Return the list of lines.
        return lines;
    }

    /**
     * Computes a list of intersection points between the given line and the rectangle.
     * @param line the given line to compute the intersection with the rectangle
     * @return a (possibly empty) list of intersection points between the line and the rectangle
     */
    public java.util.List<Point> intersectionPoints(Line line) {
        // Get the lines that compose the rectangle
        java.util.List<Line> lines = this.getLines();
        // Initialize an empty list of intersection points
        java.util.List<Point> intersectionPoints = new java.util.ArrayList<>();
        // Iterate over each line of the rectangle
        for (Line rectLine : lines) {
            // Compute the intersection point between the given line and the current line of the rectangle
            Point intersection = line.intersectionWith(rectLine);
            // If the intersection point is null,
            // check if the given line is included in the current line of the rectangle
            if (intersection == null && line.isInclusion(rectLine)) {
                // If the given line is included in the current line of the rectangle,
                // set the intersection point to the corresponding point of the rectangle line
                if (line.contains(this.getPoints().get(UPPER_LEFT_POINT_INDEX))) {
                    intersection = this.getPoints().get(UPPER_LEFT_POINT_INDEX);
                } else if (line.contains(this.getPoints().get(UPPER_RIGHT_POINT_INDEX))) {
                    intersection = this.getPoints().get(UPPER_RIGHT_POINT_INDEX);
                } else if (line.contains(this.getPoints().get(BOTTOM_RIGHT_POINT_INDEX))) {
                    intersection = this.getPoints().get(BOTTOM_RIGHT_POINT_INDEX);
                } else if (line.contains(this.getPoints().get(BOTTOM_LEFT_POINT_INDEX))) {
                    intersection = this.getPoints().get(BOTTOM_LEFT_POINT_INDEX);
                }
            }
            // If the intersection point is not null, add it to the list of intersection points
            if (intersection != null) {
                intersectionPoints.add(intersection);
            }
        }
        // Return the list of intersection points
        return intersectionPoints;
    }
}