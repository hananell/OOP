

import biuoop.DrawSurface;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * The type Rectangle.
 *
 * @author Hananel Hadad.
 */
public class Rectangle {
    private Point topLeft;
    private double wide;
    private double high;

    /**
     * constructs a new rectangle with location and width/height.
     *
     * @param upperLeft the x upperLeft coordinate of the rectangle.
     * @param width     the y width of this rectangle.
     * @param height    the height of this rectangle.
     */
    public Rectangle(Point upperLeft, double width, double height) {
        this.topLeft = upperLeft;
        this.wide = width;
        this.high = height;
    }

    /**
     * copy-constructor.
     *
     * @param rect the baseobjects.Rectangle to copy.
     */
    public Rectangle(Rectangle rect) {
        this.topLeft = new Point(rect.topLeft);
        this.wide = rect.wide;
        this.high = rect.high;
    }


    /**
     * Gets width.
     *
     * @return the width of this rectangle.
     */
    public double getWidth() {
        return this.wide;
    }

    /**
     * Gets height.
     *
     * @return the height of this rectangle.
     */
    public double getHeight() {
        return this.high;
    }

    /**
     * Gets upper left.
     *
     * @return the upper-left point of this rectangle.
     */
    public Point getUpperLeft() {
        return this.topLeft;
    }

    /**
     * Gets upper right.
     *
     * @return the upper-right point of this rectangle.
     */
    public Point getUpperRight() {
        return new Point(this.getUpperLeft().getX() + this.getWidth(), this.getUpperLeft().getY());
    }

    /**
     * Gets lower left.
     *
     * @return the lower-left point of this rectangle.
     */
    public Point getLowerLeft() {
        return new Point(this.getUpperLeft().getX(), this.getUpperLeft().getY() + this.getHeight());
    }

    /**
     * Gets lower right.
     *
     * @return the lower-right point of this rectangle.
     */
    public Point getLowerRight() {
        return new Point(this.topLeft.getX() + this.getWidth(), this.getUpperLeft().getY() + this.getHeight());
    }

    /**
     * Gets upper side.
     *
     * @return the upper side of this rectangle.
     */
    public Line getUpperSide() {
        return new Line(this.getUpperLeft(), this.getUpperRight());
    }

    /**
     * Gets lower side.
     *
     * @return the lower side of this rectangle.
     */
    public Line getLowerSide() {
        return new Line(this.getLowerLeft(), this.getLowerRight());
    }

    /**
     * Gets left side.
     *
     * @return the left side of this rectangle.
     */
    public Line getLeftSide() {
        return new Line(this.getUpperLeft(), this.getLowerLeft());
    }

    /**
     * Gets right side.
     *
     * @return the right side of this rectangle.
     */
    public Line getRightSide() {
        return new Line(this.getUpperRight(), this.getLowerRight());
    }

    /**
     * Gets center.
     *
     * @return baseobjects.Point that is approximately the center of this rectangle.
     */
    public Point getCenter() {
        double xx = this.getUpperSide().middle().getX();
        double yy = this.getRightSide().middle().getY();
        return new Point(xx, yy);
    }


    /**
     * set the top left of this rectangle to a given point.
     *
     * @param newTopLeft the given point.
     */
    public void setUpperLeft(Point newTopLeft) {
        this.topLeft = newTopLeft;
    }

    /**
     * set the top right of this rectangle to a given point.
     *
     * @param newTopRight the given point.
     */
    public void setUpperRight(Point newTopRight) {
        double newX = newTopRight.getX() - this.getUpperRight().distance(this.getUpperLeft());
        this.topLeft = new Point(newX, newTopRight.getY());
    }

