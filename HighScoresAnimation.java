import biuoop.DrawSurface;

import java.awt.Color;

/**
 * author Hananel Hadad.
 */
public class HighScoresAnimation implements Animation {
    private HighScoresTable scores;

    /**
     * Constructor.
     *
     * @param scoress the scoress
     */
    public HighScoresAnimation(HighScoresTable scoress) {
        this.scores = scoress;
    }

    /**
     * do one frame of this boring animation.
     *
     * @param d the surface to draw on.
     */
    public void doOneFrame(DrawSurface d) {
        d.setColor(Color.cyan);
        d.fillRectangle(0, 0, GameLevel.WIDTH, GameLevel.HEIGHT);
        d.setColor(Color.red);
        d.drawText(300, 90, "High Scores", 40);
        d.setColor(Color.black);
        int ind = 0;
        for (ScoreInfo infi : scores.getHighScores()) {
            String name = infi.getName();
            int score = infi.getScore();
            ind++;
            d.drawText(250, 120 + (ind * 80), name + " - " + score, 32);
        }
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
