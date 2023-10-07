package erehwon;

import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import  java.util.Random;
import java.util.List;

public class RedBlueGrid {
    private static final Color[] COLORS = {Color.WHITE, Color.RED, Color.BLUE};
    private Color[][] grid;
    private final int neighborhoodDistance;
    private double happinessThreshold;
    public final int size;
    public int counterRed = 0; // changed name

    public int counterBlue = 0; // changed name

    /**
    * Constructs n x n RedBlueGrid.
    * Vacant cells are white
     * if fractions result in non integer values then they will be rounded down
    * @param size:  number of rows and columns to initialize grid with;
     *              requires size > 0
    * @param neighborhoodDistance:  number of steps in any direction around a cell for
     *                              another cell to be considered in the neighborhood;
     *                              requires neighborhoodDistance >= 1
    * @param fractionVacant:    the fraction of vacant (white) cells in the grid;
     *                          requires fractionVacant >= 0
    * @param fractionRed:   the fraction of non-vacant (not white) red cells in the grid
     *                      requires fractionRed >= 0
    * @param happinessThreshold:    the fraction of same color cells in the neighborhood of a cell to attain happiness;
     *                              requires happinessThreshold >= 0
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
     * Randomizes grid cell colors with specified inputs.
     * @param fractionVacant:   fraction of vacant (white) cells the grid is to have;
     *                          requires fractionVacant >= 0
     * @param fractionRed:  fraction of non-vacant red cells the grid is to have;
     *                      requires fractionRed >= 0
     * @author  dzhen2023
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
               counterRed++;
           }
            if (numberOfBlue > 0) {
               while (!grid[y][x].equals(COLORS[0])) {
                   y = rng.nextInt(size);
                   x = rng.nextInt(size);
               }
               grid[y][x] = COLORS[2];
               numberOfBlue--;
               counterBlue++;
           }
        }
    }

    /**
     * Gets color of a specified cell.
     * @param row:  row index of the grid
     * @param col:  column index of the grid
     * @return  color at specified grid (Red, Blue, or White)
     * @throws  IllegalArgumentException when row and column index are out of bounds
     * @author  0r0chic0
     */
    public Color getColor(int row, int col) {
        if(!withinBounds(row,col))
       {
           throw new IllegalArgumentException("Out of Bounds");
       }
       else {
           return grid[row][col];
       }
    }

    /**
     * Changes color of specified cell.
     * @param row:  row index of the grid
     * @param col:  column index of the grid
     * @param color:    color to be changed to
     * @return  if color change is successful
     * @author  kevinlin1029
     */
    public boolean setColor(int row, int col, Color color) {
        if (!withinBounds(row, col))
            return false;

        boolean isValidColor = false;

        //this for loop is to check whether the color is valid
        for (Color validColor: COLORS){
            if (color.equals(validColor)){
                isValidColor = true;
                break;
            }
        }
        if (!isValidColor){return false;}

        grid[row][col] = color; //if the color is valid, set the color into cell

        return true; // you may need to change this
    }



    /**
     * Rotates a cell's color by this order: WHITE -> RED -> BLUE -> WHITE.
     * @param row:  row index of grid
     * @param col:  column index of grid
     * @throws IllegalArgumentException when row and column index are out of bounds
     * @author  kevinlin1029
     */
    public void shiftColor(int row, int col) {
        if (!withinBounds(row,col)) {
            throw new IllegalArgumentException("Out of Bounds");
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
     * Randomize grid colors and cell happiness threshold with desired inputs.
     * @param fractionVacant:   fraction of vacant (white) cells for grid to have;
     *                          requires fractionVacant >= 0
     * @param fractionRed:  fraction of red cells for grid to have;
     *                      requires fractionRed >= 0
     * @param happinessThreshold:   new happinessThreshold;
     *                              requires happinessThreshold >= 0
     * @author  dzhen2023
     */
    public void reset(double fractionVacant,
                      double fractionRed,
                      double happinessThreshold) {
        this.happinessThreshold = happinessThreshold;
        randomizeGrid(fractionVacant,fractionRed);
    }

    /**
     * @param row: row index of grid
     * @param col: column index of grid
     * @return  if resident at specified cell is happy
     * @throws  IllegalArgumentException if specified index is out of bounds
     * @author  dzhen2023
     */
    public boolean isHappy(int row, int col) {
        Color colorAtCell = getColor(row,col);
        return happinessCheck(row,col,colorAtCell);
    }


    /**
     * @return the fraction of happy residents
     * @author 0r0chic0
     */
    public double fractionHappy() {
        int counter = 0;
       for(int i = 0; i < size ; i++)
       {
           for(int j = 0; j < size ; j++)
           {
               if(isHappy(i,j))
                   counter++;
           }
       }
       return (double) counter / (size * size); // cast double to value and shortened code
    }


    /**
     * Moves unhappy residents to vacant (white) cells randomly in one time step.
     * @author kevinlin1029
     */
    public void oneTimeStep() {
        //use point class to store coordinates of cells;
        List<Point> unhappyPeople = new ArrayList<>();
        List<Point> vacant = new ArrayList<>();

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (grid[i][j].equals(COLORS[0])) {
                    vacant.add(new Point(i, j));  //identify vancant cells
                } else if (!isHappy(i, j)) {
                    unhappyPeople.add(new Point(i, j)); //identify unhappy people
                }
            }
        }

        // ensures that the movement of unhappy people to vacant cells is done in a randomized manner.
        Collections.shuffle(unhappyPeople);
        //compare which one is the minimum
        int mini = Math.min(vacant.size(), unhappyPeople.size());

        for (int i = 0; i < mini; i++) {
            Point origin = unhappyPeople.get(i);
            Point empty = vacant.get(i);

            grid[empty.x][empty.y] = grid[origin.x][origin.y]; // Move  person to the empty cell
            grid[origin.x][origin.y] = COLORS[0]; // Set the original cell to vacant after moving people
        }
    }


