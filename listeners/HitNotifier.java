// ID 208465096

package listeners;

/**
 * @author Ariel Ashkenazy
 * The interface Hit notifier.
 */
public interface HitNotifier {

    /**
     * Add listeners.HitListener as a listener to hit events.
     * @param hl the listeners.HitListener.
     */
    void addHitListener(HitListener hl);

    /**
     * Remove listeners.HitListener from the list of listeners to hit events.
     * @param hl the listeners.HitListener.
     */
    void removeHitListener(HitListener hl);
}