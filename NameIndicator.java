import biuoop.DrawSurface;

/**
 * @author Hananel Hadad
 */
public class NameIndicator implements Sprite {
    private Rectangle body;
    private String name;

    /**
     * Constructor.
     *
     * @param rect  the body.
     * @param namee the name.
     */
    public NameIndicator(Rectangle rect, String namee) {
        this.body = rect;
        this.name = namee;
    }

    /**
     * add this NameIndicator to a given game.
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
        String toPrint = "Level Name: " + this.name;
        d.drawText((int) rectCent.getX() * 5 / 4, (int) rectCent.getY() + 5, toPrint, 18);
    }

    /**
     * notify the sprite that time has passed.
     * he says thank you for the information.
     */
    public void timePassed() {
    }
}
