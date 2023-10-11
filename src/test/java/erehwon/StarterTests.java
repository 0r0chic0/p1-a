package erehwon;

import org.junit.jupiter.api.Test;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class StarterTests {

    @Test
    public void testCorrectCreation() {
        // Test a large grid size
        RedBlueGrid gridLarge = new RedBlueGrid(1000,4,0.2,0.5,0.5);

        RedBlueGrid grid1 = new RedBlueGrid(8, 4, 0.2, 0.5, 0.3);
        // checking correct number of vacant cells
        assertEquals(13, (grid1.getSize() * grid1.getSize()) - grid1.getNumOfRed() - grid1.getNumOfBlue());
        // checking correct number of red cells
        assertEquals(26, grid1.getNumOfRed());
        // checking correct number of red cells
        assertEquals(25, grid1.getNumOfBlue());

        // checks if grid size is correct
        assertEquals(8, grid1.getSize());

        // checks if reset is working as intended
        grid1.reset(0.2,0.5,0.3);
        // checking correct number of vacant cells
        assertEquals(13, (grid1.getSize()* grid1.getSize()) - grid1.getNumOfRed() - grid1.getNumOfBlue());
        // checking correct number of red cells
        assertEquals(26, grid1.getNumOfRed());
        // checking correct number of red cells
        assertEquals(25, grid1.getNumOfBlue());

        // checking when we use 0
        RedBlueGrid grid2 = new RedBlueGrid(8, 1, 0, 0, 0.5);
        assertEquals(0,(grid2.getSize() * grid2.getSize()) - grid2.getNumOfRed() - grid2.getNumOfBlue());
        assertEquals(0, grid2.getNumOfRed());
        assertEquals(grid2.getSize() * grid2.getSize(), grid2.getNumOfBlue());

        // checking when we use 1

        RedBlueGrid grid3 = new RedBlueGrid(8, 1, 1, 1, 0.5);
        assertEquals(grid3.getSize() * grid3.getSize(), (grid3.getSize() * grid3.getSize()) - grid3.getNumOfRed() - grid3.getNumOfBlue() );
        assertEquals(0, grid3.getNumOfRed());
        assertEquals(0, grid3.getNumOfBlue());
    }

    @Test
    public void testGetColor() {
        RedBlueGrid grid1 = new RedBlueGrid(5, 1, 0.25, 0.5, 0.3);

        // checks when a color is correct
        grid1.setColor(0,0,Color.RED);
        assertEquals(Color.RED, grid1.getColor(0,0));

        // checks when a color is wrong
        grid1.setColor(1,4, Color.BLUE);
        assertNotEquals(Color.RED, grid1.getColor(1, 4));
    }

    @Test
    public void testSetColor() {
        RedBlueGrid grid1 = new RedBlueGrid(10, 1, 0.3, 0.4, 0.35);

        // checks when grid is out of bounds
        assertFalse(grid1.setColor(11,11,Color.BLUE));

        // checks when input color is invalid
        assertFalse(grid1.setColor(5,2,Color.BLACK));

        // checks if a color change works
        grid1.setColor(1,1,Color.RED);
        assertEquals(Color.RED, grid1.getColor(1,1));
    }

    @Test
    public void testShiftColor() {
        // checks if grid shifts a cells color in the correct order
        RedBlueGrid grid1 = new RedBlueGrid(10, 2, 0.3, 0.6, 0.4);
        grid1.setColor(3,4,Color.WHITE);

        grid1.shiftColor(3,4);
        assertEquals(Color.RED, grid1.getColor(3,4));

        grid1.shiftColor(3,4);
        assertEquals(Color.BLUE, grid1.getColor(3,4));

        grid1.shiftColor(3,4);
        assertEquals(Color.WHITE, grid1.getColor(3,4));
    }

    @Test
    public void testIsHappy() {
        RedBlueGrid grid1 = new RedBlueGrid(3,1,0,0,1);

        // checks if happy when all cells in neighborhood are the same color
        assertTrue(grid1.isHappy(1, 1));
        assertTrue(grid1.isHappy(0,0));

        // checks when the cell should not be happy
        grid1.setColor(1,1,Color.RED);
        assertFalse(grid1.isHappy(1,1));
        assertFalse(grid1.isHappy(0,0));
        assertFalse(grid1.isHappy(2,2));

        // checks when neighborhoodDistance is out of bounds
        RedBlueGrid grid2 = new RedBlueGrid(3,2,0,0,1);
        assertTrue(grid2.isHappy(1, 1));
        assertTrue(grid2.isHappy(0,0));

        RedBlueGrid grid3 = new RedBlueGrid(3,2,0,0,0.5);
        grid1.setColor(1,1,Color.RED);
        grid1.setColor(0,1,Color.RED);
        grid1.setColor(0,0,Color.RED);
        grid1.setColor(0,2,Color.RED);
        grid1.setColor(1,0,Color.RED);
        assertTrue(grid3.isHappy(1,1));
        assertTrue(grid3.isHappy(2,2));


    }

    @Test
    public void testFractionHappy() {
        RedBlueGrid grid1 = new RedBlueGrid(3,1,0,0,1);
        assertEquals(1, grid1.fractionHappy());
        grid1.setColor(1,1,Color.RED);
        assertEquals(0,grid1.fractionHappy());
    }

    @Test
    public void testOneStep() {
        RedBlueGrid grid1 = new RedBlueGrid(8, 1, 0.2, 0.5, 0.7);
        List<Point> unhappyCells = new ArrayList<>();
        List<Point> vacantCells = new ArrayList<>();

        for (int i = 0; i < grid1.getSize(); i++) {
            for (int j = 0; j < grid1.getSize(); j++) {
                if (grid1.getColor(i, j).equals(Color.WHITE)) {
                    vacantCells.add(new Point(i, j));
                } else if (!grid1.isHappy(i, j)) {
                    unhappyCells.add(new Point(i, j));
                }
            }
        }

        int numOfUnhappy = unhappyCells.size();
        int numOfVacant = vacantCells.size();

        int numOfFilled = 0;
        int numOfMoved = 0;

        if (numOfVacant >= numOfUnhappy) {
            for (Point vacantCell : vacantCells) {
                if (!grid1.getColor(vacantCell.x, vacantCell.y).equals(Color.WHITE)) {
                    numOfFilled++;
                }
            }

            for (Point unhappyCell : unhappyCells) {
                if (grid1.getColor(unhappyCell.x, unhappyCell.y).equals(Color.WHITE)) {
                    numOfMoved++;
                }
            }

            assertEquals(numOfMoved, numOfFilled);
            assertEquals(numOfUnhappy, numOfMoved);
        }

        if (numOfVacant < numOfUnhappy) {
            for (Point vacantCell : vacantCells) {
                if (grid1.getColor(vacantCell.x, vacantCell.y).equals(Color.WHITE)) {
                    numOfFilled++;
                }
            }
            assertEquals(numOfVacant,numOfFilled);
        }
    }
}


