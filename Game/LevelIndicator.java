package Game;

import Levels.GameLevel;
import biuoop.DrawSurface;
import Geometric.Rectangle;

import java.awt.Color;

/**
 * The type Level indicator.
 * @author Roi Shukrun <address>roee.shukrun@live.biu.ac.il</address>
 * @version 1.5
 * @since 2023 -03-30
 */
public class LevelIndicator implements Sprite {
    // Fields:
    private final Rectangle bounds;
    private final Color color;
    private final String levelName;
    /**
     * The Score indicator text size.
     */
    static final int SCORE_INDICATOR_TEXT_SIZE = 22;


    /**
     * Instantiates a new Score indicator.
     * @param bounds the bounds
     * @param color the color
     * @param levelName the level name
     */
    public LevelIndicator(Rectangle bounds, Color color, String levelName) {
        this.bounds = bounds;
        this.color = color;
        this.levelName = levelName;
    }

    /**
     * Draw on.
     * drawing the score on the surface of the game
     * @param surface the surface
     */
    @Override
    public void drawOn(DrawSurface surface) {
        surface.setColor(color);
        surface.drawText((int) bounds.getUpperLeft().getX(), (int) bounds.getUpperLeft().getY(),
                "Level Name: " + levelName, SCORE_INDICATOR_TEXT_SIZE);
    }

    /**
     * Time Passed.
     */
    @Override
    public void timePassed() {
    }

    /**
     * Add to gameLevel.
     * adding the score indicator to the gameLevel
     * @param gameLevel the gameLevel
     */
    public void addToGame(GameLevel gameLevel) {
        gameLevel.addSprite(this);
    }
}
