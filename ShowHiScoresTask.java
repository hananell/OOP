import biuoop.KeyboardSensor;

import static biuoop.KeyboardSensor.SPACE_KEY;

/**
 * author Hananel Hadad.
 */
public class ShowHiScoresTask implements Task<Void> {
    private AnimationRunner runner;
    private Animation highScoresAnimation;
    private KeyboardSensor keyboardSensor;

    /**
     * constructor.
     *
     * @param runner              animation runner.
     * @param highScoresAnimation to run.
     * @param ks                  the ks
     */
    public ShowHiScoresTask(AnimationRunner runner, Animation highScoresAnimation, KeyboardSensor ks) {
        this.runner = runner;
        this.highScoresAnimation = highScoresAnimation;
        this.keyboardSensor = ks;
    }

    /**
     * run the animation.
     *
     * @return nothing.
     */
    public Void run() {
        this.runner.run(new KeyPressStoppableAnimation(this.keyboardSensor, SPACE_KEY, this.highScoresAnimation));
        return null;
    }
}