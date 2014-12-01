package anabalica.github.io.meowletters.letters;

/**
 * Represents a cell of a grid found out by its coordinates: row and column.
 *
 * @author Ana Balica
 */
public class Cell {
    private int row;
    private int column;

    public Cell(int row, int column) {
        this.row = row;
        this.column = column;
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }
}
