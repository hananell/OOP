
/**
 * @author Hananel Hadad.
 */
public class ScoreInfo {
    private String name;
    private int score;

    /**
     * Constructor.
     * @param namee name.
     * @param scoree score.
     */
    public ScoreInfo(String namee, int scoree) {
        this.name = namee;
        this.score = scoree;
    }

    /**
     * Constructor.
     * @param namee name.
     * @param scoree score.
     */
    public ScoreInfo(int scoree, String namee) {
        this.name = namee;
        this.score = scoree;
    }

    /**
     * @return the name.
     */
    public String getName() {
        return this.name;
    }

    /**
     * @return the score.
     */
    public int getScore() {
        return this.score;
    }
}