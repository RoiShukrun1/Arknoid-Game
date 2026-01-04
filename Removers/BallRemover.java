package Removers;

import Levels.GameLevel;
import Game.Counter;
import Listeners.HitListener;
import Objects.Ball;
import Objects.Block;

/**
 * The type Ball remover.
 * @author Roi Shukrun <address>roee.shukrun@live.biu.ac.il</address>
 * @version 1.5
 * @since 2023 -03-30
 * BallRemover is in charge of removing balls from the gameLevel, as well as keeping count
 * of the number of balls that remain.
 */

public class BallRemover implements HitListener {
    // Fields:
    private final GameLevel gameLevel;
    private final Counter remainingBalls;
    static final int DECREASE_NUMBER_OF_BALLS = 1;


    /**
     * Instantiates a new Ball remover.
     * @param gameLevel the gameLevel
     * @param remainingBalls the number of remaining balls in the games
     */
    public BallRemover(GameLevel gameLevel, Counter remainingBalls) {
        this.gameLevel = gameLevel;
        this.remainingBalls = remainingBalls;
    }

    /**
     * Hit Event.
     * removing the hitter ball (if he hit the "death-region" - bottom border block)
     * and decreasing the number of remaining blocks in the gameLevel
     * @param beingHit the being hit object
     * @param hitter the Ball that's doing the hitting
     */
    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        hitter.removeFromGame(gameLevel);
        remainingBalls.decrease(DECREASE_NUMBER_OF_BALLS);
    }
}
