package erehwon;

import org.junit.jupiter.api.Test;

import java.awt.*;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

public class Task3Tests {
    protected static Color[][] getArray(RedBlueGrid grid) {
        final int size = grid.getSize();
        Color[][] colorArray = new Color[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                colorArray[i][j] = grid.getColor(i, j);
            }
        }
        return colorArray;
    }

    protected static void setGrid(RedBlueGrid grid, Color[][] colorArray) {
        int size = colorArray.length;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                grid.setColor(i, j, colorArray[i][j]);
            }
        }
    }

    protected static boolean checkIfHappy(Color[][] colorArray,
                                          int i, int j, int neighborhood,
                                          double happinessThreshold) {
        final int size = colorArray.length;
        Color cellColor = colorArray[i][j];

        if (cellColor.equals(Color.WHITE)) {
            return false;
        }

        int sameColor  = -1;
        int neighbours = -1;
        for (int i1 = i - neighborhood; i1 <= i + neighborhood; i1++) {
            for (int j1 = j - neighborhood; j1 <= j + neighborhood; j1++) {
                Color neighbouringColor;
                if (i1 < 0 || i1 >= size || j1 < 0 || j1 >= size) {
                    continue;
                }
                neighbours++;
                try {
                    neighbouringColor = colorArray[i1][j1];
                    if (cellColor.equals(neighbouringColor)) {
                        sameColor++;
                    }
                }
                catch (Exception e) {
                    fail();
                }
            }
        }
        return ((sameColor * 1.0) / neighbours >= happinessThreshold);
    }
    private static boolean checkIfHappy(RedBlueGrid grid, int i, int j, int neighborhood) {
        Color[][] colorArray = getArray(grid);
        return checkIfHappy(colorArray, i, j, neighborhood, grid.getHappinessThreshold());
    }

    @Test
    public void testIsHappy1() {
        final int size = 4;
        final double fractionVacant = 0.1;
        final double fractionRed = 0.4;
        final double happinessThreshold = 0.4;
        final int neighborhood = 1;

        RedBlueGrid grid = new RedBlueGrid(size, neighborhood, fractionVacant, fractionRed, happinessThreshold);

        Color[][] colorArray = {
                {Color.RED,   Color.RED,   Color.RED,   Color.RED},
                {Color.RED,   Color.RED,   Color.RED,   Color.RED},
                {Color.WHITE, Color.WHITE, Color.WHITE, Color.WHITE},
                {Color.BLUE,  Color.BLUE,  Color.BLUE,  Color.BLUE}
        };

        setGrid(grid, colorArray);

        assertTrue(grid.isHappy(0, 0));
        assertTrue(grid.isHappy(0, 1));
        assertTrue(grid.isHappy(0, 2));
        assertTrue(grid.isHappy(0, 3));
        assertFalse(grid.isHappy(3, 0));
        assertTrue(grid.isHappy(3, 2));
    }

    @Test
    public void testIsHappy2() {
        final int size = 4;
        final double fractionVacant = 0.1;
        final double fractionRed = 0.4;
        final double happinessThreshold = 0.4;
        final int neighborhood = 1;

        RedBlueGrid grid = new RedBlueGrid(size, neighborhood, fractionVacant, fractionRed, happinessThreshold);

        Color[][] colorArray = {
                {Color.RED,   Color.RED,   Color.RED,   Color.RED},
                {Color.RED,   Color.RED,   Color.RED,   Color.RED},
                {Color.RED,   Color.BLUE,  Color.RED,   Color.WHITE},
                {Color.RED,   Color.RED,   Color.RED,   Color.RED}
        };

        setGrid(grid, colorArray);

        assertTrue(grid.isHappy(0, 0));
        assertTrue(grid.isHappy(0, 1));
        assertTrue(grid.isHappy(0, 2));
        assertTrue(grid.isHappy(0, 3));
        assertTrue(grid.isHappy(3, 2));
        assertFalse(grid.isHappy(2, 1));
    }

    @Test
    public void testIsHappy3() {
        final int size = 25;
        final double fractionVacant = 0.1;
        final double fractionRed = 0.4;
        final double happinessThreshold = 0.4;
        final int neighborhood = 1;

        RedBlueGrid grid = new RedBlueGrid(size, neighborhood, fractionVacant, fractionRed, happinessThreshold);

        final int num_trials = 10;
        Random rng = new Random();

        for (int trial = 0; trial < num_trials; trial++) {
            int i = rng.nextInt(size);
            int j = rng.nextInt(size);
            assertEquals(checkIfHappy(grid, i, j, neighborhood), grid.isHappy(i, j));
        }
    }

    @Test
    public void testIsHappy4() {
        final int size = 4;
        final double fractionVacant = 0.1;
        final double fractionRed = 0.4;
        final double happinessThreshold = 0.4;
        final int neighborhood = 3;

        RedBlueGrid grid = new RedBlueGrid(size, neighborhood, fractionVacant, fractionRed, happinessThreshold);

        Color[][] colorArray = {
                {Color.RED,   Color.RED,   Color.RED,   Color.RED},
                {Color.RED,   Color.RED,   Color.RED,   Color.RED},
                {Color.RED,   Color.BLUE,  Color.RED,   Color.WHITE},
                {Color.BLUE,  Color.BLUE,  Color.BLUE,  Color.BLUE}
        };

        setGrid(grid, colorArray);

        assertTrue(grid.isHappy(0, 0));
        assertTrue(grid.isHappy(0, 1));
        assertTrue(grid.isHappy(0, 2));
        assertTrue(grid.isHappy(0, 3));
        assertTrue(grid.isHappy(2, 0));
        assertFalse(grid.isHappy(3, 0));
        assertFalse(grid.isHappy(3, 2));
        assertFalse(grid.isHappy(2, 1));
    }

    @Test
    public void testFractionHappy() {
        final int trials = 20;
        final int size = 20;
        final double fractionVacant = 0.1;
        final double fractionRed = 0.4;
        final double happinessThreshold = 0.3;
        final int neighborhood = 2;

        for (int t = 0; t < trials; t++) {
            RedBlueGrid grid = new RedBlueGrid(size, neighborhood, fractionVacant, fractionRed, happinessThreshold);

            int countHappy = 0;
            for (int i = 0; i < size; i++) {
                for (int j = 0; j < size; j++) {
                    if (checkIfHappy(grid, i, j, neighborhood)) {
                        countHappy++;
                    }
                }
            }

            double expected = (countHappy * 1.0) / (size * size * (1 - fractionVacant));
            double actual = grid.fractionHappy();

            assertEquals(expected, actual, 0.001);
        }
    }
}
