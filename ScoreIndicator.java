

import biuoop.DrawSurface;


import java.awt.Color;

/**
 * @author Hananel Hadad.
 */
public class ScoreIndicator implements Sprite {
    private Rectangle body;
    private Counter counter;

    /**
     * Constructor.
     *
     * @param rect   the body for this ScoreIndicator.
     * @param scores the counter for this ScoreIndicator.
     */
    public ScoreIndicator(Rectangle rect, Counter scores) {
        this.body = rect;
        this.counter = scores;
    }

    /**
     * @return the counter of this scoreIndicator.
     */
    public Counter getCounter() {
        return counter;
    }

    /**
     * add this ScoreIndicator to a given game.
     *
     * @param game the game.
     */
    public void addToGame(GameLevel game) {
        game.addSprite(this);
    }

    /**
     * draw this object on given surface.
     *
     * @param d the surface.
     */
    public void drawOn(DrawSurface d) {
        this.body.drawOn(d, Color.cyan, Color.black);
        Point rectCent = this.body.getCenter();
        String toPrint = "Score: " + this.counter.getValue();
        d.drawText((int) rectCent.getX() - 30, (int) rectCent.getY() + 5, toPrint, 18);
    }

    /**
     * notify the sprite that time has passed.
     * it's not listening.
     */
    public void timePassed() {

    }

    /**
     * increase the scores by 100.
     */
    public void hurray() {
        this.counter.increase(100);
    }

}
