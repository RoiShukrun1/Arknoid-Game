package Levels;

import Game.Sprite;
import biuoop.DrawSurface;

import java.awt.Color;

/**
 * The type Green 3 style.
 * @author Roi Shukrun <address>roee.shukrun@live.biu.ac.il</address>
 * @version 1.5
 * @since 2023 -03-30
 */
public class Green3Style implements Sprite {
    private static final int BACKGROUND_COLOR_RED = 0;
    private static final int BACKGROUND_COLOR_GREEN = 102;
    private static final int BACKGROUND_COLOR_BLUE = 0;
    private static final int BACKGROUND_RECTANGLE_X = 25;
    private static final int BACKGROUND_RECTANGLE_Y = 30;
    private static final int BACKGROUND_RECTANGLE_WIDTH = 775;
    private static final int BACKGROUND_RECTANGLE_HEIGHT = 600;

    private static final int BUILDING_WIDTH = 150;
    private static final int BUILDING_HEIGHT = 250;
    private static final int BUILDING_X = 100;
    private static final int WINDOW_WIDTH = 15;
    private static final int WINDOW_HEIGHT = 30;
    private static final int NUM_WINDOWS_X = 5;
    private static final int NUM_WINDOWS_Y = 6;
    private static final int WINDOW_GAP = 14;
    private static final int WINDOW_START_X = 110;
    private static final int ANTENNA_X = BUILDING_X + BUILDING_WIDTH / 2;
    private static final int ANTENNA_HEIGHT1 = 200;
    private static final int ANTENNA_HEIGHT2 = 150;
    private static final int ANTENNA_THICKNESS1 = 12;
    private static final int ANTENNA_THICKNESS2 = 6;

    @Override
    public void drawOn(DrawSurface d) {
        // Draw the background
        d.setColor(new Color(BACKGROUND_COLOR_RED, BACKGROUND_COLOR_GREEN, BACKGROUND_COLOR_BLUE));
        d.fillRectangle(BACKGROUND_RECTANGLE_X, BACKGROUND_RECTANGLE_Y, BACKGROUND_RECTANGLE_WIDTH,
                BACKGROUND_RECTANGLE_HEIGHT);

        // Draw the building
        int buildingY = d.getHeight() - BUILDING_HEIGHT;
        Color buildingColor = new Color(51, 51, 51);
        d.setColor(buildingColor);
        d.fillRectangle(BUILDING_X, buildingY, BUILDING_WIDTH, BUILDING_HEIGHT);
        d.setColor(Color.darkGray);
        d.fillRectangle(BUILDING_X + WINDOW_GAP, buildingY - BUILDING_HEIGHT / 4,
                BUILDING_WIDTH - WINDOW_GAP * 2, BUILDING_HEIGHT / 4);

        // Draw the windows
        Color windowColor = Color.white;

        d.setColor(windowColor);
        int windowStartX = WINDOW_START_X;
        int windowStartY = 360;
        for (int i = 0; i < NUM_WINDOWS_X; i++) {
            for (int j = 0; j < NUM_WINDOWS_Y; j++) {
                d.fillRectangle(windowStartX, windowStartY, WINDOW_WIDTH, WINDOW_HEIGHT);
                windowStartY += WINDOW_HEIGHT + WINDOW_GAP;
            }
            windowStartX += WINDOW_WIDTH + WINDOW_GAP;
            windowStartY = 360;
        }

        // Draw the antennas
        Color antennaColor = Color.DARK_GRAY;
        Color antennaPointColor = new Color(255, 204, 51);

        d.setColor(antennaColor);
        int antennaY1 = 150;
        d.fillRectangle(ANTENNA_X - ANTENNA_THICKNESS1 / 2, antennaY1, ANTENNA_THICKNESS1, ANTENNA_HEIGHT1);
        int antennaY2 = 50;
        d.fillRectangle(ANTENNA_X - ANTENNA_THICKNESS2 / 2, antennaY2, ANTENNA_THICKNESS2, ANTENNA_HEIGHT2);

        d.setColor(antennaPointColor);
        int antennaPointRadius1 = ANTENNA_THICKNESS1 * 2;
        d.fillCircle(ANTENNA_X, antennaY1, antennaPointRadius1);
        int antennaPointRadius2 = ANTENNA_THICKNESS2 * 2;
        d.fillCircle(ANTENNA_X, antennaY2, antennaPointRadius2);
        int antennaPointRadiusInner1 = ANTENNA_THICKNESS1 / 2;
        d.setColor(Color.red);
        d.fillCircle(ANTENNA_X, antennaY1, ANTENNA_THICKNESS1);
        d.fillCircle(ANTENNA_X, antennaY2, ANTENNA_THICKNESS2);
        d.setColor(new Color(255, 255, 153));
        d.fillCircle(ANTENNA_X, antennaY1, antennaPointRadiusInner1);
        int antennaPointRadiusInner2 = ANTENNA_THICKNESS2 / 2;
        d.fillCircle(ANTENNA_X, antennaY2, antennaPointRadiusInner2);
    }

    @Override
    public void timePassed() {
    }
}

