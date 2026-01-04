package Removers;

import Levels.GameLevel;
import Game.Counter;
import Listeners.HitListener;
import Objects.Ball;
import Objects.Block;

/**
 * The type Block remover.
 * @author Roi Shukrun <address>roee.shukrun@live.biu.ac.il</address>
 * @version 1.5
 * @since 2023 -03-30
 * BlockRemover is in charge of removing blocks from the gameLevel, as well as keeping count
 * of the number of blocks that remain.
 */

public class BlockRemover implements HitListener {
    // Fields:
    private final GameLevel gameLevel;
    private final Counter remainingBlocks;
    static final int DECREASE_NUMBER_OF_BLOCKS = 1;


    /**
     * Instantiates a new Block remover.
     * @param gameLevel the current gameLevel
     * @param remainingBlocks the number of remaining blocks in the games
     */
    public BlockRemover(GameLevel gameLevel, Counter remainingBlocks) {
        this.gameLevel = gameLevel;
        this.remainingBlocks = remainingBlocks;
    }

    /**
     * Hit Event.
     * removing the hit block and decreasing the number of remaining blocks in the gameLevel
     * @param beingHit the being hit object
     * @param hitter the Ball that's doing the hitting
     */
    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        beingHit.removeHitListener(this);
        beingHit.removeFromGame(gameLevel);
        remainingBlocks.decrease(DECREASE_NUMBER_OF_BLOCKS);
    }
}