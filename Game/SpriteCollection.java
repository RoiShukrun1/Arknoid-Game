package Game;

import biuoop.DrawSurface;

import java.util.ArrayList;
import java.util.List;

/**
 * Sprite collection.
 * @author Roi Shukrun <address>roee.shukrun@live.biu.ac.il</address>
 * @version 1.5
 * @since 2023 -03-30
 */

public class SpriteCollection {
    private final List<Sprite> sprites;

    /**
     * Instantiates a new Sprite collection.
     */
    public SpriteCollection() {
        this.sprites = new ArrayList<>();
    }

    /**
     * Gets sprites.
     * @return the list of the existing sprites
     */
    public List<Sprite> getSprites() {
        return sprites;
    }

    /**
     * Add sprite.
     * add the given sprite to the sprite collection.
     * @param s the given sprite
     */
    public void addSprite(Sprite s) {
        this.sprites.add(s);
    }

    /**
     * Remove sprite.
     * remove the given sprite from the sprite collection.
     * @param s the given sprite
     */
    public void removeSprite(Sprite s) {
        this.sprites.remove(s);
    }

    /**
     * Notify all time passed.
     * call timePassed() on all sprites.
     */
    public void notifyAllTimePassed() {
        // Make a copy of the sprites before iterating over them.
        List<Sprite> sprites = new ArrayList<>(this.sprites);
        for (Sprite s : sprites) {
            s.timePassed();
        }
    }

    /**
     * Draw all on.
     * Draw all the sprites on the given surface
     * @param d the given surface
     */
    public void drawAllOn(DrawSurface d) {
        // Make a copy of the sprites before iterating over them.
        List<Sprite> sprites = new ArrayList<>(this.sprites);
        for (Sprite s : sprites) {
            s.drawOn(d);
        }
    }
}
