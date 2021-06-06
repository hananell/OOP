

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

import java.awt.Color;

/**
 * author Hananel Hadad.
 */
public class Paddle implements Collidable, Sprite {
    private Rectangle body;
    private KeyboardSensor keyboardSensor;
    private int speed;

    /**
     * construct a new paddle in a given GUI.
     *
     * @param rect   the body for this rectangle.
     * @param ks     the keyboard.
     * @param speedd the speed.
     */
    public Paddle(Rectangle rect, KeyboardSensor ks, int speedd) {
        this.body = rect;
        this.keyboardSensor = ks;
        this.speed = speedd;
    }

    /**
     * move this paddle to the left.
     */
    public void moveLeft() {
        this.body.moveLeft(this.speed);
    }

    /**
     * move this paddle to the right.
     */
    public void moveRight() {
        this.body.moveRight(this.speed);
    }

    /**
     * inform this paddle that time has passed.
     * if left or right key is pressed, move accordingly.
     */
    public void timePassed() {
        //before moving left or right, check if there is place left before we reach the edge block.
        //if there isn't, move only till the block.
        if (this.keyboardSensor.isPressed(KeyboardSensor.LEFT_KEY)) {
            if (this.body.getUpperLeft().getX() < 25) {
                this.body.setUpperLeft(new Point(20, body.getUpperLeft().getY()));
            } else {
                this.moveLeft();
            }
        }
        if (this.keyboardSensor.isPressed(KeyboardSensor.RIGHT_KEY)) {
            if (this.body.getUpperRight().getX() > GameLevel.WIDTH - 25) {
                this.body.setUpperRight(new Point(GameLevel.WIDTH - 20, body.getUpperLeft().getY()));
            } else {
                this.moveRight();
            }
        }
    }

    /**
     * draw this paddle on a given surface.
     *
     * @param d the surface.
     */
    public void drawOn(DrawSurface d) {
        this.body.drawOn(d, Color.yellow, Color.black);
    }

    /**
     * @return the rectangle body of this paddle.
     */
    public Rectangle getCollisionRectangle() {
        return this.body;
    }

    /**
     * Notify the object that we collided with it at collisionPoint with a given velocity.
     * than advance the ball a little to get its center out of the block.
     *
     * @param hitter          the ball hitting this paddle.
     * @param collisionPoint  were the collision occur.
     * @param currentVelocity the current velocity.
     * @return new velocity expected after the hit (based on the force the object inflicted on us).
     */
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        Velocity updatedVelocity = new Velocity(currentVelocity);

        //collision with upper side.
        if (this.body.getUpperSide().inLine(collisionPoint) && currentVelocity.getDy() > 0) {
            int fifth = this.body.getUpperSide().fifthNum(collisionPoint);
            switch (fifth) {
                case 1:
                    updatedVelocity = Velocity.fromAngleAndSpeed(300, currentVelocity.valToSpeed());
                    break;
                case 2:
                    updatedVelocity = Velocity.fromAngleAndSpeed(330, currentVelocity.valToSpeed());
                    break;
                case 3:
                    updatedVelocity.setY(-currentVelocity.getDy());
                    break;
                case 4:
                    updatedVelocity = Velocity.fromAngleAndSpeed(30, currentVelocity.valToSpeed());
                    break;
                case 5:
                    updatedVelocity = Velocity.fromAngleAndSpeed(60, currentVelocity.valToSpeed());
                    break;
                default:
                    ;
            }
        }

        // collision with right and left sides - normally, like block.
        if (this.body.getRightSide().inLine(collisionPoint) && currentVelocity.getDx() < 0) {
            updatedVelocity = new Velocity(currentVelocity);
            updatedVelocity.setX(-currentVelocity.getDx());
        }
        if (this.body.getLeftSide().inLine(collisionPoint) && currentVelocity.getDx() > 0) {
            updatedVelocity = new Velocity(currentVelocity);
            updatedVelocity.setX(-currentVelocity.getDx());
        }

        return updatedVelocity;
    }

    /**
     * Add this paddle to the game.
     *
     * @param g the game.
     */
    public void addToGame(GameLevel g) {
        g.addSprite(this);
        g.addCollidable(this);
    }
}
