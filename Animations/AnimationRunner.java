package Animations;

import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;

/**
 * The type Animation runner.
 * @author Roi Shukrun <address>roee.shukrun@live.biu.ac.il</address>
 * @version 1.5
 * @since 2023 -03-30
 */
public class AnimationRunner {
    /**
     * The constant SLEEPER_DEFAULT_DURATION.
     */
    // The sleeper default time duration
    static final int SLEEPER_DEFAULT_DURATION = 1000;
    /**
     * The Default sleeper time.
     */
    static final int DEFAULT_SLEEPER_TIME = 60; // The default time in milliseconds to sleep between frames.
    /**
     * The Gui width.
     */
    static final int GUI_WIDTH = 800; // The width of the GUI window.
    /**
     * The Gui height.
     */
    static final int GUI_HEIGHT = 600; // The height of the GUI window.
    private final GUI gui;
    private final int framesPerSecond;
    private final Sleeper sleeper;

    /**
     * Instantiates a new Animation runner.
     * @param gui the gui
     * @param framesPerSecond the frames per second
     */
    public AnimationRunner(GUI gui, int framesPerSecond) {
        this.gui = gui;
        this.framesPerSecond = framesPerSecond;
        this.sleeper = new Sleeper();
    }

    /**
     * Instantiates a new Animation runner without parameters.
     */
    public AnimationRunner() {
        this.gui = new GUI("Ass6Game", GUI_WIDTH, GUI_HEIGHT);
        this.framesPerSecond = DEFAULT_SLEEPER_TIME;
        this.sleeper = new Sleeper();
    }

    /**
     * Gets gui.
     * @return the gui
     */
    public GUI getGui() {
        return gui;
    }

    /**
     * Gets sleeper.
     * @return the sleeper
     */
    public Sleeper getSleeper() {
        return sleeper;
    }

    /**
     * Run.
     * Run the game - start the animation loop.
     * @param animation the animation
     */
    public void run(Animation animation) {
        // Enter the game loop
        int millisecondsPerFrame = SLEEPER_DEFAULT_DURATION / framesPerSecond;
        while (!animation.shouldStop()) {
            // Timing for the current frame
            long startTime = System.currentTimeMillis();
            DrawSurface d = gui.getDrawSurface();

            animation.doOneFrame(d);

            gui.show(d);

            long usedTime = System.currentTimeMillis() - startTime;
            long milliSecondLeftToSleep = millisecondsPerFrame - usedTime;
            if (milliSecondLeftToSleep > 0) {
                this.sleeper.sleepFor(milliSecondLeftToSleep);
            }
        }
    }
}
