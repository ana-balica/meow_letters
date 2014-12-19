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

    public Letter[][] getGrid() {
        return grid;
    }

    public int getROWS() {
        return ROWS;
    }

    public int getCOLUMNS() {
        return COLUMNS;
    }

    /**
     * Get letter at specified position
     *
     * @param row integer grid row
     * @param column integer grid column
     * @return Letter object or null
     * @throws IllegalArgumentException if either row or column are less than 0
     *                                  or bigger than the grid size
     */
    public Letter getLetter(int row, int column) throws IllegalArgumentException {
        // check if row and column valid
        if (row < 0 || row > ROWS) {
            throw new IllegalArgumentException("Invalid row.");
        }
        if (column < 0 || column > COLUMNS) {
            throw new IllegalArgumentException("Invalid column.");
        }
        return grid[row][column];
    }

    /**
     * Get the number of Letter elements from the grid.
     *
     * @return integer number of Letters
     */
    public int lettersCount() {
        int size = ROWS * COLUMNS;
        return size - emptyCellsCount;
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

    /**
     * Get a random order from the total number of occupied cells in the grid
     *
     * @return integer between 0 and number of occupied cells
     */
    private int getRandomOccupiedOrder() {
        int size = ROWS * COLUMNS;
        return (int) (Math.random() * (size - emptyCellsCount));
    }

    /**
     * Get a random letter from the grid. If grid has no letters will return null.
     *
     * @return Letter object
     */
    public Letter getRandomLetter() {
        int order = getRandomOccupiedOrder();
        int gridOrder = 0;
        for (int row = 0; row < ROWS; row++) {
            for (int column = 0; column < COLUMNS; column++) {
                if (grid[row][column] != null) {
                    if (gridOrder == order) {
                        return grid[row][column];
                    }
                    gridOrder++;
                }
            }
        }
        return null;
    }

    /**
     * Check if the grid contains a specific letter
     *
     * @param letter Letter object
     * @return true if grid has the letter, false otherwise
     */
    public boolean contains(Letter letter) {
        for (int row = 0; row < ROWS; row++) {
            for (int column = 0; column < COLUMNS; column++) {
                if (letter.equalsLetter(grid[row][column])) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Add a letter chain to the grid, each element placed in some random cell
     * on the grid.
     *
     * @param letterChain LetterChain object
     */
    public void addLetterChain(LetterChain letterChain) {
        for (Letter letter : letterChain) {
            Cell cell = getRandomEmptyCell();
            if (cell != null) {
                int row = cell.getRow();
                int column = cell.getColumn();
                letter.setPosition(cell);
                grid[row][column] = letter;
                emptyCellsCount--;
            }
        }
    }

    /**
     * Remove letters of a letter chain from a grid.
     *
     * @param letterChain LetterChain object
     */
    public void removeLetterChain(LetterChain letterChain) {
        for (Letter letter : letterChain) {
            int row = letter.getPosition().getRow();
            int column = letter.getPosition().getColumn();
            grid[row][column] = null;
            emptyCellsCount++;
        }
    }
}
