package Geometric;

/**
 * Line.
 * @author Roi Shukrun <address>roee.shukrun@live.biu.ac.il</address>
 * @version 1.5
 * @since 2023 -03-30 The Line class is describing Line-segment on two dimension scale
 */

public class Line {
    // The constant THRESHOLD - for compare between doubles
    static final double THRESHOLD = 0.00001;
    // Fields:
    private final Point start;
    private final Point end;

    // Constructors:

    /**
     * Instantiates a new Line by given two points.
     * @param start the point of the start of the line segment
     * @param end   the point of the end of the line segment
     */
    public Line(Point start, Point end) {
        this.start = start;
        this.end = end;
    }

    /**
     * Instantiates a new Line by given 4 coordinates.
     * @param x1 the x coordinate of the start point of the line segment
     * @param y1 the y coordinate of the start point of the line segment
     * @param x2 the x coordinate of the end point of the line segment
     * @param y2 the y coordinate of the end point of the line segment
     */
    public Line(double x1, double y1, double x2, double y2) {
        this.start = new Point(x1, y1);
        this.end = new Point(x2, y2);
    }

    // Accessors:

    /**
     * Length double.
     * This method calculate and returns the length of the object line
     * @return the length of the object line
     */
    public double length() {
        return start.distance(end);
    }

    /**
     * Middle point.
     * @return the middle point of the line
     */
    public Point middle() {
        return new Point(((this.start().getX() + this.end().getX()) / 2),
                ((this.start().getY() + this.end().getY()) / 2));
    }

    /**
     * Start point.
     * @return the start point of the line segment
     */
    public Point start() {
        return new Point(this.start.getX(), this.start.getY());
    }

    /**
     * End point.
     * @return the end point of the line segment.
     */
    public Point end() {
        return new Point(this.end.getX(), this.end.getY());
    }

    // Methods:

    /**
     * slope Calculate double.
     * This method handle situation that the line is vertical line (positive infinity slope).
     * @return the slope of the line segment.
     */
    public double slopeCalculate() {
        double deltaX = this.start().getX() - this.end().getX();
        double deltaY = this.start().getY() - this.end().getY();
        if (deltaX == 0) {
            return Double.POSITIVE_INFINITY;
        }
        return deltaY / deltaX;
    }

    /**
     * share One Point boolean.
     * This method checks if this line and another line share exactly one point.
     * @param other the other line to compare to
     * @return true if this line and the other line share exactly one point, false otherwise
     */
    public boolean shareOnePoint(Line other) {
        int numSharedPoints = 0;
        if (this.start.equals(other.start) || this.start.equals(other.end)) {
            numSharedPoints++;
        }
        if (this.end.equals(other.start) || this.end.equals(other.end)) {
            numSharedPoints++;
        }
        // checking if there is more than one shared point
        if (other.start().getX() <= this.start().getX()
                && other.start().getY() <= this.start().getY()
                && other.end().getX() >= this.end().getX()
                && other.end().getY() >= this.end().getY()) {
            return false;
            // Check if the other line's start and end points are within the current line
        } else if (this.start().getX() <= other.start().getX()
                && this.start().getY() <= other.start().getY()
                && this.end().getX() >= other.end().getX()
                && this.end().getY() >= other.end().getY()) {
            return false;
        }
        // return true is the numSharedPoints is exactly 1
        return numSharedPoints == 1;
    }

