import biuoop.DrawSurface;

import java.awt.Color;

/**
 * The type Greeny backround.
 *
 * @author Hananel Hadad
 */
public class GreenyBackround implements Sprite {
    /**
     * draw this object on given surface.
     *
     * @param d the surface.
     */
    public void drawOn(DrawSurface d) {
        d.setColor(new Color(39, 188, 44));
        d.fillRectangle(20, 20 + (int) GameLevel.BLOCKHEIGHT, GameLevel.WIDTH - 40, GameLevel.HEIGHT);
        d.setColor(Color.BLACK);
        d.fillRectangle(60, 400, 150, 400);
        for (int r = 0, p = 0; r < 12; r++, p += 30) {
            for (int i = 0, j = 0; i < 5; i++, j += 30) {
                d.setColor(Color.white);
                d.fillRectangle(65 + j, 405 + p, 20, 15);
            }
        }
        d.setColor(Color.darkGray);
        d.fillRectangle(120, 320, 30, 80);
        d.setColor(Color.lightGray);
        d.fillRectangle(130, 140, 10, 180);
        d.setColor(Color.yellow);
        d.fillCircle(135, 125, 15);
        d.setColor(Color.red);
        d.fillCircle(135, 125, 9);
        d.setColor(Color.white);
        d.fillCircle(135, 125, 4);
    }

    /**
     * notify the sprite that time has passed.
     * do nothing.
     */
    public void timePassed() {

    }
}
