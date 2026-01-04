package Game;

/**
 * The type Counter.
 * @author Roi Shukrun <address>roee.shukrun@live.biu.ac.il</address>
 * @version 1.5
 * @since 2023 -03-30
 */

public class Counter {
    // The counter:
    private int counter;

    /**
     * Instantiates a new Counter.
     * @param number the value of the counter
     */
    public Counter(int number) {
        this.counter = number;
    }

    /**
     * Increase.
     * add number to current count.
     * @param number the number
     */
    public void increase(int number) {
        counter = counter + number;
    }

    /**
     * Decrease.
     * subtract number from current count.
     * @param number the number
     */
    public void decrease(int number) {
        counter = counter - number;
    }

    /**
     * Gets value.
     * get current count.
     * @return the value
     */
    public int getValue() {
        return counter;
    }
}
