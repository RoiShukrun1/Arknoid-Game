package Levels;

import Animations.Animation;
import Animations.KeyPressStoppableAnimation;
import Animations.NextLevelAnimation;
import Animations.YouWinAnimation;
import biuoop.KeyboardSensor;
import Animations.AnimationRunner;
import Game.Counter;

import java.util.List;

/**
 * The type Game flow.
 * @author Roi Shukrun <address>roee.shukrun@live.biu.ac.il</address>
 * @version 1.5
 * @since 2023 -03-30
 */
public class GameFlow {
    /**
     * The Default stop between levels.
     */
    static final int DEFAULT_STOP_BETWEEN_LEVELS = 1000;
    private final AnimationRunner animationRunner;
    private final KeyboardSensor keyboardSensor;
    private Counter currentScore = new Counter(0);


    /**
     * Instantiates a new Game flow.
     * @param ar the ar
     * @param ks the ks
     */
    public GameFlow(AnimationRunner ar, KeyboardSensor ks) {
        this.animationRunner = ar;
        this.keyboardSensor = ks;
    }

    /**
     * Run levels.
     * This method gets the list of levels that describe the flow
     * of the game and run the animation of each level
     * @param levels the levels
     */
    public void runLevels(List<LevelInformation> levels) {
        for (int i = 0; i < levels.size(); i++) {
            LevelInformation levelInfo = levels.get(i);
            GameLevel level = new GameLevel(keyboardSensor, animationRunner, currentScore);

            level.initialize(levelInfo);

            while (!level.shouldStop()) {
                level.run();
                if (level.shouldStop()) {
                    break;
                }
            }
            // check if the player moved to the next level - if it is we will run the next level animation
            if (i != levels.size() - 1) {
                if (level.getRemainingBlocksCounter().getValue() == 0) {
                    currentScore = level.getCurrentScore();
                    // Player has finished the level, show "Next Level" animation
                    Animation nextLevel = new NextLevelAnimation(currentScore, DEFAULT_STOP_BETWEEN_LEVELS);
                    this.animationRunner.run(nextLevel);
                    animationRunner.getSleeper().sleepFor(DEFAULT_STOP_BETWEEN_LEVELS); // Wait for 1 second
                }
            } else if (i == levels.size() - 1) { // Check if it is the last level
                if (level.getRemainingBlocksCounter().getValue() == 0) {
                    currentScore = level.getCurrentScore();
                    // Player has finished the final level, show "You Win" animation
                    Animation youWinScreen =
                            new KeyPressStoppableAnimation(this.keyboardSensor, KeyboardSensor.SPACE_KEY,
                                    new YouWinAnimation(currentScore));
                    this.animationRunner.run(youWinScreen);
                    // Close the GUI
                    this.animationRunner.getGui().close();
                }
            }
        }
    }
}
