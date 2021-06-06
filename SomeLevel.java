import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * The type Some level.
 */
public class SomeLevel implements LevelInformation {
    private int numberOfBalls;
    private List<Velocity> initialBallVelocities;
    private int paddleSpeed;
    private int paddleWidth;
    private String levelName;
    private Sprite backgroundSprite;
    private List<Block> blocks;
    private int numberOfBlocksToRemove;


    /**
     * build the name..
     *
     * @param s the line.
     * @throws Exception the exception
     */
    public void buildName(String s) throws Exception {
        String[] split = s.split(":");
        this.levelName = split[1];
        if (this.levelName == null) {
            throw new Exception("problem in game level");
        }
    }


    /**
     * build velocities and number of balls.
     *
     * @param s the line.
     * @throws Exception the exception
     */
    public void buildinitialBallVelocitiesAndNumber(String s) throws Exception { // ball_velocities:45,200 -45,200 0,300
        List<Velocity> ans = new ArrayList<>();
        String[] split = s.split(":");  // ball_velocities + 45,200 -45,200 0,300
        String[] splitTwo = split[1].split(" ");    // 45,200 + -45,200 + 0,300
        for (int i = 0; i < splitTwo.length; i++) {
            String[] splitThree = splitTwo[i].split(","); // 45 + 200
            Velocity v = Velocity.fromAngleAndSpeed(Integer.parseInt(splitThree[0]), Integer.parseInt(splitThree[1]));
            ans.add(v);
        }
        this.initialBallVelocities = ans;
        // the number of balls
        this.numberOfBalls = ans.size();
        if (this.initialBallVelocities.isEmpty() || this.numberOfBalls == 0) {
            throw new Exception("problem in balls");
        }
    }

    /**
     * build background.
     *
     * @param s the line.
     * @throws Exception the exception
     */
    public void buildBackground(String s) throws Exception {
        String[] split = s.split(":");
        //if it's a color
        if (split[1].charAt(0) == 'c') {
            this.backgroundSprite = new DullBackground(BlocksDefinitionReader.colori(split[1]));
        } else {
            //if it's an image
            BufferedImage img = null;
            try {
                img = ImageIO.read(new File(split[1].substring(6, split[1].length() - 1)));
                this.backgroundSprite = new ImageSprite(img);
            } catch (IOException e) {
                ;
            }
        }
        if (this.backgroundSprite == null) {
            throw new Exception("problem in background");
        }
    }

    /**
     * build paddle speed.
     *
     * @param s the line.
     * @throws Exception the exception
     */
    public void buildPaddleSpeed(String s) throws Exception {
        String[] split = s.split(":");
        this.paddleSpeed = Integer.parseInt(split[1]);
        if (this.paddleSpeed == 0) {
            throw new Exception("problem in paddle speed");
        }
    }

    /**
     * build paddle width.
     *
     * @param s the line.
     * @throws Exception the exception
     */
    public void buildPaddleWidth(String s) throws Exception {
        String[] split = s.split(":");
        this.paddleWidth = Integer.parseInt(split[1]);
        if (this.paddleWidth == 0) {
            throw new Exception("problem in paddle width");
        }
    }

    /**
     * build number of blocks to remove.
     *
     * @param s the line.
     * @throws Exception the exception
     */
    public void buildNumberOfBlocks(String s) throws Exception {
        String[] split = s.split(":");
        this.numberOfBlocksToRemove = Integer.parseInt(split[1]);
        if (this.numberOfBlocksToRemove == 0) {
            throw new Exception("problem in number of blocks to remove");
        }
    }

    /**
     * build list of blocks.
     *
     * @param list the list.
     * @throws Exception the exception
     */
    public void buildListOfBlocks(List<Block> list) throws Exception {
        this.blocks = list;
        if (this.blocks == null) {
            throw new Exception("problem in blocks");
        }
    }


    /**
     * @return number of balls.
     */
    public int numberOfBalls() {
        return this.numberOfBalls;
    }

    /**
     * @return The initial velocity of each ball.
     */
    public List<Velocity> initialBallVelocities() {
        return this.initialBallVelocities;
    }

    /**
     * @return speed of the paddle.
     */
    public int paddleSpeed() {
        return this.paddleSpeed;
    }

    /**
     * @return width of the paddle.
     */
    public int paddleWidth() {
        return this.paddleWidth;
    }

    /**
     * @return the name of the level.
     */
    public String levelName() {
        return this.levelName;
    }

    /**
     * @return the background.
     */
    public Sprite getBackground() {
        return this.backgroundSprite;
    }

    /**
     * @return The Blocks that make up this level, each block contains its size, color and location.
     */
    public List<Block> blocks() {
        return this.blocks;
    }

    /**
     * @return Number of levels that should be removed before the level is considered to be "cleared".
     * This number should be <= blocks.size();
     */
    public int numberOfBlocksToRemove() {
        return this.numberOfBlocksToRemove;
    }

}
