import Levels.GameFlow;
import Levels.Green3Level;
import Levels.LevelInformation;
import Levels.WideEasyLevel;
import Levels.DirectHitLevel;
import biuoop.KeyboardSensor;
import Animations.AnimationRunner;

import java.util.ArrayList;
import java.util.List;

/**
 * Ass 5 game.
 * @author Roi Shukrun <address>roee.shukrun@live.biu.ac.il</address>
 * @version 1.5
 * @since 2023 -03-30
 */
public class Ass6Game {

    /**
     * String to array int [ ].
     * The stringsToArray method converts an array of strings
     * to an array of integers.
     * the method is getting an array of strings (numbers) and
     * converting it to array of integers.
     * the array gets only numbers from 1-3.
     * @param numbers an array of strings to be converted to an array of integers.
     * @return the int [ ] arr the converted array of integers [1-3].
     */
    public static int[] stringsToArray(String[] numbers) {
        List<Integer> numberList = new ArrayList<>();
        for (String number : numbers) {
            // filtering the unnecessary strings
            if (number.matches("[1-3]")) {
                int num = Integer.parseInt(number);
                numberList.add(num);
            }
        }
        int[] arr = new int[numberList.size()];
        for (int i = 0; i < numberList.size(); i++) {
            arr[i] = numberList.get(i);
        }
        return arr;
    }

    /**
     * The entry point of application.
     * The program gets array of string, that represents the order of the game levels.
     * Then it runs the game by the correct level's order.
     * @param args the input arguments
     */
    public static void main(String[] args) {
        int[] numbers = stringsToArray(args);
        // creating the list of levels (given by the main arguments)
        List<LevelInformation> levels = new ArrayList<>();
        for (int number : numbers) {
            switch (number) {
                case 1 -> levels.add(new DirectHitLevel());
                case 2 -> levels.add(new WideEasyLevel());
                case 3 -> levels.add(new Green3Level());
                default -> {
                }
            }
        }
        // running the game
        AnimationRunner runner = new AnimationRunner();
        KeyboardSensor keyboardSensor = runner.getGui().getKeyboardSensor();
        GameFlow game = new GameFlow(runner, keyboardSensor);
        game.runLevels(levels);
    }
}
