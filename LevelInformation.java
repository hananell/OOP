import java.util.List;

/**
 * The interface Level information.
 *
 * @author Hananel Hadad
 */
public interface LevelInformation {
    /**
     * Number of balls int.
     *
     * @return number of balls.
     */
    int numberOfBalls();

    /**
     * Initial ball velocities list.
     *
     * @return The initial velocity of each ball.
     */
    List<Velocity> initialBallVelocities();

    /**
     * Paddle speed int.
     *
     * @return speed of the paddle.
     */
    int paddleSpeed();

    /**
     * Paddle width int.
     *
     * @return width of the paddle.
     */
    int paddleWidth();

    /**
     * Level name string.
     *
     * @return the name of the level.
     */
    String levelName();

    /**
     * Gets background.
     *
     * @return the background.
     */
    Sprite getBackground();

    /**
     * Blocks list.
     *
     * @return The Blocks that make up this level, each block contains its size, color and location.
     */
    List<Block> blocks();

    /**
     * Number of blocks to remove int.
     *
     * @return Number of levels that should be removed before the level is considered to be "cleared".
     * This number should be <= blocks.size();
     */
    int numberOfBlocksToRemove();
}
