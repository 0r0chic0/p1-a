package erehwon;

import org.junit.jupiter.api.Test;

public class StarterTests {

    @Test
    public void testCorrectCreation() {
        RedBlueGrid rbGrid = new RedBlueGrid(10, 1, 0.3, 0.4, 0.35);
        // complete the test by verifying that the grid has the correct number
        // of vacant, red, and blue cells
    }

    @Test
    public void testSetColor() {
        RedBlueGrid rbGrid = new RedBlueGrid(10, 1, 0.3, 0.4, 0.35);
        // complete the test by setting a cell's colour and verifying that
        // the colour was correctly changed
    }

}