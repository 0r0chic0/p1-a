package erehwon;

import java.awt.*;
import java.util.*;
import java.util.List;

public class RedBlueGrid {
    private static final Color[] COLORS = {Color.WHITE, Color.RED, Color.BLUE};
    private Color[][] grid;
    private final int neighborhoodDistance;
    private double happinessThreshold;
    private final int size;
    private int counterRed;
    private int counterBlue;

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
     *                          requires 0 <= fractionVacant <= 1
    * @param fractionRed:   the fraction of non-vacant (not white) red cells in the grid
     *                      requires 0 <= fractionRed <= 1
    * @param happinessThreshold:    the fraction of same color cells in the neighborhood of a cell to attain happiness;
     *                              requires 0 <= happinessThreshold <= 1
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

        randomizeGrid(fractionVacant, fractionRed);
    }

    /**
     * Randomizes grid cell colors with specified inputs.
     * @param fractionVacant:   fraction of vacant (white) cells the grid is to have;
     *                          requires 0 <= fractionVacant <= 1
     * @param fractionRed:  fraction of non-vacant red cells the grid is to have;
     *                      requires 0 <= fractionRed <= 1
     * @author  dzhen2023
     */
    private void randomizeGrid(double fractionVacant, double fractionRed) {
        int numberOfVacant = (int) Math.round(((double) (size * size)) * fractionVacant);
        int numberOfNonVacant = (size * size) - numberOfVacant;
        int numberOfRed = (int) Math.round(((double) numberOfNonVacant) * fractionRed);
        int numberOfBlue = numberOfNonVacant - numberOfRed;
        Random rng = new Random();
        Map<Integer, List<Integer>> rowWithVacant = new HashMap<>();

        for (int i = 0; i < size; i++) {
            rowWithVacant.put(i, new ArrayList<>());
            for (int j = 0; j < size; j++) {
                grid[i][j] = COLORS[0];
                rowWithVacant.get(i).add(j);
            }
        }

        for (int i = 0; i < size; i++) {
            Collections.shuffle(rowWithVacant.get(i));
        }

        counterBlue = 0;
        counterRed = 0;

        for (int x, y, i = rng.nextInt(size); numberOfRed > 0 || numberOfBlue > 0; i = rng.nextInt(size)) {
            for (int j = 0; j < size; j++) {
                if (numberOfRed > 0 && !rowWithVacant.get(i).isEmpty()) {
                    x = i;
                    y = rowWithVacant.get(i).get(0);
                    grid[x][y] = COLORS[1];
                    numberOfRed--;
                    rowWithVacant.get(i).remove(0);
                    counterRed++;
                }
                if (numberOfBlue > 0 && !rowWithVacant.get(i).isEmpty()) {
                    x = i;
                    y = rowWithVacant.get(i).get(0);
                    grid[y][x] = COLORS[2];
                    rowWithVacant.get(i).remove(0);
                    numberOfBlue--;
                    counterBlue++;
                }
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
        if (!withinBounds(row, col)) {
            throw new IllegalArgumentException("Out of Bounds");
        } else {
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
        if (!withinBounds(row, col)) {
            return false;
        }

        boolean isValidColor = false;

        for (Color validColor: COLORS) {
            if (color.equals(validColor)) {
                isValidColor = true;
                break;
            }
        }
        if (!isValidColor) {
            return false;
        }

        grid[row][col] = color;

        return true;
    }



    /**
     * Rotates a cell's color by this order: WHITE -> RED -> BLUE -> WHITE.
     * @param row:  row index of grid
     * @param col:  column index of grid
     * @throws IllegalArgumentException when row and column index are out of bounds
     * @author  kevinlin1029
     */
    public void shiftColor(int row, int col) {
        if (!withinBounds(row, col)) {
            throw new IllegalArgumentException("Out of Bounds");
        }

        Color currentColor = grid[row][col];

        // find the next color
        if (currentColor.equals(COLORS[0])) {
            grid[row][col] = COLORS[1];
        } else if (currentColor.equals(COLORS[1])) {
            grid[row][col] = COLORS[2];
        } else if (currentColor.equals(COLORS[2])) {
            grid[row][col] = COLORS[0];
        }
    }

    /**
     * Randomize grid colors and cell happiness threshold with desired inputs.
     * @param fractionVacant:   fraction of vacant (white) cells for grid to have;
     *                          requires 0 <= fractionVacant <= 1
     * @param fractionRed:  fraction of red cells for grid to have;
     *                      requires 0 <= fractionRed <= 1
     * @param happinessThreshold:   new happinessThreshold;
     *                              requires 0 <= happinessThreshold <= 1
     * @author  dzhen2023
     */
    public void reset(double fractionVacant,
                      double fractionRed,
                      double happinessThreshold) {
        this.happinessThreshold = happinessThreshold;
        randomizeGrid(fractionVacant, fractionRed);
    }

    /**
     * @param row: row index of grid
     * @param col: column index of grid
     * @return  if resident at specified cell is happy
     * @throws  IllegalArgumentException if specified index is out of bounds
     * @author  dzhen2023
     */
    public boolean isHappy(int row, int col) {
        Color colorAtCell = getColor(row, col);
        return happinessCheck(row, col, colorAtCell);
    }


    /**
     * @return  fraction of happy residents
     * @author  0r0chic0
     */
    public double fractionHappy() {
        int happyCounter = 0;

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (isHappy(i, j)) {
                    happyCounter++;
                }
            }
        }

        return (double) happyCounter / (size * size);
    }


    /**
     * Moves unhappy residents to vacant (white) cells randomly in one time step.
     * @author kevinlin1029
     */
    public void oneTimeStep() {

        List<Point> unhappyPeople = new ArrayList<>();
        List<Point> vacant = new ArrayList<>();

        initializeCellLists(unhappyPeople, vacant);


        Collections.shuffle(unhappyPeople);
        int mini = Math.min(vacant.size(), unhappyPeople.size());

        for (int i = 0; i < mini; i++) {
            Point origin = unhappyPeople.get(i);
            Point empty = vacant.get(i);

            grid[empty.x][empty.y] = grid[origin.x][origin.y];
            grid[origin.x][origin.y] = COLORS[0];
        }
    }


    /**
     * Simulates the RedBlueGrid in a non-randomized way
     * @param numSteps:     number of time steps the simulation will run for
     * @author  dzhen2023
     */
    public void simulate(int numSteps) {
        for (int k = 0; k < numSteps; k++) {
            List<Point> unhappyCells = new ArrayList<>();
            List<Point> vacantCells = new ArrayList<>();

            initializeCellLists(unhappyCells, vacantCells);

            for (int i = 0; i < vacantCells.size(); i++) {
                int vacantX = vacantCells.get(i).x;
                int vacantY = vacantCells.get(i).y;

                for (int j = 0; j < unhappyCells.size(); j++) {
                    int unhappyX = unhappyCells.get(j).x;
                    int unhappyY = unhappyCells.get(j).y;

                    if (happinessCheck(vacantX, vacantY, grid[unhappyX][unhappyY])) {
                        grid[vacantX][vacantY] = grid[unhappyX][unhappyY];
                        grid[unhappyX][unhappyY] = COLORS[0];
                        vacantCells.remove(i);
                        unhappyCells.remove(j);

                        break;
                    }
                }
            }

            Map<Point, Double> redFractions = new HashMap<>();
            Map<Point, Double> blueFractions = new HashMap<>();

            for (Point vacantCell : vacantCells) {
                redFractions.put(vacantCell, getFractionOfSameColor(vacantCell.x, vacantCell.y, COLORS[1]));
                blueFractions.put(vacantCell, getFractionOfSameColor(vacantCell.x, vacantCell.y, COLORS[2]));
            }

            for (Point unhappyCell: unhappyCells) {
                if (getColor(unhappyCell.x, unhappyCell.y).equals(COLORS[1]) && !redFractions.isEmpty()) {
                    moveCloseToHappiness(redFractions, blueFractions, vacantCells, unhappyCell);
                } else if (getColor(unhappyCell.x, unhappyCell.y).equals(COLORS[2]) && !blueFractions.isEmpty()) {
                    moveCloseToHappiness(blueFractions, redFractions, vacantCells, unhappyCell);
                }

                if (vacantCells.isEmpty()) {
                    break;
                }
            }


            redFractions.clear();
            blueFractions.clear();
            vacantCells.clear();
            unhappyCells.clear();
        }
    }

    /**
     * Initialize lists that keep track of vacant cells and unhappy cells.
     * @param unhappyCells: list of unhappy cells to initialize
     *                      requires that unhappyCells is empty
     * @param vacantCells:  list of vacant cells to initialize
     *                      requires that vacantCells is empty
     */
    private void initializeCellLists(List<Point> unhappyCells, List<Point> vacantCells) {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (grid[i][j].equals(COLORS[0])) {
                    vacantCells.add(new Point(i, j));
                } else if (!isHappy(i, j)) {
                    unhappyCells.add(new Point(i, j));
                }
            }
        }
    }

    /**
     * Moves an unhappy cell to a cell closer to more of its kind.
     * @param colorFractions1:  list of fractions of same color cells in the neighborhood a vacant cell
     *                          requires that colorFractions1 is not empty
     * @param colorFractions2:  list of fractions of different color cells in the neighborhood a vacant cell
     *                          requires that colorFractions2 is not empty
     * @param vacantCells:  list of vacant cells
     *                      requires that vacantCells is not empty
     * @param unhappyCell:  position of unhappyCell on the grid
     *                      requires that unhappyCell exists on the grid
     * @author  dzhen2023
     */
    private void moveCloseToHappiness(Map<Point, Double> colorFractions1, Map<Point, Double> colorFractions2,
                                      List<Point> vacantCells, Point unhappyCell) {
        double maxFraction = colorFractions1.get(vacantCells.get(0));
        int maxIndex = 0;

        for (int i = 1; i < vacantCells.size(); i++) {
            if (maxFraction <= colorFractions1.get(vacantCells.get(i))) {
                maxFraction = colorFractions1.get(vacantCells.get(i));
                maxIndex = i;
            }
        }

        grid[vacantCells.get(maxIndex).x][vacantCells.get(maxIndex).y] = getColor(unhappyCell.x, unhappyCell.y);
        grid[unhappyCell.x][unhappyCell.y] = COLORS[0];

        colorFractions1.remove(vacantCells.get(maxIndex));
        colorFractions2.remove(vacantCells.get(maxIndex));
        vacantCells.remove(maxIndex);
    }


    /**
     * Checks specified cell if a colored cell would be happy there
     * @param row   row index of grid
     * @param col   column index of grid
     * @param colorToCheck  color to check happiness for
     * @throws IllegalArgumentException when grid index is out of range
     * @return if the color would be happy at specified row and col
     * @author dzhen2023
     */
    private boolean happinessCheck(int row, int col, Color colorToCheck) {
        return getFractionOfSameColor(row, col, colorToCheck) >= happinessThreshold;
    }

    /**
     * Gets fraction of same color cells in neighborhood
     * @param row:  row index of grid
     * @param col:  column index of grid
     * @param colorToCheck:    color to check neighborhood for
     * @throws IllegalArgumentException when grid index is out of range
     * @return  the fraction of same color cells in the neighborhood of specified
     * @author  dzhen2023
     */
    private double getFractionOfSameColor(int row, int col, Color colorToCheck) {
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
            if (!withinBounds(row, col + i)) {
                break;
            }

            int[] counts = verticalCount(row, col + i, bottomBound, topBound, colorToCheck);
            cellCount += counts[0];
            sameCount += counts[1];

            if (i != 0) {
                cellCount++;
                if (getColor(row, col + i).equals(colorToCheck)) {
                    sameCount++;
                }
            }
        }

        for (int i = -1; i >= leftBound; i--) {
            if (!withinBounds(row, col + i)) {
                break;
            }

            int[] counts = verticalCount(row, col + i, bottomBound, topBound, colorToCheck);
            cellCount += counts[0];
            sameCount += counts[1];

            cellCount++;
            if (getColor(row, col + i).equals(colorToCheck)) {
                sameCount++;
            }
        }

        return (double) sameCount / (double) cellCount;
    }

    /**
     * Counts total cells and cells of a same color (as origin cell) a column of bound heights
     * @param row:  specified starting row index
     * @param col:  specified column index
     * @param bottomBound   bottom boundary of ideal neighborhood
     * @param topBound:     top boundary of ideal neighborhood
     * @param colorToCheck:  color of cell at center of neighborhood
     * @return  array that contains the number of cells and same color cells in the bounded column
     * @author  dzhen2023
     */
    private int[] verticalCount(int row, int col, int bottomBound, int topBound, Color colorToCheck) {
        int cellCount = 0;
        int sameCount = 0;

        for (int j = -1; j >= topBound; j--) {
            if (!withinBounds(row + j, col)) {
                break;
            }
            cellCount++;
            if (getColor(row + j, col).equals(colorToCheck)) {
                sameCount++;
            }
        }

        for (int j = 1; j <= bottomBound; j++) {
            if (!withinBounds(row + j, col)) {
                break;
            }
            cellCount++;
            if (getColor(row + j, col).equals(colorToCheck)) {
                sameCount++;
            }
        }

        return new int[] {cellCount, sameCount, topBound, bottomBound};
    }

    /**
     * @param row: row index of grid
     * @param col: column index of grid
     * @return if row or column index is out of bounds
     */
    private boolean withinBounds(int row, int col) {
        return !(row < 0 || row >= size || col < 0 || col >= size);
    }

    /**
     * Gets number of red cells in the grid.
     * @return count of red cells after a randomization
     */
    public int getNumOfRed() {
        return counterRed;
    }

    /**
     * Gets number of blue cells in the grid.
     * @return count of blue cells after a randomization
     */
    public int getNumOfBlue() {
        return counterBlue;
    }

    /**
     * Gets the number of rows/columns in the grid.
     * @return size of grid
     */
    public int getSize() {
        return size;
    }

    /**
     * Gets the happinessThreshold of the grid.
     * @return happinessThreshold of the grid
     */
    public double getHappinessThreshold() {
        return happinessThreshold;
    }
}