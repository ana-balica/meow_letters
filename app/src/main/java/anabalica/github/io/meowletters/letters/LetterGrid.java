package anabalica.github.io.meowletters.letters;

/**
 * LetterGrid is a class that represents the internal structure of the game grid.
 * It keeps track of all letters and their position on the board.
 *
 * @author Ana Balica
 */
public class LetterGrid {
    private int ROWS;
    private int COLUMNS;
    private Letter[][] grid;
    private int emptyCellsCount;

    /**
     * Class constructor that accepts number of rows and columns, and initializes
     * all the class attributes accordingly.
     *
     * @param rows integer number of rows
     * @param columns integer number of columns
     */
    public LetterGrid(int rows, int columns) {
        if (rows < 0 || columns < 0) {
            throw new IllegalArgumentException("Rows and columns must be positive numbers");
        }
        ROWS = rows;
        COLUMNS = columns;
        grid = new Letter[rows][columns];
        emptyCellsCount = rows * columns;
    }

    /**
     * Class constructor that is initialized from an existent grid of letters.
     *
     * @param grid 2D matrix of Letter objects
     */
    public LetterGrid(Letter[][] grid) {
        ROWS = grid.length;
        COLUMNS = grid[0].length;
        this.grid = grid;
        emptyCellsCount = getEmptyCellsCount();
    }

    /**
     * Return the number of empty (null) cells from the current grid.
     *
     * @return integer number of empty cells
     */
    public int getEmptyCellsCount() {
        int emptyCellsCount = 0;
        for (int row = 0; row < ROWS; row++) {
            for (int column = 0; column < COLUMNS; column++) {
                if (grid[row][column] == null) {
                    emptyCellsCount++;
                }
            }
        }
        return emptyCellsCount;
    }

    /**
     * Return a random order from the total number of empty cells in the grid
     *
     * @return integer between 0 and number of empty cells
     */
    private int getRandomOrder() {
        return (int) (Math.random() * emptyCellsCount);
    }

    /**
     * Get a random empty cell (is not occupied by a letter) from the grid
     *
     * @return Cell object that holds the cell coordinates
     */
    public Cell getRandomEmptyCell() {
        if (emptyCellsCount == 0) {
            return null;
        }

        int order = getRandomOrder();
        int gridOrder = 0;
        for (int row = 0; row < ROWS; row++) {
            for (int column = 0; column < COLUMNS; column++) {
                if (grid[row][column] == null) {
                    if (gridOrder == order) {
                        return new Cell(row, column);
                    }
                    gridOrder++;
                }
            }
        }
        return null;
    }
}
