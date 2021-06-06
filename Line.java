

import biuoop.DrawSurface;

import java.awt.Color;
import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * @author Hananel Hadad.
 */
public class Line {
    private Point start;
    private Point end;

    /**
     * constructs a new line with two given points.
     *
     * @param start first given point.
     * @param end   second given point.
     */
    public Line(Point start, Point end) {
        if (start != null && end != null) {
            this.start = start;
            this.end = end;
        }
    }

    /**
     * constructs a new line, copied from other line.
     * for future use.
     *
     * @param other the line to copy.
     */
    public Line(Line other) {
        if (other != null) {
            this.start = other.start;
            this.end = other.end;
        }
    }

    /**
     * constructs a new line with four given values of points.
     *
     * @param x1 the x coordinate of the first point.
     * @param y1 the y coordinate of the first point.
     * @param x2 the x coordinate of the second point.
     * @param y2 the y coordinate of the second point.
     */
    public Line(double x1, double y1, double x2, double y2) {
        this.start = new Point(x1, y1);
        this.end = new Point(x2, y2);
    }

    /**
     * @return the length of this line.
     */
    public double length() {
        return (this.start.distance(this.end));
    }

    /**
     * initializes a point in the middle of this line and returns it.
     *
     * @return the point in the middle.
     */
    public Point middle() {
        double newX, newY;
        newX = (this.start.getX() + this.end.getX()) / 2;
        newY = (this.start.getY() + this.end.getY()) / 2;
        Point ans = new Point(newX, newY);
        return ans;
    }

    /**
     * @return the starting point of this line.
     */
    public Point start() {
        return this.start;
    }

    /**
     * @return the ending point of this line.
     */
    public Point end() {
        return this.end;
    }


    /**
     * round given double value.
     *
     * @param value  number to round.
     * @param places how many digits remain after the dot.
     * @return rounded value.
     */
    public static double round(double value, int places) {
        BigDecimal bd = new BigDecimal(Double.toString(value));
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }

