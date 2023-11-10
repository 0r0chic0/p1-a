package erehwon;

import org.junit.jupiter.api.Test;

import java.awt.*;
import java.util.Map;
import java.util.Random;

import static erehwon.Task1Tests.enumerateColors;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Task2Tests {

    @Test
    public void testReset1() {
        final int size = 10;
        RedBlueGrid rbg = new RedBlueGrid(size, 1, 0.1, 0.4, 0.3);
        Map<Color, Integer> countMap = enumerateColors(rbg);
        assertEquals(10, countMap.get(Color.WHITE));
        assertEquals(36, countMap.get(Color.RED));
        assertEquals(54, countMap.get(Color.BLUE));

        rbg.reset(0.2, 0.8, 0.3);
        countMap = enumerateColors(rbg);
        assertEquals(20, countMap.get(Color.WHITE));
        assertEquals(64, countMap.get(Color.RED));
        assertEquals(16, countMap.get(Color.BLUE));
    }

    @Test
    public void testReset2() {
        final int size = 100;
        Random r = new Random();
        RedBlueGrid rbg = new RedBlueGrid(size, 1, r.nextInt(10) / 10.0, r.nextInt() / 10.0, (1 + r.nextInt(8)) / 10.0);

        rbg.reset(0.2, 0.8, 0.3);
        Map <Color, Integer> countMap = enumerateColors(rbg);
        assertEquals(2000, countMap.get(Color.WHITE));
        assertEquals(6400, countMap.get(Color.RED));
        assertEquals(1600, countMap.get(Color.BLUE));
    }

    @Test
    public void testShift() {
        final int size = 20;
        final double trailsFraction = 0.2;
        Random r = new Random();
        RedBlueGrid rbg = new RedBlueGrid(size, 1, r.nextInt(10) / 10.0, r.nextInt() / 10.0, (1 + r.nextInt(8)) / 10.0);

        final int trials = (int)((size * size) * trailsFraction);

        for (int t = 0; t < trials; t++) {
            int i = r.nextInt(size);
            int j = r.nextInt(size);
            Color c1 = rbg.getColor(i, j);
            rbg.shiftColor(i, j);
            Color c2 = rbg.getColor(i, j);

            if (c1.equals(Color.WHITE)) {
                assertEquals(Color.RED, c2);
            }
            if (c1.equals(Color.RED)) {
                assertEquals(Color.BLUE, c2);
            }
            if (c1.equals(Color.BLUE)) {
                assertEquals(Color.WHITE, c2);
            }
        }

    }
}
