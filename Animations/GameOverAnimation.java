package Animations;

import biuoop.DrawSurface;
import Game.Counter;

import java.awt.Color;

/**
 * The type Game over animation.
 * @author Roi Shukrun <address>roee.shukrun@live.biu.ac.il</address>
 * @version 1.5
 * @since 2023 -03-30
 */
public class GameOverAnimation implements Animation {
    private static final int GAME_SCORE_X_LOCATION = 70;
    private static final int GAME_SCORE_Y_LOCATION = 300;
    private final Counter currentScore;

    /**
     * Instantiates a new Game over animation.
     * @param currentScore the current score
     */
    public GameOverAnimation(Counter currentScore) {
        this.currentScore = currentScore;
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        d.setColor(Color.RED);
        d.drawText(GAME_SCORE_X_LOCATION, GAME_SCORE_Y_LOCATION,
                "Game Over. Your score is " + currentScore.getValue(), 50);
    }

    @Override
    public boolean shouldStop() {
        return false;
    }
}
