package Levels;

import Game.Sprite;
import Objects.Block;
import Physics.Velocity;

import java.util.List;

/**
 * The interface Level information.
 * @author Roi Shukrun <address>roee.shukrun@live.biu.ac.il</address>
 * @version 1.5
 * @since 2023 -03-30
 */
public interface LevelInformation {
    /**
     * Number of balls int.
     * @return the int
     */
    int numberOfBalls();

    /**
     * Initial ball velocities list.
     * The initial velocity of each ball
     * @return the list
     */
    List<Velocity> initialBallVelocities();

    /**
     * Paddle speed int.
     * @return the int
     */
    int paddleSpeed();

    /**
     * Paddle width int.
     * @return the int
     */
    int paddleWidth();

    /**
     * Level name string.
     * the level name will be displayed at the top of the screen.
     * @return the string
     */
    String levelName();

    /**
     * Gets background.
     * Returns a sprite with the background of the level
     * @return the background
     */
    Sprite getBackground();

    /**
     * Blocks list.
     * The Blocks that make up this level, each block contains
     * its size, color and location.
     * @return the list
     */
    List<Block> blocks();

    /**
     * Number of blocks to remove int.
     * Number of blocks that should be removed
     * before the level is considered to be "cleared".
     * @return the int
     */
    int numberOfBlocksToRemove();
}
