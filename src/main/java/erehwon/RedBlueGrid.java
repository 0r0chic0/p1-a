package erehwon;

import java.awt.Color;
import  java.util.Random;

public class RedBlueGrid {
    private static final Color[] COLORS = {Color.WHITE, Color.RED, Color.BLUE};
    private Color[][] grid;
    private int neighborhoodDistance;
    private double happinessThreshold;
    public int size;
    private int cellsInNeighborhood;

    public int counter1 = 0;

    public int counter2 = 0;

    /**
    * Constructs n x n RedBlueGrid
    * Vacant cells are white
     * if fractions result in non integer values then they will be rounded down
    * @param size is size of constructed grid
    * @param neighborhoodDistance is steps need to reach a cell within the neighborhood
    * @param fractionVacant is percentage of vacant cells in grid
    * @param fractionRed is percentage of non-vacant cells that are red in grid
    * @param happinessThreshold: percentage of same color cells in the neighborhood for a cell to attain happiness, > 0
    * @author dzhen2023
    */
    public RedBlueGrid(int size,
                       int neighborhoodDistance,
                       double fractionVacant,
                       double fractionRed,
                       double happinessThreshold) {
        grid = new Color[size][size];
        this.happinessThreshold = happinessThreshold;
        this.neighborhoodDistance = neighborhoodDistance;
        this.size = size;
        randomizeGrid(fractionVacant,fractionRed);
        }

    /**
     * effects: changes grid colors with given values
     * @param fractionVacant is percentage of vacant cells in grid
     * @param fractionRed is percentage of non-vacant cells that are red in grid
     */
    private void randomizeGrid (double fractionVacant, double fractionRed) {
        int numberOfVacant = (int) (((double)(size*size)) * fractionVacant);
        int numberOfNonVacant = (size*size) - numberOfVacant;
        int numberOfRed = (int) (((double) numberOfNonVacant) * fractionRed);
        int numberOfBlue = numberOfNonVacant - numberOfRed;
        Random rng = new Random();

        for (int i=0; i < size; i++) {
            for (int j=0; j < size; j++) {
                grid[i][j] = COLORS[0];
            }
        }

        for (int x = rng.nextInt(size), y = rng.nextInt(size); numberOfRed > 0 || numberOfBlue > 0;) //changed && to ||
        {
           if (numberOfRed > 0) {
               while (!grid[y][x].equals(COLORS[0])) {
                   y = rng.nextInt(size);
                   x = rng.nextInt(size);
               }
               grid[y][x] = COLORS[1];
               numberOfRed--;
               counter1++;
           }
            if (numberOfBlue > 0) {
               while (!grid[y][x].equals(COLORS[0])) {
                   y = rng.nextInt(size);
                   x = rng.nextInt(size);
               }
               grid[y][x] = COLORS[2];
               numberOfBlue--;
               counter2++;
           }
        }
    }

    // do nothing for cells that are not on the grid
    public Color getColor(int row, int col) {
        if(row < 0 || row > grid.length || col < 0 || col > grid.length)
       {
           throw new IllegalArgumentException("Invalid Grid Values");
       }
       else {
           return grid[row][col];
       }
    }

    // can only set a valid colour for this project
    // do nothing for cells that are not on the grid
    // this method may violate the fractional allocation
    // of space but we are still implementing it
    public boolean setColor(int row, int col, Color color) {

        //check the grid's boundary
        if (row < 0 || row >= grid.length || col < 0 || col >= grid[0].length) {
            return false;
        }

        boolean checkColorValidity = false;

        //this for loop is to check whether the color is valid
        for (Color key: COLORS){
            if (color.equals(key)){
                checkColorValidity = true;
                break;
            }
        }
        if (!checkColorValidity){return false;}

        grid[row][col] = color; //if the color is valid, set the color into cell

        return true; // you may need to change this
    }

    // for rotating through the colours in the order
    // WHITE -> RED -> BLUE -> WHITE -> ...
    public void shiftColor(int row, int col) {
        if (row < 0 || row >= grid.length || col < 0 || col >= grid[0].length) {
            return; // Out of  boundaries
        }

        Color currentColor = grid[row][col];

        // find the next color
        if (currentColor.equals(Color.WHITE)) {
            grid[row][col] = Color.RED;
        } else if (currentColor.equals(Color.RED)) {
            grid[row][col] = Color.BLUE;
        } else if (currentColor.equals(Color.BLUE)) {
            grid[row][col] = Color.WHITE;
        }
    }

    /**
     *
     * @param fractionVacant: fraction of vacant cells to mutate grid with
     * @param fractionRed: fraction of red cells to mutate grid with
     * @param happinessThreshold: new happinessThreshold
     * @author dzhen2023
     */
    public void reset(double fractionVacant,
                      double fractionRed,
                      double happinessThreshold) {
        this.happinessThreshold = happinessThreshold;
        randomizeGrid(fractionVacant,fractionRed);
    }