    /**
     * Is isInclusion boolean.
     * This method checks if one of two lines (the current object and given line)
     * include the other or include part of the other
     * @param other the given line
     * @return true if the lines intersect, false otherwise
     */
    public boolean isInclusion(Line other) {
        // if there is just one share point there is nu inclusion
        if (this.shareOnePoint(other)) {
            return false;
        }
        // calculate the slopes of the lines
        double m1 = this.slopeCalculate();
        double m2 = other.slopeCalculate();

        // if both lines are vertical
        if ((this.start().getX() == this.end().getX()) && (other.start().getX() == other.end().getX())) {
            if (this.start().getX() == other.start().getX()) {
                return (other.start().getY() <= this.start().getY() && other.end().getY() >= this.end().getY())
                        || (other.end().getY() <= this.start().getY() && other.start().getY() >= this.end().getY());
            } else {
                return false;
            }
        } else if ((Math.abs(m1 - m2)) > THRESHOLD) { //if the lines aren't parallel there isn't a chance for inclusion
            return false;
        } else { // Check if the current line's start and end points are within the other line
            // If none of the above conditions are met, then the lines do not overlap
            if (other.start().getX() <= this.start().getX()
                    && other.start().getY() <= this.start().getY()
                    && other.end().getX() >= this.end().getX()
                    && other.end().getY() >= this.end().getY()) {
                return true;
                // Check if the other line's start and end points are within the current line
            } else if (this.start().getX() <= other.start().getX()
                    && this.start().getY() <= other.start().getY()
                    && this.end().getX() >= other.end().getX()
                    && this.end().getY() >= other.end().getY()) {
                return true;
                // Check if the endpoints of one line lie on the other line
            } else {
                int numEndpointsOnOtherLine = 0;
                if (other.contains(this.start()) || other.contains(this.end())) {
                    numEndpointsOnOtherLine++;
                }
                if (this.contains(other.start()) || this.contains(other.end())) {
                    numEndpointsOnOtherLine++;
                }
                return numEndpointsOnOtherLine > 1;
            }
        }
    }

    /**
     * contains boolean.
     * This method checks if a point lies on the object line
     * @param point the given point
     * @return true if the point lies on the object line, else false
     */
    public boolean contains(Point point) {
        // getting the needed coordinates
        double x = point.getX();
        double y = point.getY();
        double x1 = this.start().getX();
        double y1 = this.start().getY();
        double x2 = this.end().getX();
        double y2 = this.end().getY();

        if (x1 == x2) {
            // vertical line, check if x is the same
            return Math.abs(x - x1) < THRESHOLD && y >= Math.min(y1, y2) && y <= Math.max(y1, y2);
        } else if (y1 == y2) {
            // horizontal line, check if y is the same
            return Math.abs(y - y1) < THRESHOLD && x >= Math.min(x1, x2) && x <= Math.max(x1, x2);
        } else {
            // defining max and min
            double maxX = Math.max(x1, x2);
            double minX = Math.min(x1, x2);
            double maxY = Math.max(y1, y2);
            double minY = Math.min(y1, y2);

            if (x >= minX && x <= maxX && y >= minY && y <= maxY) {
                // check if the point lies on the line using the slope-intercept formula
                double slope = this.slopeCalculate();
                double intercept = y1 - slope * x1;
                return Math.abs(y - slope * x - intercept) < THRESHOLD;
            } else {
                return false;
            }
        }
    }


