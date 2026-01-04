package Animations;

import biuoop.DrawSurface;
import Game.SpriteCollection;

import java.awt.Color;

/**
 * The type Countdown animation.
 * @author Roi Shukrun <address>roee.shukrun@live.biu.ac.il</address>
 * @version 1.5
 * @since 2023 -03-30
 */
public class CountdownAnimation implements Animation {
    private final double countdownDuration; // Total duration of the countdown animation
    private final int countFrom; // The number to count down from
    private final SpriteCollection gameScreen; // The game screen to display underneath the countdown
    private double countdownStartTime; // The time when the countdown animation started
    private boolean finished; // Flag indicating if the countdown animation has finished
    private boolean firstFrame; // Flag indicating if it's the first frame of the animation

    /**
     * Instantiates a new Countdown animation.
     * @param numOfSeconds the num of seconds
     * @param countFrom the count from
     * @param gameScreen the game screen
     */
    public CountdownAnimation(double numOfSeconds, int countFrom, SpriteCollection gameScreen) {
        this.countdownDuration = numOfSeconds;
        this.countFrom = countFrom;
        this.gameScreen = gameScreen;
        this.countdownStartTime = 0;
        this.finished = false;
        this.firstFrame = true;
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        if (firstFrame) {
            countdownStartTime = System.currentTimeMillis();
            firstFrame = false;
        }

        gameScreen.drawAllOn(d); // Draw the game screen

        // Calculate the time passed since the start of the countdown
        double timePassed = (System.currentTimeMillis() - countdownStartTime) / 1000.0;

        // Calculate the time for each number to appear on the screen
        double numberDisplayTime = countdownDuration / countFrom;

        // Calculate the current number to display based on the time passed
        int currentNumber = countFrom - (int) (timePassed / numberDisplayTime);

        d.setColor(Color.gray);
        if (currentNumber > 0) {
            // Display the current number on the screen
            d.drawText(d.getWidth() / 2, d.getHeight() / 2, String.valueOf(currentNumber), 60);
        } else {
            // Display "Go!" when the countdown is finished
            d.drawText(d.getWidth() / 2, d.getHeight() / 2, "Go!", 60);
        }
        // Check if the countdown animation has finished
        if (timePassed >= countdownDuration) {
            finished = true;
        }
    }

    @Override
    public boolean shouldStop() {
        return finished;
    }
}
