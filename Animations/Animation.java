package Animations;

import biuoop.DrawSurface;

/**
 * The interface Animation.
 * @author Roi Shukrun <address>roee.shukrun@live.biu.ac.il</address>
 * @version 1.5
 * @since 2023 -03-30
 */
public interface Animation {
    /**
     * Do one frame.
     * this method running one frame of the animation
     * @param d the drawSurface
     */
    void doOneFrame(DrawSurface d);

    /**
     * Should stop boolean.
     * this method returning true if the animation should stop for some reason
     * @return the boolean true if the animation should stop, false otherwise
     */
    boolean shouldStop();
}
