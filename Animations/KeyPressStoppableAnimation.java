package Animations;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

/**
 * The type Key press stoppable animation.
 * @author Roi Shukrun <address>roee.shukrun@live.biu.ac.il</address>
 * @version 1.5
 * @since 2023 -03-30
 */
public class KeyPressStoppableAnimation implements Animation {
    private final KeyboardSensor sensor;
    private final String key;
    private final Animation animation;
    private boolean isAlreadyPressed;
    private boolean stopAnimation;


    /**
     * Instantiates a new Key press stoppable animation.
     * @param sensor the sensor
     * @param key the key
     * @param animation the animation
     */
    public KeyPressStoppableAnimation(KeyboardSensor sensor, String key, Animation animation) {
        this.sensor = sensor;
        this.key = key;
        this.animation = animation;
        this.isAlreadyPressed = false;
        this.stopAnimation = false;
    }

    @Override
    public void doOneFrame(DrawSurface d) {
         if (!this.sensor.isPressed(this.key)) {
            this.isAlreadyPressed = false;
        }
        if (!this.isAlreadyPressed && !this.sensor.isPressed(this.key)) {
            this.animation.doOneFrame(d);
        }
        if (this.sensor.isPressed(this.key)) {
            this.stopAnimation = true;
        }
    }

    @Override
    public boolean shouldStop() {
        return this.stopAnimation;
    }
}