    /**
     * Simulates the RedBlueGrid in a non-randomized way
     * @param numSteps:     number of time steps the simulation will run for
     * @author  dzhen2023
     */
    public void simulate(int numSteps) {
        for (int k = 0; k < numSteps; k++) {

            List<Point> unhappyPeople = new ArrayList<>();
            List<Point> vacant = new ArrayList<>();

            for (int i = 0; i < size; i++) {
                for (int j = 0; j < size; j++) {
                    if (grid[i][j].equals(COLORS[0])) {
                        vacant.add(new Point(i, j));
                    } else if (!isHappy(i, j)) {
                        unhappyPeople.add(new Point(i, j));
                    }
                }
            }

            for (int i = 0; i < vacant.size(); i++) {
                int vacantX = vacant.get(i).x;
                int vacantY = vacant.get(i).y;

                for (int j = 0; j < unhappyPeople.size(); j++) {
                    int unhappyX = unhappyPeople.get(j).x;
                    int unhappyY = unhappyPeople.get(j).y;

                    if (happinessCheck(vacantX,vacantY,grid[unhappyX][unhappyY])) {
                        grid[vacantX][vacantY] = grid[unhappyX][unhappyY];
                        grid[unhappyX][unhappyY] = COLORS[0];

                        break;
                    }
                }
            }

            vacant.clear();
            unhappyPeople.clear();
        }


    }

    /**
     * effects: checks specified cell if a colored cell would be happy there
     * @param row is row index of grid
     * @param col is column index of grid
     * @param color is color to check happiness of
     * @throws IllegalArgumentException when grid index is out of range
     * @return if the color would be happy at specified row and col
     * @author dzhen2023
     */
    private boolean happinessCheck(int row, int col, Color color) {
        if (!withinBounds(row, col)) {
            throw new IllegalArgumentException("Out of Bounds");
        }

        int cellCount = 0;
        int sameCount = 0;
        int topBound = -neighborhoodDistance;
        int bottomBound = neighborhoodDistance;
        int leftBound = -neighborhoodDistance;
        int rightBound = neighborhoodDistance;

        for (int i = 0; i <= rightBound; i++) {
            if (col + i >= size) {
                rightBound = i - 1;
                break;
            }
            for (int j = -1; j >= topBound; j--) {
                if (row + j < 0) {
                    topBound = j + 1;
                    break;
                }
                cellCount++;
                if (getColor(row + j, col).equals(color)) {
                    sameCount++;
                }
            }

            for (int j = 1; j <= bottomBound; j++) {
                if (row + j >= size) {
                    bottomBound = j - 1;
                    break;
                }
                cellCount++;
                if (getColor(row + j, col).equals(color)) {
                    sameCount++;
                }
            }

            cellCount++;
            if (getColor(row, col + i).equals(color)) {
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
                if (getColor(row + j,col).equals(color)) {
                    sameCount++;
                }
            }

            for (int j = 1; j <= bottomBound; j++) {
                cellCount++;
                if (getColor(row + j,col).equals(color)) {
                    sameCount++;
                }
            }

            cellCount++;
            if (getColor(row, col + i).equals(color)) {
                sameCount++;
            }
        }

        sameCount--;
        cellCount--;

        return ((double) sameCount / (double) cellCount) >= happinessThreshold;
    }

    /**
     * @param row: row index of grid
     * @param col: column index of grid
     * @return if row or column index is out of bounds
     */
    private boolean withinBounds (int row, int col) {
        return !(row < 0 || row >= size || col < 0 || col >= size);
    }

}

// for testing purposes
class Main {
    public static void main (String[] args) {
        // test to check if the grid is configured properly
        RedBlueGrid test1 = new RedBlueGrid(8, 4, 0.2, 0.5, 0.3);
        double square = test1.size * test1.size;
        double r =  Math.round((test1.counterRed / (0.8 * square))*10);
        double red = r/10;
        double b = Math.round((test1.counterBlue / (0.8 * square))*10);
        double blue = b/10;
        double v = Math.round(((square - (test1.counterRed + test1.counterRed))/square)*10);
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
