package Listeners;

/**
 * The interface Hit notifier.
 * @author Roi Shukrun <address>roee.shukrun@live.biu.ac.il</address>
 * @version 1.5
 * @since 2023 -03-30
 */
public interface HitNotifier {

    /**
     * Add hit listener.
     * Add hl as a listener to hit events.
     * @param hl the hit listener
     */
    void addHitListener(HitListener hl);

    /**
     * Remove hit listener.
     * Remove hl from the list of listeners to hit events.
     * @param hl the hit listener
     */
    void removeHitListener(HitListener hl);
}
