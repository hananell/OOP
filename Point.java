

/**
 * @author Hananel Hadad.
 */
public class Point {
    private double x;
    private double y;

    /**
     * constructs a new point.
     *
     * @param x the x coordinate.
     * @param y the y coordinate.
     */
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * constructs a new point, copy of other point.
     *
     * @param p the point to copy.
     */
    public Point(Point p) {
        if (p != null) {
            this.x = p.x;
            this.y = p.y;
        }
    }

    /**
     * calculates the distance between this poont and and other point.
     *
     * @param other the point to calculate the distance from.
     * @return the distance. if this point or the other point are null, return -1.
     */
    public double distance(Point other) {
        if (other == null) {
            return -1;
        }
        return (Math.sqrt(Math.pow(this.x - other.x, 2) + Math.pow(this.y - other.y, 2)));
    }

    /**
     * determines whether this point is equal to other point.
     *
     * @param other point to compare to.
     * @return true if they are equals, and false if not.
     */
    public boolean equals(Point other) {
        if (other == null) {
            return false;
        }
        int xx = Double.compare(this.getX(), other.getX());
        int yy = Double.compare(this.getY(), other.getY());
        if (xx == 0 && yy == 0) {
            return true;
        }
        return false;
    }

    /**
     * @return the x coordinate of this point.
     */
    public double getX() {
        return this.x;
    }

    /**
     * @return the y coordinate of this point.
     */
    public double getY() {
        return this.y;
    }

    /**
     * change the x coordinate of this point to the given value.
     *
     * @param xx the value to set.
     */
    public void setX(double xx) {
        this.x = xx;
    }

    /**
     * change the y coordinate of this point to the given value.
     *
     * @param yy the value to set.
     */
    public void setY(double yy) {
        this.y = yy;
    }


    /**
     * print this point.
     */
    public void printP() {
        System.out.println("(" + this.getX() + "," + this.getY() + ")");
    }

}