    /**
     * Is intersecting boolean.
     * This method checks if two lines (the current object and given line) intersect, including special cases
     * @param other the given line
     * @return true if the lines intersect, false otherwise
     */
    public boolean isIntersecting(Line other) {
        // Get the start and end points for both lines
        Point l1start = this.start();
        Point l1end = this.end();
        Point l2start = other.start();
        Point l2end = other.end();

        // if the lines are equal they obviously intersect
        if (this.equals(other)) {
            return true;
        }

        // if the lines share one point they obviously intersect
        if (this.shareOnePoint(other)) {
            return true;
        }

        // if there is inclusion they obviously intersect
        if (this.isInclusion(other)) {
            return true;
        }

        //checking special cases:
        // Check for vertical lines
        if (l1start.getX() == l1end.getX()) {
            if (l2start.getX() == l2end.getX()) {
                // Both lines are vertical, so they intersect if they share a point
                return l1start.equals(l2start) || l1start.equals(l2end)
                        || l1end.equals(l2start) || l1end.equals(l2end);
            } else {
                // Only the first line is vertical, so calculate the intersection point
                double m2 = other.slopeCalculate();
                double b2 = l2start.getY() - m2 * l2start.getX();
                double x = l1start.getX();
                double y = m2 * x + b2;

                // Check if the intersection point is within the bounds of both lines
                if ((y < Math.min(l1start.getY(), l1end.getY())) || (y > Math.max(l1start.getY(), l1end.getY()))) {
                    return false;
                }
                if ((x < Math.min(l2start.getX(), l2end.getX())) || (x > Math.max(l2start.getX(), l2end.getX()))) {
                    return false;
                }

                // Check if the intersection point is on the other line
                return (!(y < Math.min(l2start.getY(), l2end.getY()))) && (!(y > Math.max(l2start.getY(),
                        l2end.getY())));
            }
        } else if (l2start.getX() == l2end.getX()) {
            // Only the second line is vertical, so calculate the intersection point
            double m1 = this.slopeCalculate();
            double b1 = l1start.getY() - m1 * l1start.getX();
            double x = l2start.getX();
            double y = m1 * x + b1;

            // Check if the intersection point is within the bounds of both lines
            if ((y < Math.min(l2start.getY(), l2end.getY())) || (y > Math.max(l2start.getY(), l2end.getY()))) {
                return false;
            }
            if ((x < Math.min(l1start.getX(), l1end.getX())) || (x > Math.max(l1start.getX(), l1end.getX()))) {
                return false;
            }

            // Check if the intersection point is on the other line
            return (!(y < Math.min(l1start.getY(), l1end.getY()))) && (!(y > Math.max(l1start.getY(), l1end.getY())));
        } else if (l1start.getY() == l1end.getY() && (l2start.getY() == l2end.getY())) {
            // Both lines are horizontal, so they intersect if they share a point
            return l1start.equals(l2start) || l1start.equals(l2end)
                    || l1end.equals(l2start) || l1end.equals(l2end);
        } else {  // if there is no special case:
            // Calculate the slope and y-intercept for both lines
            double m1 = this.slopeCalculate();
            double b1 = l1start.getY() - m1 * l1start.getX();
            double m2 = other.slopeCalculate();
            double b2 = l2start.getY() - m2 * l2start.getX();

            // Check if the lines are parallel
            if ((Math.abs(m1 - m2)) < THRESHOLD) {
                if (!this.isInclusion(other)) {
                    return false;
                }
            }

            // Calculate the intersection point
            double x = (b2 - b1) / (m1 - m2);
            double y = m1 * x + b1;

            // Check if the intersection point is within the bounds of both lines
            if ((x < Math.min(l1start.getX(), l1end.getX())) || (x > Math.max(l1start.getX(), l1end.getX()))) {
                return false;
            }
            if ((x < Math.min(l2start.getX(), l2end.getX())) || (x > Math.max(l2start.getX(), l2end.getX()))) {
                return false;
            }
            if ((y < Math.min(l1start.getY(), l1end.getY())) || (y > Math.max(l1start.getY(), l1end.getY()))) {
                return false;
            }
            if ((y < Math.min(l2start.getY(), l2end.getY())) && (y > Math.max(l2start.getY(), l2end.getY()))) {
                return false;
            }
            return true;
            // If all checks pass, the lines intersect, and we return true
        }
    }

