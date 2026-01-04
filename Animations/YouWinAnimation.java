package Animations;

import biuoop.DrawSurface;
import Game.Counter;

import java.awt.Color;

/**
 * The type You win animation.
 * @author Roi Shukrun <address>roee.shukrun@live.biu.ac.il</address>
 * @version 1.5
 * @since 2023 -03-30
 */
public class YouWinAnimation implements Animation {
    private static final int GAME_SCORE_X_LOCATION = 70;
    private static final int GAME_SCORE_Y_LOCATION = 300;
    private final Counter currentScore;

    /**
     * Instantiates a new You win animation.
     * @param currentScore the current score
     */
    public YouWinAnimation(Counter currentScore) {
        this.currentScore = currentScore;
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        d.setColor(Color.BLUE);
        d.drawText(GAME_SCORE_X_LOCATION, GAME_SCORE_Y_LOCATION,
                "You Win! Your score is " + currentScore.getValue(), 50);
    }

    @Override
    public boolean shouldStop() {
        return false;
    }
}