// ID 208465096

package geometry;

/**
 * @author Ariel Ashkenazy
 * Utility class
 */
public class Util {
    /**
     * checks if two doubles have the same value.
     * @param a a double variable we want to compare with b.
     * @param b a double variable we want to compare with a.
     * @return boolean, true if the doubles have the same value, false otherwise.
     */
    public static boolean areTheSame(double a, double b) {
        double sub = Math.abs(a - b);
        return sub <= Math.pow(10, -13);   // 1E-13
    }
}