    /**
     * Return a (possibly empty) List of intersection points with the specified line.
     *
     * @param line the line to check intersection points with.
     * @return list of the intersection points.
     */
    public List<Point> intersectionPoints(Line line) {
        //initialize the list for the answer,
        //and variable to receive potential intersection point with each side of the rectangle.
        //for each side, we hold find the potential intersection point, than add it to the list only if it's not null.
        List<Point> inters = new ArrayList<>();
        Point potential;
        //upper side.
        potential = line.intersectionWith(this.getUpperSide());
        if (potential != null) {
            inters.add(new Point(potential));
        }
        //left side.
        potential = line.intersectionWith(this.getLeftSide());
        if (potential != null) {
            inters.add(new Point(potential));
        }
        //right side.
        potential = line.intersectionWith(this.getRightSide());
        if (potential != null) {
            inters.add(new Point(potential));
        }
        //lower side.
        potential = line.intersectionWith(this.getLowerSide());
        if (potential != null) {
            inters.add(new Point(potential));
        }

        return inters;
    }

    /**
     * In rect boolean.
     *
     * @param p a given point.
     * @return true if the given point is inside this rectangle, or false if it doesn't.
     */
    public boolean inRect(Point p) {
        if (p.getX() >= this.topLeft.getX() && p.getX() <= this.getUpperRight().getX()) {
            if (p.getY() >= this.topLeft.getY() && p.getY() <= this.getLowerLeft().getY()) {
                return true;
            }
        }
        return false;
    }

    /**
     * Point to rect side line.
     *
     * @param p a given point.
     * @return the side that the given point is on, or null if there  isn't one.
     */
    public Line pointToRectSide(Point p) {
        if (this.getUpperSide().inLine(p)) {
            return this.getUpperSide();
        }
        if (this.getLowerSide().inLine(p)) {
            return this.getLowerSide();
        }
        if (this.getRightSide().inLine(p)) {
            return this.getRightSide();
        }
        if (this.getLeftSide().inLine(p)) {
            return this.getLeftSide();
        }
        return null;
    }

    /**
     * Line to rect side line.
     *
     * @param lini a given line.
     * @return the side that the given point is on, or null if there  isn't one.
     */
    public Line lineToRectSide(Line lini) {
        if (lini.isIntersecting(this.getUpperSide())) {
            return this.getUpperSide();
        }
        if (lini.isIntersecting(this.getLowerSide())) {
            return this.getLowerSide();
        }
        if (lini.isIntersecting(this.getRightSide())) {
            return this.getRightSide();
        }
        if (lini.isIntersecting(this.getLeftSide())) {
            return this.getLeftSide();
        }
        return null;
    }

    /**
     * fill this rectangle with a given background, than draw it.
     *
     * @param surface    the surface.
     * @param background the background
     * @param border     the border
     */
    public void drawOn(DrawSurface surface, BlockBackground background, Color border) {
        if (surface == null || background == null) {
            System.out.println("null");
            return;
        }
        int x = (int) this.getUpperLeft().getX();
        int y = (int) this.getUpperLeft().getY();
        int width = (int) this.getWidth();
        int height = (int) this.getHeight();
        background.drawOn(surface, this);
        if (border != null) {
            surface.setColor(border);
            surface.drawRectangle(x, y, width, height);
        }
    }

    /**
     * fill this rectangle with a given color, than draw it.
     *
     * @param surface the surface.
     * @param color   the color
     * @param border  the border
     */
    public void drawOn(DrawSurface surface, Color color, Color border) {
        this.drawOn(surface, new BlockBackground(color), border);
    }

    /**
     * Generate random rectangle.
     *
     * @return the baseobjects.Rectangle generated
     */
    public static Rectangle generateRandomRec() {
        Random rand = new Random(); // create a random-number generator
        Point p = new Point(rand.nextInt(600), rand.nextInt(600));
        Rectangle r = new Rectangle(p, rand.nextInt(70), rand.nextInt(70));
        return r;
    }

    /**
     * move this rectangle to the left.
     *
     * @param d distance to move.
     */
    public void moveLeft(int d) {
        this.topLeft.setX(this.topLeft.getX() - d);
    }

    /**
     * move this rectangle to the right.
     *
     * @param d distance to move.
     */
    public void moveRight(int d) {
        this.topLeft.setX(this.topLeft.getX() + d);
    }


}
