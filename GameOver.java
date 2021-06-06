import biuoop.DrawSurface;

import java.awt.Color;

/**
 * The type Game over.
 *
 * @author Hananel Hadad
 */
public class GameOver implements Animation {
    private int score;

    /**
     * Constructor.
     *
     * @param scores the scores reached.
     */
    public GameOver(int scores) {
        this.score = scores;
    }

    /**
     * do one frame of this boring animation.
     *
     * @param d the surface to draw on.
     */
    public void doOneFrame(DrawSurface d) {
        d.setColor(Color.white);
        d.fillRectangle(0, 0, GameLevel.WIDTH, GameLevel.HEIGHT);
        d.setColor(Color.red);
        String toPrint = "Game Over. Your score is " + score;
        d.drawText(100, 300, toPrint, 50);
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