    /**
     * checks whether a given point is on our line.
     *
     * @param p the given point.
     * @return true if the point is on our line, false otherwise.
     */
    public boolean inLine(Point p) {
        //round the values of the line and the point
        p.setX(round(p.getX(), 3));
        p.setY(round(p.getY(), 3));
        this.start.setX(round(this.start.getX(), 3));
        this.end.setX(round(this.end.getX(), 3));
        this.start.setY(round(this.start.getY(), 3));
        this.end.setY(round(this.end.getY(), 3));

        //option number one - the starting point of the line is bigger than the ending point in both x and y.
        if (this.start().getX() >= this.end().getX() && this.start().getY() >= this.end().getY()) {
            if (p.getX() <= this.start().getX() && p.getX() >= this.end().getX()) {
                if (p.getY() <= this.start().getY() && p.getY() >= this.end().getY()) {
                    return true;
                }
            }
        }

        //option number two - the starting point of the line is bigger than the ending point in x, but smaller in y.
        if (this.start().getX() >= this.end().getX() && this.start().getY() <= this.end().getY()) {
            if (p.getX() <= this.start().getX() && p.getX() >= this.end().getX()) {
                if (p.getY() >= this.start().getY() && p.getY() <= this.end().getY()) {
                    return true;
                }
            }
        }

        //option number three - the starting point of the line is bigger than the ending point in y, but smaller in x.
        if (this.start().getX() <= this.end().getX() && this.start().getY() >= this.end().getY()) {
            if (p.getX() >= this.start().getX() && p.getX() <= this.end().getX()) {
                if (p.getY() <= this.start().getY() && p.getY() >= this.end().getY()) {
                    return true;
                }
            }
        }

        //option number four - the starting point of the line is smaller than the ending point in both x and y.
        if (this.start().getX() <= this.end().getX() && this.start().getY() <= this.end().getY()) {
            if (p.getX() >= this.start().getX() && p.getX() <= this.end().getX()) {
                if (p.getY() >= this.start().getY() && p.getY() <= this.end().getY()) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * checks whether this line intersect with other line.
     *
     * @param other the line to check intersection with.
     * @return true if the lines intersect, and false otherwise.
     */
    public boolean isIntersecting(Line other) {
        Point p = new Point(this.intersectionWith(other));
        if (p.getX() == 0 && p.getY() == 0) {
            if (!(new Line(this).inLine(new Point(p)) && new Line(other).inLine(new Point(p)))) {
                return false;
            }
        }
        return true;
    }

    /**
     * find and return the intersection point of this line with another line.
     * assuming this line is vertical.
     *
     * @param other the other line.
     * @return the intersection point, or null if there isn't one.
     */
    public Point verticalInter(Line other) {
        // m:   m = (y2-y1)/(x2-x1)
        double m = (other.end.getY() - other.start.getY()) / (other.end.getX() - other.start.getX());
        // b:   y-y1 = m(x-x1)   =>if x=0:    y = -mx1+y1
        double b = -(m * other.start.getX()) + other.start.getY();
        //potential point: x of the vertical line, y that comes according to the equation of the second line.
        Point potential = new Point(this.start.getX(), m * this.start.getX() + b);
        if (new Line(this).inLine(new Point(potential)) && new Line(other).inLine(new Point(potential))) {
            return potential;

        }
        return null;
    }


    /**
     * checks whether this line intersect with other line.
     * if it does, return the intersection point, if it doesn't return null.
     * the code is much like the previous function.
     *
     * @param other the line to check intersection with.
     * @return the intersection point, or null if the lines don't intersect.
     */
    public Point intersectionWith(Line other) {
        if (other == null) {
            return null;
        }
        //treat vertical lines.
        if (round(this.start().getX(), 3) == round(this.end().getX(), 3)
                &&
                round(other.start().getX(), 3) == round(other.end().getX(), 3)) {
            return null;
        }
        if (round(this.start().getX(), 3) == round(this.end().getX(), 3)) {
            return this.verticalInter(other);
        }
        if (round(other.start().getX(), 3) == round(other.end().getX(), 3)) {
            return other.verticalInter(this);
        }
        //calculates the steepness of the lines.
        double m1 = (this.start.getY() - this.end.getY()) / (this.start.getX() - this.end.getX());
        double m2 = (other.start.getY() - other.end.getY()) / (other.start.getX() - other.end.getX());
        //if they are the same, it's the same line or two overlapping lines - returns null.
        if (Double.compare(m1, m2) == 0) {
            return null;
        }
        //calculates the b value of the lines, from the line equation y = mx + b.
        double b1 = -(m1 * this.start.getX()) + this.start.getY();
        double b2 = -(m2 * other.start.getX()) + other.start.getY();
        //calculates the coordinates of the intersection point. presuming the lines are endless.
        double xx = (b2 - b1) / (m1 - m2);
        double yy = m1 * xx + b1;
        Point inter = new Point(xx, yy);
        //if the potential intersection point is in both lines, return it.
        if (new Line(this).inLine(new Point(inter)) && new Line(other).inLine(new Point(inter))) {
            return inter;
        }
        return null;
    }

    /**
     * checks whether this line is equal to other line.
     * the lines are equal only if both their start and their ends points are equal.
     *
     * @param other the line to compare to.
     * @return true if the lines are equal, and false otherwise.
     */
    public boolean equals(Line other) {
        if (other == null) {
            return false;
        }
        return (this.start.equals(other.start) && this.end.equals(other.end));
    }

    /**
     * check all intersection points of this line with given rectangle.
     * and return the one closest to the start of this line.
     *
     * @param rect the rectangle to check intersection with.
     * @return the return the closest intersection point to the start of the line, or null if there isn't one.
     */
    public Point closestIntersectionToStartOfLine(Rectangle rect) {
        if (rect == null) {
            return null;
        }
        //initialize baseobjects.Point to hold the minimal intersection point,
        //and another point to hold the intersection point of each other side, and maybe replace the minimal point.
        Point minInter = this.intersectionWith(rect.getUpperSide());
        Point curInter;

        curInter = this.intersectionWith(rect.getLowerSide());
        if (curInter != null) {
            if (minInter == null) {
                minInter = new Point(curInter);
            } else {
                if (this.start().distance(curInter) < this.start().distance(minInter)) {
                    minInter = new Point(curInter);
                }
            }
        }

        curInter = this.intersectionWith(rect.getRightSide());
        if (curInter != null) {
            if (minInter == null) {
                minInter = new Point(curInter);
            } else {
                if (this.start().distance(curInter) < this.start().distance(minInter)) {
                    minInter = new Point(curInter);
                }
            }
        }

        curInter = this.intersectionWith(rect.getLeftSide());
        if (curInter != null) {
            if (minInter == null) {
                minInter = new Point(curInter);
            } else {
                if (this.start().distance(curInter) < this.start().distance(minInter)) {
                    minInter = new Point(curInter);
                }
            }
        }
        if (minInter == null) {
            return null;
        }
        return new Point(minInter);
    }


    /**
     * draw this line on a given surface.
     *
     * @param surface the surface.
     */
    public void drawOn(DrawSurface surface) {
        surface.setColor(Color.GRAY);
        int shorterLine = (int) this.start.getX();
        surface.drawLine(shorterLine, (int) this.start.getY(), (int) this.end.getX(), (int) this.end.getY());
    }

    /**
     * receive a point on this line, assume this line is horizontal and the point is on the line.
     * return a number: 1 to the leftest fifth, 2 for the second from leftest, and so on.
     *
     * @param p the point.
     * @return a number that reflects the fifth the given point is on.
     */
    public int fifthNum(Point p) {
        double lengthi = this.end.getX() - this.start.getX();
        double cur = p.getX() - this.start.getX();
        if (cur < lengthi / 5) {
            return 1;
        }
        if (cur < (lengthi / 5) * 2) {
            return 2;
        }
        if (cur < (lengthi / 5) * 3) {
            return 3;
        }
        if (cur < (lengthi / 5) * 4) {
            return 4;
        }
        return 5;
    }

    /**
     * print this line.
     */
    public void printLine() {
        System.out.print("start = ");
        this.start.printP();
        System.out.print("end = ");
        this.end.printP();
    }

}