    /**
     * Intersection with point.
     * This method returns the coordinates of the intersection points
     * between two lines (the current object and given line)
     * @param other the given line
     * @return the point of intersection if the lines intersect, null if there is no intersection/there is inclusion
     */
    public Point intersectionWith(Line other) {
        // if the lines are equal there is no one specific intersection
        if (this.equals(other)) {
            return null;
        } else if ((this.shareOnePoint(other))) { //if there is one shared point we return it
            if (this.start.equals(other.start) || this.start.equals(other.end)) {
                return this.start();
            }
            if (this.end.equals(other.start) || this.end.equals(other.end)) {
                return this.end();
            }
        } else if (this.isInclusion(other) && (!(this.shareOnePoint(other)))) {
            // if one line include the other we return null because there is no one specific intersection
            return null;
        } else if (this.isIntersecting(other)) {  // if there is an intersection between the lines

            // Get the start and end points for both lines
            Point l1start = this.start();
            Point l1end = this.end();
            Point l2start = other.start();
            Point l2end = other.end();

            // Check for vertical lines
            if (l1start.getX() == l1end.getX() && (l2start.getX() != l2end.getX())) {
                // Only the first line is vertical, so calculate the intersection point
                double m2 = other.slopeCalculate();
                double b2 = l2start.getY() - m2 * l2start.getX();
                double x = l1start.getX();
                double y = m2 * x + b2;
                // returning the intersection point
                return new Point(x, y);
            } else if (l1start.getX() != l1end.getX() && (l2start.getX() == l2end.getX())) {
                // Only the second line is vertical, so calculate the intersection point
                double m1 = this.slopeCalculate();
                double b1 = l1start.getY() - m1 * l1start.getX();
                double x = l2start.getX();
                double y = m1 * x + b1;
                // returning the intersection point
                return new Point(x, y);
            } else {

                // Calculate the slope and y-intercept for both lines
                double m1 = this.slopeCalculate();
                double b1 = l1start.getY() - m1 * l1start.getX();
                double m2 = other.slopeCalculate();
                double b2 = l2start.getY() - m2 * l2start.getX();

                // Calculate the intersection point
                double x = (b2 - b1) / (m1 - m2);
                double y = m1 * x + b1;

                // returning the intersection point
                return new Point(x, y);
            }
        }
        // if we didn't find any point of intersection we return null
        return null;
    }

    /**
     * Equals boolean.
     * This method checks if two lines are equal (the current object and given line)
     * @param other the given line
     * @return true if the lines are equal, false otherwise
     */
    public boolean equals(Line other) {
        // Check if the start and end points of both lines are equal
        if (this.start().equals(other.start()) && this.end().equals(other.end())) {
            return true;
        }
        // Check if the start and end points of both lines are reversed
        if (this.start().equals(other.end()) && this.end().equals(other.start())) {
            return true;
        }
        // Check if the start of one line is equal to the end of the other, and vice versa
        return this.start().equals(other.end()) && this.end().equals(other.start());
        // If none of the above cases match, the lines are not equal
    }


    /**
     * closest Intersection To Start Of Line point.
     * If this line does not intersect with the rectangle, return null.
     * Otherwise, return the closest intersection point to the
     * start of the line.
     * @param rect the given rectangle
     * @return the closest intersection point to the start of the line
     */
    public Point closestIntersectionToStartOfLine(Rectangle rect) {
        java.util.List<Point> points = rect.intersectionPoints(this);
        if (points.isEmpty()) {
            return null;
        }
        Point closestPoint = points.get(0);
        double closestDistance = closestPoint.distance(this.start());
        for (Point point : points) {
            double distance = point.distance(this.start());
            if (distance < closestDistance) {
                closestDistance = distance;
                closestPoint = point;
            }
        }
        return closestPoint;
    }

    /**
     * Split line line [ ].
     * @param numSegments the num of segment that we want to split the line to
     * @return the array of segments
     */
    public Line[] splitLine(int numSegments) {
        // Calculate the length of each segment
        double segmentLength = this.length() / numSegments;

        // Calculate the direction vector of the line
        double dx = this.end().getX() - this.start().getX();
        double dy = this.end().getY() - this.start().getY();

        // Calculate the unit vector in the direction of the line
        double length = Math.sqrt(dx * dx + dy * dy);
        double ux = dx / length;
        double uy = dy / length;

        // Create an array to hold the split lines
        Line[] splitLines = new Line[numSegments];

        // Split the line into segments
        Point startPoint = this.start();
        for (int i = 0; i < numSegments; i++) {
            // Calculate the end point of the segment
            double x = startPoint.getX() + ux * segmentLength;
            double y = startPoint.getY() + uy * segmentLength;
            Point endPoint = new Point(x, y);

            // Create the segment and add it to the array
            splitLines[i] = new Line(startPoint, endPoint);

            // Update the start point for the next segment
            startPoint = endPoint;
        }
        return splitLines;
    }
}