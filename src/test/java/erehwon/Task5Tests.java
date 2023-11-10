package erehwon;

import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

public class Task5Tests {

    @Test
    public void testOneStepHappiness() {
        Color[][] original = {
                {Color.RED, Color.RED, Color.BLUE, Color.RED},
                {Color.RED, Color.RED, Color.RED, Color.RED},
                {Color.RED, Color.WHITE, Color.WHITE, Color.BLUE},
                {Color.BLUE, Color.BLUE, Color.BLUE, Color.BLUE}
        };

        final int size = 4;
        final double fractionVacant = 0.1;
        final double fractionRed = 0.4;
        final double happinessThreshold = 0.3;
        final int neighborhood = 1;
        RedBlueGrid grid = new RedBlueGrid(size, neighborhood, fractionVacant, fractionRed, happinessThreshold);
        Utils.setGrid(grid, original);

        assertNotEquals(1.0, grid.fractionHappy());
        grid.simulate(1);
        assertTrue(Utils.allResidentsHappy(grid, size));
    }

    @Test
    public void testMultistepHappiness1() {
        final int size = 10;
        final double fractionVacant = 0.3;
        final double fractionRed = 0.5;
        final double happinessThreshold = 0.4;
        final int neighborhood = 1;
        RedBlueGrid grid = new RedBlueGrid(size, neighborhood, fractionVacant, fractionRed, happinessThreshold);
        Color[][] gridArray = {
                {Color.BLUE, Color.BLUE, Color.RED, Color.BLUE, Color.RED, Color.WHITE, Color.WHITE, Color.RED, Color.BLUE, Color.WHITE},
                {Color.RED, Color.BLUE, Color.BLUE, Color.WHITE, Color.RED, Color.RED, Color.RED, Color.BLUE, Color.WHITE, Color.BLUE},
                {Color.BLUE, Color.BLUE, Color.BLUE, Color.BLUE, Color.BLUE, Color.WHITE, Color.RED, Color.RED, Color.RED, Color.BLUE},
                {Color.RED, Color.WHITE, Color.BLUE, Color.RED, Color.WHITE, Color.RED, Color.BLUE, Color.RED, Color.BLUE, Color.WHITE},
                {Color.BLUE, Color.WHITE, Color.WHITE, Color.RED, Color.WHITE, Color.RED, Color.RED, Color.RED, Color.RED, Color.WHITE},
                {Color.RED, Color.RED, Color.BLUE, Color.BLUE, Color.RED, Color.WHITE, Color.BLUE, Color.RED, Color.RED, Color.WHITE},
                {Color.RED, Color.BLUE, Color.BLUE, Color.WHITE, Color.RED, Color.WHITE, Color.BLUE, Color.BLUE, Color.WHITE, Color.RED},
                {Color.WHITE, Color.BLUE, Color.WHITE, Color.BLUE, Color.BLUE, Color.RED, Color.BLUE, Color.RED, Color.RED, Color.BLUE},
                {Color.RED, Color.RED, Color.WHITE, Color.WHITE, Color.BLUE, Color.WHITE, Color.WHITE, Color.BLUE, Color.WHITE, Color.BLUE},
                {Color.BLUE, Color.WHITE, Color.BLUE, Color.WHITE, Color.RED, Color.WHITE, Color.RED, Color.WHITE, Color.RED, Color.WHITE}
        };
        Utils.setGrid(grid, gridArray);


        int steps = 0;
        while (!Utils.allResidentsHappy(grid, size)) {
            steps++;
            grid.simulate(1);
        }
        assertTrue(steps <= 6 && steps > 1);
    }

