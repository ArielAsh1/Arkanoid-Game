// ID 208465096

package settings;

/**
 * @author Ariel Ashkenazy
 * a classic counter class.
 */
public class Counter {
    private int count;

    /**
     * constructor.
     * @param startingCount the starting number for the count.
     */
    public Counter(int startingCount) {
        count = startingCount;
    }

    /**
     * adds a number to the current count.
     * @param number the amount we add.
     */
    public void increase(int number) {
        count += number;
    }

    /**
     * subtracts a number from current count.
     * @param number the amount we subtract.
     */
    public void decrease(int number) {
        count -= number;
    }
    /**
     * gets the current count.
     * @return the current count.
     */
    public int getValue() {
        return count;
    }
}