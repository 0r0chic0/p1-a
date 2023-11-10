package erehwon;

import org.junit.jupiter.api.Test;

import java.awt.*;

import static erehwon.Task3Tests.*;
import static org.junit.jupiter.api.Assertions.*;

public class Task4Tests {
    private static boolean validMoves(Color[][] original, Color[][] updated,
                                      int neighborhood,
                                      double happinessThreshold) {

        int original_red = 0;
        int original_blue = 0;
        int updated_red = 0;
        int updated_blue = 0;

        for (int i = 0; i < original.length; i++) {
            for (int j = 0; j < original.length; j++) {

                if (original[i][j].equals(Color.RED)) {
                    original_red++;
                } else if (original[i][j].equals(Color.BLUE)) {
                    original_blue++;
                }

                if (updated[i][j].equals(Color.RED)) {
                    updated_red++;
                } else if (updated[i][j].equals(Color.BLUE)) {
                    updated_blue++;
                }

                if (!original[i][j].equals(updated[i][j])) {
                    if (!(original[i][j].equals(Color.WHITE)
                            || (updated[i][j].equals(Color.WHITE)))) {
                        return false;
                    }
                    if (updated[i][j].equals(Color.WHITE)) {
                        if (checkIfHappy(original, i, j, neighborhood, happinessThreshold)) {
                            return false;
                        }
                    }
                }
            }
        }
        return (original_red == updated_red && original_blue == updated_blue);
    }

    @Test
    public void testOneStepEasy() {
        final int size = 10;
        final double fractionVacant     = 0.0;
        final double fractionRed        = 0.0;
        final double happinessThreshold = 0.4;
        final int    neighborhood       = 2;

        RedBlueGrid rbg = new RedBlueGrid(size, neighborhood,
                fractionVacant, fractionRed, happinessThreshold);

        Color[][] original = getArray(rbg);
        rbg.oneTimeStep();
        Color[][] updated = getArray(rbg);
        assertTrue(validMoves(original, updated, neighborhood, happinessThreshold));
        assertEquals(1.0, rbg.fractionHappy());
    }

    @Test
    public void testOneStepMix1() {
        final int size = 4;
        final double fractionVacant     = 0.1;
        final double fractionRed        = 0.4;
        final double happinessThreshold = 0.4;
        final int neighborhood          = 1;

        RedBlueGrid grid = new RedBlueGrid(size, neighborhood, fractionVacant, fractionRed, happinessThreshold);

        Color[][] original = {
                {Color.RED,   Color.RED,   Color.RED,   Color.RED},
                {Color.RED,   Color.RED,   Color.RED,   Color.RED},
                {Color.WHITE, Color.WHITE, Color.WHITE, Color.BLUE},
                {Color.BLUE,  Color.BLUE,  Color.BLUE,  Color.BLUE}
        };
        setGrid(grid, original);
        grid.oneTimeStep();
        Color[][] updated = getArray(grid);
        assertEquals(Color.WHITE, grid.getColor(3, 0));
        assertTrue(validMoves(original, updated, neighborhood, happinessThreshold));
    }

    @Test
    public void testOneStepMix2() {
        final int size = 4;
        final double fractionVacant     = 0.1;
        final double fractionRed        = 0.4;
        final double happinessThreshold = 0.4;
        final int neighborhood          = 1;

        RedBlueGrid grid = new RedBlueGrid(size, neighborhood, fractionVacant, fractionRed, happinessThreshold);

        Color[][] original = {
                {Color.RED,   Color.RED,   Color.RED,   Color.RED},
                {Color.RED,   Color.RED,   Color.RED,   Color.RED},
                {Color.RED,   Color.WHITE, Color.WHITE, Color.BLUE},
                {Color.BLUE,  Color.BLUE,  Color.BLUE,  Color.BLUE}
        };
        setGrid(grid, original);
        grid.oneTimeStep();
        Color[][] updated = getArray(grid);
        assertEquals(Color.WHITE, grid.getColor(3, 0));
        assertTrue(grid.getColor(2, 1).equals(Color.BLUE)
                || grid.getColor(2, 2).equals(Color.BLUE));
        assertTrue(validMoves(original, updated, neighborhood, happinessThreshold));
    }

    @Test
    public void testOneStepMix3() {
        final int size = 4;
        final double fractionVacant     = 0.1;
        final double fractionRed        = 0.4;
        final double happinessThreshold = 0.4;
        final int neighborhood          = 1;

        RedBlueGrid grid = new RedBlueGrid(size, neighborhood, fractionVacant, fractionRed, happinessThreshold);

        Color[][] original = {
                {Color.RED,   Color.RED,   Color.BLUE,  Color.RED},
                {Color.RED,   Color.RED,   Color.RED,   Color.RED},
                {Color.RED,   Color.WHITE, Color.WHITE, Color.BLUE},
                {Color.BLUE,  Color.BLUE,  Color.BLUE,  Color.BLUE}
        };
        setGrid(grid, original);
        grid.oneTimeStep();
        Color[][] updated = getArray(grid);
        assertEquals(Color.WHITE, grid.getColor(3, 0));
        assertEquals(Color.WHITE, grid.getColor(0, 2));
        assertTrue(grid.getColor(2, 1).equals(Color.BLUE)
                && grid.getColor(2, 2).equals(Color.BLUE));
        assertTrue(validMoves(original, updated, neighborhood, happinessThreshold));
        assertFalse(grid.isHappy(2,1));
        assertEquals(0.928, grid.fractionHappy(), 0.001);
    }

    @Test
    public void testOneStepMixLarge() {
        final int size = 30;
        final double fractionVacant     = 0.3;
        final double fractionRed        = 0.3;
        final double happinessThreshold = 0.4;
        final int    neighborhood       = 3;

        RedBlueGrid rbg = new RedBlueGrid(size, neighborhood,
                fractionVacant, fractionRed, happinessThreshold);

        Color[][] original = getArray(rbg);
        rbg.oneTimeStep();
        Color[][] updated = getArray(rbg);
        assertTrue(validMoves(original, updated, neighborhood, happinessThreshold));
    }
}
