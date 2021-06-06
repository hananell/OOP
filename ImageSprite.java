import biuoop.DrawSurface;

import java.awt.Image;

/**
 * The type Image sprite.
 *
 * @author Hananel Hadad.
 */
public class ImageSprite implements Sprite {
    private Image image;

    /**
     * Instantiates a new Image sprite.
     *
     * @param imagee the imagee
     */
    public ImageSprite(Image imagee) {
        this.image = imagee;
    }

    /**
     * draw this object on given surface.
     *
     * @param d the surface.
     */
    public void drawOn(DrawSurface d) {
        d.drawImage(0, 0, this.image);
    }

    /**
     * notify the sprite that time has passed.
     */
    public void timePassed() {
        ;
    }
}
