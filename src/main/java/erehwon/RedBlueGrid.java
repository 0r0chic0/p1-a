package erehwon;

import java.awt.Color;
import  java.util.Random;

public class RedBlueGrid {
    private static final Color[] COLORS = {Color.WHITE, Color.RED, Color.BLUE};
    private Color[][] grid;

    /*
    * Constructs a n x n RedBlueGrid
    * @param size: size of constructed grid
    * @param neighborhoodDistance: steps need to reach a cell within the neighborhood
    * @param fractionVacant: percentage of vacant cells
    * @param fractionRed: percentage of non-vacant cells that are red
    * @param happinessThreshold: percentage of same color cells in the neighborhood for a cell to attain happiness
    * @author dzhen2023
    */
    public RedBlueGrid(int size,
                       int neighborhoodDistance,
                       double fractionVacant,
                       double fractionRed,
                       double happinessThreshold) {
        grid = new Color[size][size];
        int numberOfVacant = (int) (((double)(size*size)) * fractionVacant);
        int numberOfNonVacant = (size*size) - numberOfVacant;
        int numberOfRed = (int) (((double) numberOfNonVacant) * fractionRed);
        int numberOfBlue = numberOfNonVacant - numberOfRed;
        Random rng = new Random();





    }

    // do nothing for cells that are not on the grid
    public Color getColor(int row, int col) {
        // TODO: Implement this method
        return null;
    }

    // can only set a valid colour for this project
    // do nothing for cells that are not on the grid
    // this method may violate the fractional allocation
    // of space but we are still implementing it
    public boolean setColor(int row, int col, Color color) {
        // TODO: Implement this method
        return false; // you may need to change this
    }

    // for rotating through the colours in the order
    // WHITE -> RED -> BLUE -> WHITE -> ...
    public void shiftColor(int row, int col) {
        // TODO: Implement this method
    }

    // recolour the cells with the given
    // fraction of vacant cells, the fraction
    // of non-vacant cells that are red
    // (the rest are blue cells),
    // and a possibly new happiness threshold
    public void reset(double fractionVacant,
                      double fractionRed,
                      double happinessThreshold) {
        // TODO: Implement this method
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
