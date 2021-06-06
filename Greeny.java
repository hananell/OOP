import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * The type Greeny.
 *
 * @author Hananel Hadad
 */
public class Greeny implements LevelInformation {
    /**
     * @return number of balls at this level.
     */
    public int numberOfBalls() {
        return 3;
    }

    /**
     * @return list of Velocities at this level.
     */
    public List<Velocity> initialBallVelocities() {
        List<Velocity> ans = new ArrayList<>();
        for (int i = 1; i <= 3; i++) {
            Velocity v = Velocity.fromAngleAndSpeed(270 + (i * 180 / 4), 4);
            ans.add(v);
        }

        return ans;
    }

    /**
     * @return paddle speed at this level.
     */
    public int paddleSpeed() {
        return 7;
    }

    /**
     * @return paddle speed at this level.
     */
    public int paddleWidth() {
        return (int) GameLevel.BLOCKWIDTH * 2;
    }

    /**
     * @return name of this level.
     */
    public String levelName() {
        return "Greeny";
    }

    /**
     * @return the background.
     */
    public Sprite getBackground() {
        return new GreenyBackround();
    }

    /**
     * @return The Blocks that make up this level, each block contains its size, color and location.
     */
    public List<Block> blocks() {
        List<Block> ans = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 10 - i; j++) {
                double xTopLeft = GameLevel.WIDTH - 20 - ((j + 1) * GameLevel.BLOCKWIDTH);
                double yTopLeft = ((double) GameLevel.HEIGHT / 4) + (i * GameLevel.BLOCKHEIGHT);
                Point topLeft = new Point(xTopLeft, yTopLeft);
                Rectangle recti = new Rectangle(topLeft, GameLevel.BLOCKWIDTH, GameLevel.BLOCKHEIGHT);
                Block blocki = new Block(recti, 1, new Color(40 + i * 6, 150 + j * 10, 100 - i * 3));
                ans.add(blocki);
            }
        }
        return ans;
    }

    /**
     * @return Number of levels that should be removed before the level is considered to be "cleared".
     */
    public int numberOfBlocksToRemove() {
        return 40;
    }

    /**
     * return a color according to given number, that would be the line number on the surface.
     * if no match found to the number, return black.
     *
     * @param lineNum the number.
     * @return matching color, or black.
     */
    private Color colors(int lineNum) {
        switch (lineNum) {
            case 0:
                return Color.gray;
            case 1:
                return Color.red;
            case 2:
                return Color.yellow;
            case 3:
                return Color.cyan;
            case 4:
                return Color.pink;
            case 5:
                return Color.green;
            default:
                return Color.black;
        }
    }
}
