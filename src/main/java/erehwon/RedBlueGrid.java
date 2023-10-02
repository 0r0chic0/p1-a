package erehwon;

import java.awt.Color;
import  java.util.Random;

public class RedBlueGrid {
    private static final Color[] COLORS = {Color.WHITE, Color.RED, Color.BLUE};
    private Color[][] grid;
    private int neighborhoodDistance;
    private double happinessThreshold;
    private int size;

    /**
    * Constructs n x n RedBlueGrid
    * Vacant cells are white
    * @param size is size of constructed grid
    * @param neighborhoodDistance is steps need to reach a cell within the neighborhood
    * @param fractionVacant is percentage of vacant cells in grid
    * @param fractionRed is percentage of non-vacant cells that are red in grid
    * @param happinessThreshold: percentage of same color cells in the neighborhood for a cell to attain happiness
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

        for (int x = rng.nextInt(size), y = rng.nextInt(size); numberOfRed > 0 && numberOfBlue > 0;) {
           if (numberOfRed > 0) {
               while (!grid[y][x].equals(COLORS[0])) {
                   y = rng.nextInt(size);
                   x = rng.nextInt(size);
               }
               grid[y][x] = COLORS[1];
               numberOfRed--;
           }
           if (numberOfBlue > 0) {
               while (!grid[y][x].equals(COLORS[0])) {
                   y = rng.nextInt(size);
                   x = rng.nextInt(size);
               }
               grid[y][x] = COLORS[2];
               numberOfBlue--;
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
        // TODO: Implement this method

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
        // TODO: Implement this method
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

    // recolour the cells with the given
    // fraction of vacant cells, the fraction
    // of non-vacant cells that are red
    // (the rest are blue cells),
    // and a possibly new happiness threshold
    public void reset(double fractionVacant,
                      double fractionRed,
                      double happinessThreshold) {
        this.happinessThreshold = happinessThreshold;
        randomizeGrid(fractionVacant,fractionRed);
    }

    // is the resident at the given cell happy?
    public boolean isHappy(int row, int col) {
        // TODO: Implement this method
        return false;
    }

    // what fraction of the erehwon residents are happy?
    public double fractionHappy() {
        // TODO: Implement this method
        return -1;
    }

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
        RedBlueGrid test = new RedBlueGrid(8,1,0.15,0.5,0.3);
    }
}
