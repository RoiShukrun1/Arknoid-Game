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
 * The type Green 3 level.
 * @author Roi Shukrun <address>roee.shukrun@live.biu.ac.il</address>
 * @version 1.5
 * @since 2023 -03-30
 */
public class Green3Level implements LevelInformation {
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
    static final int DEFAULT_Y_VALUE_OF_BLOCK = 130; // The default y value of the top row of blocks.
    /**
     * The Default x value of block.
     */
    static final int DEFAULT_X_VALUE_OF_BLOCK = 725; // The default x value of the rightmost column of blocks.
    /**
     * The Default max blocks in layer.
     */
    static final int DEFAULT_MAX_BLOCKS_IN_LAYER = 10;
    /**
     * The Default num of layers.
     */
    static final int DEFAULT_NUM_OF_LAYERS = 5;
    static final int DEFAULT_PADDLE_WIDTH = 100;
    static final int DEFAULT_PADDLE_SPEED = 5;
    static final int DEFAULT_BALL_SPEED = 5;
    private final Green3Style green3Style = new Green3Style();

    @Override
    public int numberOfBalls() {
        return initialBallVelocities().size();
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        List<Velocity> ballVelocity = new ArrayList<>();
        double angle1 = -45;
        double angle2 = 45;
        Velocity velocity1 = Velocity.fromAngleAndSpeed(angle1, DEFAULT_BALL_SPEED);
        Velocity velocity2 = Velocity.fromAngleAndSpeed(angle2, DEFAULT_BALL_SPEED);
        ballVelocity.add(new Velocity(velocity1.getDx(), velocity1.getDy()));
        ballVelocity.add(new Velocity(velocity2.getDx(), velocity2.getDy()));
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
        return "Green 3";
    }

    @Override
    public Sprite getBackground() {
        return green3Style;
    }

    @Override
    public List<Block> blocks() {
        List<Block> blocks = new ArrayList<>();
        // create blocks for the layers of blocks in the game
        int xValue = DEFAULT_X_VALUE_OF_BLOCK;
        int yValue = DEFAULT_Y_VALUE_OF_BLOCK;
        int numOfBlocks = DEFAULT_MAX_BLOCKS_IN_LAYER;
        Color[] colors = {Color.GRAY, Color.RED, Color.YELLOW, Color.BLUE, Color.white};
        for (int j = 0; j < DEFAULT_NUM_OF_LAYERS; j++) {
            for (int i = 0; i < numOfBlocks; i++) {
                // create a new block with the appropriate color
                Block block = new Block(new Rectangle(new Point(xValue, yValue), DEFAULT_BLOCK_WIDTH,
                        DEFAULT_BLOCK_HEIGHT), colors[j]);
                blocks.add(block);
                xValue = xValue - DEFAULT_BLOCK_WIDTH;
            }
            xValue = DEFAULT_X_VALUE_OF_BLOCK;
            yValue = yValue + DEFAULT_BLOCK_HEIGHT;
            numOfBlocks--;
        }
        return blocks;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return blocks().size();
    }
}
