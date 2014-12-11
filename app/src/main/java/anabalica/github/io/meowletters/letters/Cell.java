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
        setRow(row);
        setColumn(column);
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }

    public void setRow(int row) throws IllegalArgumentException {
        if (row < 0) {
            throw new IllegalArgumentException("Row can not be negative.");
        }
        this.row = row;
    }

    public void setColumn(int column) throws IllegalArgumentException {
        if (column < 0) {
            throw new IllegalArgumentException("Column can not be negative.");
        }
        this.column = column;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Cell cell = (Cell) o;

        if (column != cell.column) return false;
        if (row != cell.row) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = row;
        result = 31 * result + column;
        return result;
    }
}
