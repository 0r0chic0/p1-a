package erehwon;

import org.junit.jupiter.api.Test;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

public class Task1Tests {

    protected static Map<Color, Integer> enumerateColors(RedBlueGrid rbg)  {
        HashMap<Color, Integer> countMap = new HashMap<>();
        final int size = rbg.getSize();
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                Color ijCell = rbg.getColor(i, j);
                int count = countMap.getOrDefault(ijCell, 0);
                count++;
                countMap.put(ijCell, count);
            }
        }
        return countMap;
    }

    @Test
    public void testConstructorSimple() {
        final int size = 10;
        RedBlueGrid rbg = new RedBlueGrid(10, 1, 0.1, 0.4, 0.3);
        Map<Color, Integer> countMap = enumerateColors(rbg);
        assertEquals(10, countMap.get(Color.WHITE));
        assertEquals(36, countMap.get(Color.RED));
        assertEquals(54, countMap.get(Color.BLUE));
    }

    @Test
    public void testConstructorRounding() {
        final int size = 17;
        final double fractionVacant     = 0.1;
        final double fractionRed        = 0.4;
        final double happinessThreshold = 0.3;
        final int    trials             = 20;

        double expVacant = trials * (size * size) * fractionVacant;
        double expRed    = trials * (size * size) * (1 - fractionVacant) * fractionRed;
        double expBlue   = trials * (size * size) * (1 - fractionVacant) * (1 - fractionRed);

        double vacant = 0;
        double red = 0;
        double blue = 0;

        for (int t = 0; t < trials; t++) {
            RedBlueGrid rbg = new RedBlueGrid(size, 1, fractionVacant, fractionRed, happinessThreshold);
            Map<Color, Integer> countMap = enumerateColors(rbg);
            vacant += countMap.get(Color.WHITE);
            red += countMap.get(Color.RED);
            blue += countMap.get(Color.BLUE);
        }

        assertTrue(vacant >= expVacant - trials && vacant <= expVacant + trials);
        assertTrue(red >= expRed - trials && red <= expRed + trials);
        assertTrue(blue >= expBlue - trials && blue <= expBlue + trials);
    }

    @Test
    public void testSetColorValid() {
        final int size = 17;
        final double fractionVacant     = 0.1;
        final double fractionRed        = 0.4;
        final double happinessThreshold = 0.3;

        RedBlueGrid grid = new RedBlueGrid(size, 2, fractionVacant, fractionRed, happinessThreshold);

        Random r = new Random();
        final int i = r.nextInt(size);
        final int j = r.nextInt(size);

        Color c = grid.getColor(i, j);

        if (c == Color.RED) {
            assertTrue(grid.setColor(i, j, Color.BLUE));
            return;
        }
        if (c == Color.BLUE) {
            assertTrue(grid.setColor(i, j, Color.WHITE));
            return;
        }
        if (c == Color.WHITE) {
            assertTrue(grid.setColor(i, j, Color.RED));
        }
    }

    @Test
    public void testSetColorInvalid() {
        final int size = 7;
        final double fractionVacant     = 0.1;
        final double fractionRed        = 0.4;
        final double happinessThreshold = 0.3;

        RedBlueGrid grid = new RedBlueGrid(size, 2, fractionVacant, fractionRed, happinessThreshold);

        assertFalse(grid.setColor(-1, -1, Color.BLUE));
        assertFalse(grid.setColor(size + 1, 0, Color.BLUE));
    }

    @Test
    public void testGetColorInvalid() {
        final int size = 7;
        final double fractionVacant     = 0.1;
        final double fractionRed        = 0.4;
        final double happinessThreshold = 0.3;

        RedBlueGrid grid = new RedBlueGrid(size, 2, fractionVacant, fractionRed, happinessThreshold);

        try {
            Color c = grid.getColor(-1, -1);
            if (c != null) {
                fail();
            }
        }
        catch (Exception e) {
            assertTrue(true, "Caught exception. OK!");
        }

        try {
            Color c = grid.getColor(size + 1, size + 2);
            if (c != null) {
                fail();
            }
        }
        catch (Exception e) {
            assertTrue(true, "Caught exception. OK!");
        }
    }

}
