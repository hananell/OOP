

import biuoop.DrawSurface;

/**
 * @author Hananel Hadad.
 */
public class LivesIndicator implements Sprite {
    private Rectangle body;
    private Counter remainingLives;

    /**
     * Constructor.
     *
     * @param rect            the body.
     * @param remainingLivess the lives.
     */
    public LivesIndicator(Rectangle rect, Counter remainingLivess) {
        this.body = rect;
        this.remainingLives = remainingLivess;
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
        Point rectCent = this.body.getCenter();
        String toPrint = "Lives: " + this.remainingLives.getValue();
        d.drawText((int) rectCent.getX() / 2, (int) rectCent.getY() + 5, toPrint, 18);
    }

    /**
     * notify the sprite that time has passed.
     * he says thank you for the information.
     */
    public void timePassed() {
    }

    /**
     * @return true if the player has any life left.
     */
    public boolean stillAlive() {
        return this.remainingLives.getValue() > 0;
    }

    /**
     * decrease one life from the player.
     */
    public void minusLife() {
        this.remainingLives.decrease(1);
    }
}
