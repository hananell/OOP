

import biuoop.DrawSurface;

/**
 * author Hananel Hadad.
 */
public interface Sprite {

    /**
     * draw this object on given surface.
     *
     * @param d the surface.
     */
    void drawOn(DrawSurface d);

    /**
     * notify the sprite that time has passed.
     */
    void timePassed();
}
