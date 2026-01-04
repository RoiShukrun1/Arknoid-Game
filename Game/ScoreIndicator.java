package Game;

import Levels.GameLevel;
import biuoop.DrawSurface;
import Geometric.Rectangle;

import java.awt.Color;

/**
 * The type Score indicator.
 * @author Roi Shukrun <address>roee.shukrun@live.biu.ac.il</address>
 * @version 1.5
 * @since 2023 -03-30
 */

public class ScoreIndicator implements Sprite {
    // Fields:
    private final Counter scoreCounter;
    private final Rectangle bounds;
    private final Color color;
    static final int SCORE_INDICATOR_TEXT_SIZE = 22;


    /**
     * Instantiates a new Score indicator.
     * @param bounds the bounds
     * @param color the color
     * @param currentScore the current score
     */
    public ScoreIndicator(Rectangle bounds, Color color, Counter currentScore) {
        this.bounds = bounds;
        this.color = color;
        this.scoreCounter = currentScore;
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
                "Score: " + scoreCounter.getValue(), SCORE_INDICATOR_TEXT_SIZE);
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
