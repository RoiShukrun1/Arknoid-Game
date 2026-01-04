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
 * The type Direct hit level.
 * @author Roi Shukrun <address>roee.shukrun@live.biu.ac.il</address>
 * @version 1.5
 * @since 2023 -03-30
 */
public class DirectHitLevel implements LevelInformation {
    // Magic numbers:
    static final int DEFAULT_PADDLE_WIDTH = 100;
    static final int DEFAULT_PADDLE_SPEED = 5;
    static final int DEFAULT_BALL_DX = 0;
    static final int DEFAULT_BALL_DY = 5;
    static final int DEFAULT_BLOCK_X = 370;
    static final int DEFAULT_BLOCK_Y = 150;
    static final int DEFAULT_BLOCK_WIDTH = 30;
    static final int DEFAULT_BLOCK_HEIGHT = 30;
    // The background of the level
    private final DirectHitStyle directHitStyle = new DirectHitStyle();
    @Override
    public int numberOfBalls() {
        return initialBallVelocities().size();
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        List<Velocity> ballVelocity = new ArrayList<>();
        ballVelocity.add(new Velocity(DEFAULT_BALL_DX, DEFAULT_BALL_DY));
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
        return "Direct Hit";
    }

    @Override
    public Sprite getBackground() {
        return directHitStyle;
    }

    @Override
    public List<Block> blocks() {
        List<Block> blocks = new ArrayList<>();
        blocks.add(new Block(new Rectangle(new Point(DEFAULT_BLOCK_X, DEFAULT_BLOCK_Y), DEFAULT_BLOCK_WIDTH,
                DEFAULT_BLOCK_HEIGHT), Color.red));
        return blocks;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return blocks().size();
    }
}
