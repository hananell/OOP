import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

/**
 * author Hananel Hadad.
 */
public class KeyPressStoppableAnimation implements Animation {
    private KeyboardSensor keyboardSensor;
    private String string;
    private Animation animation;
    private boolean isAlreadyPressed = true;

    /**
     * Constructor.
     *
     * @param sensor     keyboardSensor.
     * @param key        key.
     * @param animationn animation.
     */
    public KeyPressStoppableAnimation(KeyboardSensor sensor, String key, Animation animationn) {
        this.keyboardSensor = sensor;
        this.string = key;
        this.animation = animationn;
    }

    /**
     * do one frame of this animation.
     *
     * @param d the surface to draw on.
     */
    public void doOneFrame(DrawSurface d) {
        this.animation.doOneFrame(d);
        if (!this.keyboardSensor.isPressed(this.string)) {
            this.isAlreadyPressed = false;
        }
    }

    /**
     * determine if the animation should stop.
     *
     * @return true if it should.
     */
    public boolean shouldStop() {
        return this.keyboardSensor.isPressed(this.string) && !this.isAlreadyPressed;
    }
}