    @Test
    public void testMultistepHappiness2() {
        final int size = 10;
        final double fractionVacant = 0.3;
        final double fractionRed = 0.5;
        final double happinessThreshold = 0.3;
        final int neighborhood = 1;
        RedBlueGrid grid = new RedBlueGrid(size, neighborhood, fractionVacant, fractionRed, happinessThreshold);
        Color[][] gridArray = {
                {Color.WHITE, Color.RED, Color.WHITE, Color.BLUE, Color.RED, Color.WHITE, Color.RED, Color.BLUE, Color.WHITE, Color.WHITE},
                {Color.WHITE, Color.BLUE, Color.WHITE, Color.WHITE, Color.BLUE, Color.WHITE, Color.RED, Color.RED, Color.BLUE, Color.RED},
                {Color.RED, Color.BLUE, Color.BLUE, Color.RED, Color.BLUE, Color.BLUE, Color.RED, Color.BLUE, Color.BLUE, Color.BLUE},
                {Color.RED, Color.BLUE, Color.BLUE, Color.RED, Color.BLUE, Color.RED, Color.WHITE, Color.BLUE, Color.BLUE, Color.BLUE},
                {Color.RED, Color.WHITE, Color.RED, Color.WHITE, Color.BLUE, Color.RED, Color.BLUE, Color.RED, Color.WHITE, Color.BLUE},
                {Color.BLUE, Color.WHITE, Color.RED, Color.BLUE, Color.WHITE, Color.BLUE, Color.BLUE, Color.WHITE, Color.WHITE, Color.BLUE},
                {Color.WHITE, Color.RED, Color.WHITE, Color.RED, Color.RED, Color.WHITE, Color.RED, Color.RED, Color.WHITE, Color.RED},
                {Color.BLUE, Color.RED, Color.BLUE, Color.BLUE, Color.WHITE, Color.RED, Color.RED, Color.WHITE, Color.WHITE, Color.RED},
                {Color.BLUE, Color.RED, Color.WHITE, Color.BLUE, Color.RED, Color.RED, Color.BLUE, Color.WHITE, Color.RED, Color.WHITE},
                {Color.BLUE, Color.WHITE, Color.BLUE, Color.BLUE, Color.RED, Color.WHITE, Color.RED, Color.RED, Color.WHITE, Color.RED}
        };
        Utils.setGrid(grid, gridArray);

        int steps = 0;
        while (!Utils.allResidentsHappy(grid, size)) {
            steps++;
            grid.simulate(1);
        }
        assertTrue(steps <= 3 && steps > 1);
    }

