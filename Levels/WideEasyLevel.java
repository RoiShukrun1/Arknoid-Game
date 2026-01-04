package Levels;

import Game.Sprite;
import Geometric.Point;
import Geometric.Rectangle;
import Objects.Block;
import Physics.Velocity;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * The type Wide easy level.
 * @author Roi Shukrun <address>roee.shukrun@live.biu.ac.il</address>
 * @version 1.5
 * @since 2023 -03-30
 */
public class WideEasyLevel implements LevelInformation {
    /**
     * The Default block height.
     */
    static final int DEFAULT_BLOCK_HEIGHT = 30; // The default height of a block.
    /**
     * The Default block width.
     */
    static final int DEFAULT_BLOCK_WIDTH = 50; // The default width of a block.
    /**
     * The Default y value of block.
     */
    static final int DEFAULT_Y_VALUE_OF_BLOCK = 240; // The default y value of the top row of blocks.
    /**
     * The Default x value of block.
     */
    static final int DEFAULT_X_VALUE_OF_BLOCK = 725; // The default x value of the rightmost column of blocks.
    /**
     * The Default num of blocks.
     */
    static final int DEFAULT_NUM_OF_BLOCKS = 15;
    static final int DEFAULT_PADDLE_WIDTH = 600;
    static final int DEFAULT_PADDLE_SPEED = 5;
    static final int DEFAULT_BALL_SPEED = 5;
    static final int DEFAULT_NUM_OF_BALLS = 10;

    private final WideEasyStyle wideEasyStyle = new WideEasyStyle();

    @Override
    public int numberOfBalls() {
        return initialBallVelocities().size();
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        List<Velocity> ballVelocity = new ArrayList<>();
        double angle;
        int angleChange1 = 10;
        int angleChange2 = 20;
        for (int i = 0; i < DEFAULT_NUM_OF_BALLS; i++) {
            if (i % 2 == 0) {
                angle = angleChange1;
                angleChange1 = angleChange1 + 9;
            } else {
                angle = -angleChange2;
                angleChange2 = angleChange2 + 9;
            }
            Velocity velocity = Velocity.fromAngleAndSpeed(angle, DEFAULT_BALL_SPEED);
            ballVelocity.add(velocity);
        }
        return ballVelocity;
    }

    @Override
    public int paddleSpeed() {
        return DEFAULT_PADDLE_SPEED;
    }

    @Override
    public int paddleWidth() {
        return DEFAULT_PADDLE_WIDTH;
    }

    @Override
    public String levelName() {
        return "Wide Easy";
    }

    @Override
    public Sprite getBackground() {
        return wideEasyStyle;
    }

    @Override
    public List<Block> blocks() {
        List<Block> blocks = new ArrayList<>();
        // create blocks for the layers of blocks in the game
        int xValue = DEFAULT_X_VALUE_OF_BLOCK;
        Color[] colors = {Color.CYAN, Color.CYAN, Color.PINK, Color.PINK, Color.BLUE, Color.BLUE, Color.GREEN,
                Color.GREEN, Color.GREEN, Color.YELLOW, Color.YELLOW, Color.ORANGE, Color.ORANGE, Color.RED, Color.RED};
        for (int i = 0; i < DEFAULT_NUM_OF_BLOCKS; i++) {
            // create a new block with the appropriate color
            Block block = new Block(new Rectangle(new Point(xValue, DEFAULT_Y_VALUE_OF_BLOCK), DEFAULT_BLOCK_WIDTH,
                    DEFAULT_BLOCK_HEIGHT), colors[i]);
            blocks.add(block);
            xValue = xValue - DEFAULT_BLOCK_WIDTH;
        }
        return blocks;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return blocks().size();
    }
}
