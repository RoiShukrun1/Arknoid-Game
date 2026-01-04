package Listeners;

import Game.Counter;
import Objects.Ball;
import Objects.Block;

/**
 * The type Score tracking listener.
 * @author Roi Shukrun <address>roee.shukrun@live.biu.ac.il</address>
 * @version 1.5
 * @since 2023 -03-30
 */

public class ScoreTrackingListener implements HitListener {
    static final int SCORE_INCREASE = 5;
    // The current score of the game
    private final Counter currentScore;

    /**
     * Instantiates a new Score tracking listener.
     * @param scoreCounter the score counter
     */
    public ScoreTrackingListener(Counter scoreCounter) {
        this.currentScore = scoreCounter;
    }

    /**
     * Hit event.
     * when hit committed and block disappeared we increase the score of the game by 5 points
     * @param beingHit the hit block
     * @param hitter the hitter ball
     */
    public void hitEvent(Block beingHit, Ball hitter) {
        currentScore.increase(SCORE_INCREASE);
    }
}