    @Test
    public void testLargerGrid() {
        final int size = 20;
        final double fractionVacant = 0.2;
        final double fractionRed = 0.5;
        final double happinessThreshold = 0.3;
        final int neighborhood = 2;
        RedBlueGrid grid = new RedBlueGrid(size, neighborhood, fractionVacant, fractionRed, happinessThreshold);
        Color[][] gridArray = {
                {Color.RED, Color.BLUE, Color.RED, Color.WHITE, Color.RED, Color.BLUE, Color.WHITE, Color.WHITE, Color.BLUE, Color.BLUE, Color.BLUE, Color.WHITE, Color.WHITE, Color.BLUE, Color.BLUE, Color.RED, Color.BLUE, Color.WHITE, Color.BLUE, Color.BLUE},
                {Color.RED, Color.BLUE, Color.RED, Color.BLUE, Color.WHITE, Color.RED, Color.RED, Color.BLUE, Color.RED, Color.RED, Color.WHITE, Color.WHITE, Color.WHITE, Color.RED, Color.WHITE, Color.WHITE, Color.RED, Color.WHITE, Color.RED, Color.WHITE},
                {Color.BLUE, Color.RED, Color.BLUE, Color.RED, Color.RED, Color.BLUE, Color.BLUE, Color.WHITE, Color.RED, Color.RED, Color.BLUE, Color.BLUE, Color.BLUE, Color.RED, Color.BLUE, Color.RED, Color.RED, Color.RED, Color.RED, Color.WHITE},
                {Color.WHITE, Color.WHITE, Color.BLUE, Color.BLUE, Color.BLUE, Color.RED, Color.BLUE, Color.BLUE, Color.RED, Color.WHITE, Color.WHITE, Color.RED, Color.BLUE, Color.WHITE, Color.WHITE, Color.RED, Color.RED, Color.RED, Color.RED, Color.BLUE},
                {Color.BLUE, Color.BLUE, Color.RED, Color.RED, Color.RED, Color.RED, Color.WHITE, Color.BLUE, Color.BLUE, Color.BLUE, Color.RED, Color.WHITE, Color.BLUE, Color.WHITE, Color.RED, Color.WHITE, Color.BLUE, Color.BLUE, Color.RED, Color.RED},
                {Color.BLUE, Color.BLUE, Color.BLUE, Color.RED, Color.BLUE, Color.BLUE, Color.BLUE, Color.BLUE, Color.BLUE, Color.RED, Color.WHITE, Color.RED, Color.RED, Color.RED, Color.WHITE, Color.WHITE, Color.BLUE, Color.RED, Color.RED, Color.RED},
                {Color.RED, Color.RED, Color.WHITE, Color.RED, Color.RED, Color.RED, Color.RED, Color.BLUE, Color.BLUE, Color.RED, Color.BLUE, Color.BLUE, Color.BLUE, Color.BLUE, Color.RED, Color.RED, Color.BLUE, Color.BLUE, Color.BLUE, Color.RED},
                {Color.BLUE, Color.RED, Color.BLUE, Color.BLUE, Color.BLUE, Color.BLUE, Color.WHITE, Color.BLUE, Color.BLUE, Color.RED, Color.WHITE, Color.WHITE, Color.WHITE, Color.RED, Color.RED, Color.RED, Color.BLUE, Color.RED, Color.BLUE, Color.BLUE},
                {Color.BLUE, Color.BLUE, Color.BLUE, Color.RED, Color.RED, Color.RED, Color.WHITE, Color.RED, Color.WHITE, Color.BLUE, Color.WHITE, Color.RED, Color.WHITE, Color.BLUE, Color.RED, Color.BLUE, Color.WHITE, Color.RED, Color.RED, Color.BLUE},
                {Color.BLUE, Color.WHITE, Color.BLUE, Color.RED, Color.BLUE, Color.RED, Color.RED, Color.RED, Color.WHITE, Color.BLUE, Color.RED, Color.RED, Color.RED, Color.BLUE, Color.WHITE, Color.RED, Color.BLUE, Color.RED, Color.BLUE, Color.RED},
                {Color.BLUE, Color.RED, Color.BLUE, Color.WHITE, Color.RED, Color.BLUE, Color.WHITE, Color.WHITE, Color.WHITE, Color.RED, Color.BLUE, Color.BLUE, Color.BLUE, Color.WHITE, Color.BLUE, Color.WHITE, Color.RED, Color.BLUE, Color.BLUE, Color.WHITE},
                {Color.WHITE, Color.BLUE, Color.WHITE, Color.RED, Color.RED, Color.RED, Color.BLUE, Color.BLUE, Color.BLUE, Color.BLUE, Color.WHITE, Color.RED, Color.BLUE, Color.RED, Color.RED, Color.RED, Color.RED, Color.BLUE, Color.RED, Color.RED},
                {Color.RED, Color.WHITE, Color.BLUE, Color.BLUE, Color.BLUE, Color.RED, Color.RED, Color.BLUE, Color.RED, Color.BLUE, Color.BLUE, Color.WHITE, Color.WHITE, Color.RED, Color.RED, Color.BLUE, Color.RED, Color.WHITE, Color.WHITE, Color.RED},
                {Color.RED, Color.BLUE, Color.BLUE, Color.RED, Color.RED, Color.RED, Color.BLUE, Color.RED, Color.BLUE, Color.RED, Color.BLUE, Color.RED, Color.RED, Color.RED, Color.BLUE, Color.RED, Color.WHITE, Color.BLUE, Color.BLUE, Color.RED},
                {Color.RED, Color.WHITE, Color.BLUE, Color.BLUE, Color.BLUE, Color.RED, Color.BLUE, Color.RED, Color.WHITE, Color.RED, Color.BLUE, Color.RED, Color.WHITE, Color.RED, Color.RED, Color.BLUE, Color.BLUE, Color.WHITE, Color.BLUE, Color.RED},
                {Color.WHITE, Color.WHITE, Color.RED, Color.BLUE, Color.BLUE, Color.WHITE, Color.RED, Color.BLUE, Color.RED, Color.WHITE, Color.RED, Color.BLUE, Color.WHITE, Color.RED, Color.BLUE, Color.RED, Color.RED, Color.RED, Color.RED, Color.RED},
                {Color.RED, Color.RED, Color.BLUE, Color.RED, Color.BLUE, Color.BLUE, Color.RED, Color.BLUE, Color.RED, Color.WHITE, Color.BLUE, Color.RED, Color.RED, Color.WHITE, Color.BLUE, Color.BLUE, Color.BLUE, Color.BLUE, Color.BLUE, Color.BLUE},
                {Color.RED, Color.BLUE, Color.RED, Color.RED, Color.WHITE, Color.BLUE, Color.BLUE, Color.BLUE, Color.BLUE, Color.WHITE, Color.BLUE, Color.RED, Color.BLUE, Color.BLUE, Color.BLUE, Color.BLUE, Color.WHITE, Color.RED, Color.WHITE, Color.WHITE},
                {Color.RED, Color.BLUE, Color.RED, Color.BLUE, Color.BLUE, Color.RED, Color.RED, Color.BLUE, Color.WHITE, Color.RED, Color.BLUE, Color.RED, Color.BLUE, Color.RED, Color.BLUE, Color.RED, Color.RED, Color.BLUE, Color.RED, Color.WHITE},
                {Color.RED, Color.RED, Color.BLUE, Color.RED, Color.WHITE, Color.WHITE, Color.BLUE, Color.BLUE, Color.WHITE, Color.BLUE, Color.WHITE, Color.BLUE, Color.BLUE, Color.RED, Color.RED, Color.RED, Color.BLUE, Color.BLUE, Color.BLUE, Color.RED}
        };
        Utils.setGrid(grid, gridArray);

        int steps = 0;
        while (!Utils.allResidentsHappy(grid, size)) {
            steps++;
            grid.simulate(1);
        }
        assertTrue(steps <= 17 && steps > 1);
    }

}
