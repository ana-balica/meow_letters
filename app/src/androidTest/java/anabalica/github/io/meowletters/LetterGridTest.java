package anabalica.github.io.meowletters;

import junit.framework.*;

import anabalica.github.io.meowletters.letters.Cell;
import anabalica.github.io.meowletters.letters.Letter;
import anabalica.github.io.meowletters.letters.LetterChain;
import anabalica.github.io.meowletters.letters.LetterGrid;

/**
 * Test LetterGrid class
 *
 * @author Ana Balica
 */
public class LetterGridTest extends TestCase {

    public void testGetEmptyCellsCount() {
        LetterGrid grid = new LetterGrid(5, 6);
        assertEquals(grid.getEmptyCellsCount(), 30);
        Letter bigGrid[][] = {
                {null, new Letter("F"), null},
                {null, null, null},
                {new Letter("W"), null, null}
        };
        LetterGrid letterGrid = new LetterGrid(bigGrid);
        assertEquals(letterGrid.getEmptyCellsCount(), 7);
    }

    public void testGetRandomEmptyCell() {
        LetterGrid letterGrid = new LetterGrid(1, 1);
        Cell cell = letterGrid.getRandomEmptyCell();
        assertEquals(cell.getRow(), 0);
        assertEquals(cell.getColumn(), 0);

        Letter grid[][] = {{new Letter("A")}};
        LetterGrid newLetterGrid = new LetterGrid(grid);
        Cell nullCell = newLetterGrid.getRandomEmptyCell();
        assertNull(nullCell);

        Letter bigGrid[][] = {
                {null, new Letter("F"), null},
                {null, null, null},
                {new Letter("W"), null, null}
        };
        LetterGrid bigLetterGrid = new LetterGrid(bigGrid);

        for (int i = 0; i < 18; i++) {
            Cell someCell = bigLetterGrid.getRandomEmptyCell();
            assertFalse(someCell.getRow() == 0 && someCell.getColumn() == 1);
            assertFalse(someCell.getRow() == 2 && someCell.getColumn() == 0);
        }
    }

    public void testContains() {
        LetterGrid letterGrid = new LetterGrid(5, 5);
        Letter letterW = new Letter("W");
        Letter letterB = new Letter("B");
        letterGrid.getGrid()[3][1] = letterW;
        assertTrue(letterGrid.contains(letterW));
        assertFalse(letterGrid.contains(letterB));
    }

    public void testAddLetterChain() {
        LetterChain letterChain = new LetterChain();
        Letter letterA = new Letter("A");
        Letter letterG = new Letter("G");
        Letter letterQ = new Letter("Q");
        letterChain.add(letterA);
        letterChain.add(letterG);
        letterChain.add(letterQ);
        LetterGrid letterGrid = new LetterGrid(3, 3);
        letterGrid.addLetterChain(letterChain);

        assertTrue(letterGrid.contains(letterA));
        assertTrue(letterGrid.contains(letterG));
        assertTrue(letterGrid.contains(letterQ));
        assertEquals(letterGrid.getEmptyCellsCount(), 6);
    }
}
