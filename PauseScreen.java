

import biuoop.DrawSurface;

/**
 * @author Hananel Hadad
 */
public class PauseScreen implements Animation {

    /**
     * Constructor.
     */
    public PauseScreen() {
    }

    /**
     * do one frame of this boring animation.
     *
     * @param d the surface to draw on.
     */
    public void doOneFrame(DrawSurface d) {
        d.drawText(10, d.getHeight() / 2, "paused -- press space to continue", 32);
    }

    /**
     * determine if this animation should stop running.
     *
     * @return according to this.stop.
     */
    public boolean shouldStop() {
        return false;
    }
}