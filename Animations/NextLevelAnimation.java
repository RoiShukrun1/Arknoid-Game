package Animations;

import biuoop.DrawSurface;
import Game.Counter;

import java.awt.Color;

/**
 * The type Next level animation.
 * @author Roi Shukrun <address>roee.shukrun@live.biu.ac.il</address>
 * @version 1.5
 * @since 2023 -03-30
 */
public class NextLevelAnimation implements Animation {
    private static final int GAME_SCORE_X_LOCATION = 70;
    private static final int GAME_SCORE_Y_LOCATION = 300;
    private final Counter currentScore;
    private final long animationDuration; // Duration of the animation in milliseconds
    private boolean shouldStop = false;

    /**
     * Instantiates a new Next level animation.
     * @param currentScore the current score
     * @param animationDuration the duration of the animation in milliseconds
     */
    public NextLevelAnimation(Counter currentScore, long animationDuration) {
        this.currentScore = currentScore;
        this.animationDuration = animationDuration;
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        // Calculate the elapsed time since the animation started
        long currentTime = System.currentTimeMillis();
        // Start time of the animation
        long startTime = 0;
        long elapsedTime = currentTime - startTime;

        // Draw the animation text
        d.setColor(Color.BLUE);
        d.drawText(GAME_SCORE_X_LOCATION, GAME_SCORE_Y_LOCATION,
                "Next Level! Your score is " + currentScore.getValue(), 50);

        // Check if the animation duration has elapsed
        if (elapsedTime >= animationDuration) {
            shouldStop = true; // Stop the animation
        }
    }

    @Override
    public boolean shouldStop() {
        return shouldStop;
    }
}
