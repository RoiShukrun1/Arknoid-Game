package Levels;

import Game.Sprite;
import biuoop.DrawSurface;

import java.awt.Color;

/**
 * The type Direct hit style.
 * @author Roi Shukrun <address>roee.shukrun@live.biu.ac.il</address>
 * @version 1.5
 * @since 2023 -03-30
 */
public class DirectHitStyle implements Sprite {
    private static final int BACKGROUND_X = 25;
    private static final int BACKGROUND_Y = 30;
    private static final int BACKGROUND_WIDTH = 775;
    private static final int BACKGROUND_HEIGHT = 600;

    private static final int CIRCLE_CENTER_X = 385;
    private static final int CIRCLE_CENTER_Y = 165;
    private static final int OUTER_CIRCLE_RADIUS = 120;
    private static final int MIDDLE_CIRCLE_RADIUS = 90;
    private static final int INNER_CIRCLE_RADIUS = 60;

    private static final int LINE1_START_X = 365;
    private static final int LINE1_START_Y = 165;
    private static final int LINE1_END_X = 240;
    private static final int LINE1_END_Y = 165;

    private static final int LINE2_START_X = 405;
    private static final int LINE2_START_Y = 165;
    private static final int LINE2_END_X = 525;
    private static final int LINE2_END_Y = 165;

    private static final int LINE3_START_X = 385;
    private static final int LINE3_START_Y = 145;
    private static final int LINE3_END_X = 385;
    private static final int LINE3_END_Y = 20;

    private static final int LINE4_START_X = 385;
    private static final int LINE4_START_Y = 185;
    private static final int LINE4_END_X = 385;
    private static final int LINE4_END_Y = 310;

    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(Color.black);
        d.fillRectangle(BACKGROUND_X, BACKGROUND_Y, BACKGROUND_WIDTH, BACKGROUND_HEIGHT);

        d.setColor(Color.blue);
        d.drawCircle(CIRCLE_CENTER_X, CIRCLE_CENTER_Y, OUTER_CIRCLE_RADIUS);
        d.drawCircle(CIRCLE_CENTER_X, CIRCLE_CENTER_Y, MIDDLE_CIRCLE_RADIUS);
        d.drawCircle(CIRCLE_CENTER_X, CIRCLE_CENTER_Y, INNER_CIRCLE_RADIUS);

        d.drawLine(LINE1_START_X, LINE1_START_Y, LINE1_END_X, LINE1_END_Y);
        d.drawLine(LINE2_START_X, LINE2_START_Y, LINE2_END_X, LINE2_END_Y);
        d.drawLine(LINE3_START_X, LINE3_START_Y, LINE3_END_X, LINE3_END_Y);
        d.drawLine(LINE4_START_X, LINE4_START_Y, LINE4_END_X, LINE4_END_Y);
    }

    @Override
    public void timePassed() {
        // No implementation needed for this example
    }
}

