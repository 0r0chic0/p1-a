package erehwon;

import java.awt.*;

public class Utils {

    protected static void setGrid(RedBlueGrid grid, Color[][] colorArray) {
        int size = colorArray.length;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                grid.setColor(i, j, colorArray[i][j]);
            }
        }
    }

    protected static Color[][] getArray(RedBlueGrid grid, final int size) {
        Color[][] colorArray = new Color[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                colorArray[i][j] = grid.getColor(i, j);
            }
        }
        return colorArray;
    }

    protected static void printColorArray(final Color[][] colorArray, final int size) {
        for (int i = 0; i < size; i++) {
            System.out.print("{");
            for (int j = 0; j < size; j++) {
                switch (colorArray[i][j].hashCode()) {
                    case -1 -> System.out.print("Color.WHITE, ");
                    case -65536 -> System.out.print("Color.RED, ");
                    case -16776961 -> System.out.print("Color.BLUE, ");
                }
            }
            System.out.println("},");
        }
    }

    protected static boolean allResidentsHappy(RedBlueGrid grid, int size) {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (grid.getColor(i, j) == null
                        || grid.getColor(i, j).equals(Color.WHITE)) {
                    continue;
                }
                if (!grid.isHappy(i, j)) {
                    return false;
                }
            }
        }
        return true;
    }

}
