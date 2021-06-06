/**
 * The interface Hit listener.
 *
 * @author Hananel Hadad.
 */
public interface HitListener {
    /**
     * Hit event.
     *
     * @param beingHit the object being hit.
     * @param hitter   the Ball that's doing the hitting.
     */
    void hitEvent(Block beingHit, Ball hitter);
}