    /**
     *
     * @param row: row index of grid
     * @param col: col index of grid
     * @return if the resident at specified cell is happy, throws illegalArgumentException if out of bounds
     * @author dzhen2023
     */
    public boolean isHappy(int row, int col) {
        if (row < 0 || col < 0 || row >= grid.length || col >= grid[0].length) {
            throw new IllegalArgumentException("Invalid Grid Index");
        }

        Color currentCellColor = getColor(row,col);
        int cellCount = 0;
        int sameCount = 0;
        int topBound = -neighborhoodDistance;
        int bottomBound = neighborhoodDistance;
        int leftBound = -neighborhoodDistance;
        int rightBound = neighborhoodDistance;

        for (int i = 0; i <= rightBound; i++) {
            if (col + i >= grid[0].length) {
                rightBound = i - 1;
                break;
            }
            for (int j = -1; j >= topBound; j--) {
                if (row + j < 0) {
                    topBound = j + 1;
                    break;
                }
                cellCount++;
                if (getColor(row + j, col).equals(currentCellColor)) {
                    sameCount++;
                }
            }

            for (int j = 1; j <= bottomBound; j++) {
                if (row + j >= grid.length) {
                    bottomBound = j - 1;
                    break;
                }
                cellCount++;
                if (getColor(row + j, col).equals(currentCellColor)) {
                    sameCount++;
                }
            }

            cellCount++;
            if (getColor(row, col + i).equals(currentCellColor)) {
                sameCount++;
            }
        }

        for (int i = -1; i >= leftBound; i--) {
            if (col + i < 0) {
                leftBound = i + 1;
                break;
            }

            for (int j = -1; j >= topBound; j--) {
                cellCount++;
                if (getColor(row + j,col).equals(currentCellColor)) {
                    sameCount++;
                }
            }

            for (int j = 1; j <= bottomBound; j++) {
                cellCount++;
                if (getColor(row + j,col).equals(currentCellColor)) {
                    sameCount++;
                }
            }

            cellCount++;
            if (getColor(row, col + i).equals(currentCellColor)) {
                sameCount++;
            }
        }

        sameCount--;
        cellCount--;

        return ((double) sameCount / (double) cellCount) >= happinessThreshold;
    }

    /**
     * @return true if color at index is the same as input color
     * @author dzhen2023
     */

    // what fraction of the erehwon residents are happy?
    public double fractionHappy() {
        int counter = 0;
        double frac = 0;
       for(int i = 0; i < size ; i++)
       {
           for(int j = 0; j < size ; j++)
           {
               if(isHappy(i,j))
                   counter++;
           }
       }
      frac = counter / (size * size);
       return frac;
    }
    /**
     * @return frac which is the fraction of happy residents
     * @author 0r0chic0
     */

    // simulate exactly one time step of movement
    public void oneTimeStep() {
        // TODO: Implement this method
    }

    // simulate multiple time steps
    public void simulate(int numSteps) {
        // TODO: Implement this method
    }

}

// for testing purposes
class Main {
    public static void main (String[] args) {
        // test to check if the grid is configured properly
        RedBlueGrid test1 = new RedBlueGrid(8, 4, 0.2, 0.5, 0.3);
        double square = test1.size * test1.size;
        double r =  Math.round((test1.counter1 / (0.8 * square))*10);
        double red = r/10;
        double b = Math.round((test1.counter2 / (0.8 * square))*10);
        double blue = b/10;
        double v = Math.round(((square - (test1.counter1 + test1.counter2))/square)*10);
        double vacant = v/10;
        System.out.println(red);
        System.out.println(blue);
        System.out.println(vacant);
        System.out.println((test1.size) * (test1.size));
        if (red == 0.5 && blue == 0.5 && vacant == 0.2) {
            System.out.println("True");
        }
        else {
            System.out.println("False");
        }
        //test for 2nd task
        RedBlueGrid test2 = new RedBlueGrid(10, 2, 0.3, 0.6, 0.4);

        // shiftColor method test
        System.out.println("At the beginning: color at (3,4): " + test2.getColor(3, 4));
        test2.shiftColor(3, 4);
        System.out.println("Color at (3,4) through 1st shift: " + test2.getColor(3, 4));
        test2.shiftColor(3, 4);
        System.out.println("Color at (3,4) through 2nd shift: " + test2.getColor(3, 4));
        test2.shiftColor(3, 4);
        System.out.println("Color at (3,4) through 3rd shift: " + test2.getColor(3, 4));

        //  reset method test
        // 60% vacant; 30% red; 0.5 for happinessThreshold
        test2.reset(0.6, 0.3, 0.5);
        System.out.println("Color at (3,4) through reset: " + test2.getColor(3, 4));

    }
}
