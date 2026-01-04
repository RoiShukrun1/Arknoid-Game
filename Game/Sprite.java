package Game;

import biuoop.DrawSurface;

/**
 * The interface Sprite.
 * @author Roi Shukrun <address>roee.shukrun@live.biu.ac.il</address>
 * @version 1.5
 * @since 2023 -03-30
 */

public interface Sprite {
    /**
     * Draw on.
     * draw the sprite on a given surface
     * @param d the given surface
     */
    void drawOn(DrawSurface d);

    /**
     * Time passed.
     * notify the sprite that time has passed
     */
    void timePassed();
}