package Listeners;

import Objects.Ball;
import Objects.Block;

/**
 * The interface Hit listener.
 * @author Roi Shukrun <address>roee.shukrun@live.biu.ac.il</address>
 * @version 1.5
 * @since 2023 -03-30
 */

public interface HitListener {
    /**
     * Hit event.
     * This method is called whenever the beingHit object is hit.

     * @param beingHit the being hit object
     * @param hitter the Ball that's doing the hitting
     */
    void hitEvent(Block beingHit, Ball hitter);
}
