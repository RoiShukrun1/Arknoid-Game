package Levels;

import Game.Sprite;
import biuoop.DrawSurface;

import java.awt.Color;

/**
 * The type Wide easy style.
 * @author Roi Shukrun <address>roee.shukrun@live.biu.ac.il</address>
 * @version 1.5
 * @since 2023 -03-30
 */
public class WideEasyStyle implements Sprite {

    private static final int BACKGROUND_COLOR_RED = 255;
    private static final int BACKGROUND_COLOR_GREEN = 102;
    private static final int BACKGROUND_COLOR_BLUE = 0;
    private static final int BACKGROUND_RECTANGLE_X = 25;
    private static final int BACKGROUND_RECTANGLE_Y = 30;
    private static final int BACKGROUND_RECTANGLE_WIDTH = 775;
    private static final int BACKGROUND_RECTANGLE_HEIGHT = 600;

    private static final int SUN_X = 130;
    private static final int SUN_Y = 130;
    private static final int SUN_RADIUS = 60;
    private static final int LINE_END_Y = 240;

    private static final int NUM_LINES = 120;

    // Gradient colors
    private static final int INNER_COLOR1_RED = 255;
    private static final int INNER_COLOR1_GREEN = 255;
    private static final int INNER_COLOR1_BLUE = 153;
    private static final int INNER_COLOR2_RED = 255;
    private static final int INNER_COLOR2_GREEN = 204;
    private static final int INNER_COLOR2_BLUE = 0;

    @Override
    public void drawOn(DrawSurface d) {
        // Draw the background
        Color backgroundColor = new Color(BACKGROUND_COLOR_RED, BACKGROUND_COLOR_GREEN, BACKGROUND_COLOR_BLUE);
        d.setColor(backgroundColor);
        d.fillRectangle(BACKGROUND_RECTANGLE_X, BACKGROUND_RECTANGLE_Y,
                BACKGROUND_RECTANGLE_WIDTH, BACKGROUND_RECTANGLE_HEIGHT);

        // Draw the lines
        Color lineColor = new Color(240, 230, 140);
        int halfNumLines = NUM_LINES / 2;
        for (int i = 0; i < NUM_LINES; i++) {
            double startX;
            if (i == 0) {
                startX = 0;
            } else {
                startX = SUN_X;
            }
            double startY = SUN_Y;
            double endX = 1000.0 * (i + 1) / NUM_LINES;
            double endY = LINE_END_Y;

            // Adjust the density based on the line index
            int density;
            if (i < halfNumLines) {
                density = i * 2;
            } else {
                density = (NUM_LINES - i - 1) * 2;
            }

            // Draw the lines with the adjusted density
            for (int j = 0; j < density; j++) {
                double lineStartX = startX + (endX - startX) * j / density;
                double lineStartY = startY + (endY - startY) * j / density;
                double lineEndX = startX + (endX - startX) * (j + 1) / density;
                double lineEndY2 = startY + (endY - startY) * (j + 1) / density;
                d.setColor(lineColor);
                d.drawLine((int) lineStartX, (int) lineStartY, (int) lineEndX, (int) lineEndY2);
            }
        }

        // Draw the sun circles
        // Outer circle
        d.setColor(new Color(240, 230, 140));
        d.fillCircle(SUN_X, SUN_Y, SUN_RADIUS);

        // Inner circle - First gradient color
        int innerRadius1 = SUN_RADIUS - 10;
        for (int r = SUN_RADIUS; r >= innerRadius1; r--) {
            float ratio = (float) (r - innerRadius1) / (SUN_RADIUS - innerRadius1);
            Color color = new Color(
                    (int) (INNER_COLOR1_RED * ratio),
                    (int) (INNER_COLOR1_GREEN * ratio),
                    (int) (INNER_COLOR1_BLUE * ratio)
            );
            d.setColor(color);
            d.drawCircle(SUN_X, SUN_Y, r);
        }

        // Inner circle - Second gradient color
        int innerRadius2 = SUN_RADIUS - 20;
        for (int r = innerRadius1; r >= innerRadius2; r--) {
            float ratio = (float) (r - innerRadius2) / (innerRadius1 - innerRadius2);
            Color color = new Color(
                    (int) (INNER_COLOR2_RED * ratio),
                    (int) (INNER_COLOR2_GREEN * ratio),
                    (int) (INNER_COLOR2_BLUE * ratio)
            );
            d.setColor(color);
            d.drawCircle(SUN_X, SUN_Y, r);
        }
    }

    @Override
    public void timePassed() {
        // No time-based actions for this sprite
    }
}

