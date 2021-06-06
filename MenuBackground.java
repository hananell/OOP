import biuoop.DrawSurface;

import java.awt.Color;

/**
 * author Hananel Hadad.
 */
public class MenuBackground implements Sprite {
    private String title;

    /**
     * Instantiates a new Menu background.
     *
     * @param titlee the titlee.
     */
    public MenuBackground(String titlee) {
        this.title = titlee;
    }


    /**
     * draw this object on given surface.
     *
     * @param d the surface.
     */
    public void drawOn(DrawSurface d) {
        d.setColor(Color.red);
        d.fillRectangle(0, 0, GameLevel.WIDTH, GameLevel.HEIGHT);
        d.setColor(Color.blue);
        d.drawText(300, 90, title, 40);
    }

    /**
     * notify the sprite that time has passed.
     */
    public void timePassed() {
        ;
    }

}
