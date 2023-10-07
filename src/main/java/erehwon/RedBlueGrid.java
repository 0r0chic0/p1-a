package erehwon;

import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import  java.util.Random;
import java.util.List;

public class RedBlueGrid {
    private static final Color[] COLORS = {Color.WHITE, Color.RED, Color.BLUE};
    private Color[][] grid;
    private int neighborhoodDistance;
    private double happinessThreshold;
    public int size;

    public int counter1 = 0;

    public int counter2 = 0;

    /**
    * Constructs n x n RedBlueGrid
    * Vacant cells are white
     * if fractions result in non integer values then they will be rounded down
    * @param size is size of constructed grid
    * @param neighborhoodDistance is steps need to reach a cell within the neighborhood
    * @param fractionVacant is the fraction of vacant cells in grid
    * @param fractionRed is the fraction of non-vacant cells that are red in grid
    * @param happinessThreshold is the fraction of same color cells in the neighborhood for a cell to attain happiness and is > 0
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
     * @author dzhen2023
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

    /**
     * @param row is the row index of the grid
     * @param col is the column index of the grid
     * @return the color at specified grid
     * @throws IllegalArgumentException when row and column index are out of bounds
     * @author 0r0chic0
     */
    public Color getColor(int row, int col) {
        if(row < 0 || row > grid.length || col < 0 || col > grid.length)
       {
           throw new IllegalArgumentException("Invalid Grid Values");
       }
       else {
           return grid[row][col];
       }
    }

    /**
     *
     * @param row is the row index of the grid
     * @param col is the column index of the grid
     * @param color is the color to be set
     * @return if color change is successful
     * @author kevinlin1029
     */
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



    /**
     * effects: rotates a cell's color by this order: WHITE -> RED -> BLUE -> WHITE
     * @param row is the row index of grid
     * @param col is the column index of grid
     * @author kevinlin1029
     */
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
     * effects: randomize RedBlueGrid with desired inputs entered in the GUI
     * @param fractionVacant is fraction of vacant cells to mutate grid with
     * @param fractionRed is fraction of red cells to mutate grid with
     * @param happinessThreshold is new happinessThreshold
     * @author dzhen2023
     */
    public void reset(double fractionVacant,
                      double fractionRed,
                      double happinessThreshold) {
        this.happinessThreshold = happinessThreshold;
        randomizeGrid(fractionVacant,fractionRed);
    }

    /**
     * @param row is row index of grid
     * @param col is column index of grid
     * @return if the resident at specified cell is happy
     * @throws IllegalArgumentException if out of bounds
     * @author dzhen2023
     */
    public boolean isHappy(int row, int col) {
        Color currentCellColor = getColor(row,col);
        return happinessCheck(row,col,currentCellColor);
    }


    /**
     * @return the fraction of happy residents
     * @author 0r0chic0
     */
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
      frac = (double) counter / (size * size); // cast double to value
       return frac;
    }


    /**
     * effects: moves unhappy residents randomly in one time step
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
     * effects: simulates the RedBlueGrid in a non-randomized way
     * @param numSteps is the number of times the Simulation will run
     * @author dzhen2023
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
        if (row < 0 || col < 0 || row >= size || col >= size) {
            throw new IllegalArgumentException("Invalid Grid Index");
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
