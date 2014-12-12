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

    public void testGetLetter() {
        Letter grid[][] = {
                {null, new Letter("F"), null},
                {null, null, null},
                {new Letter("W"), null, null}
        };
        LetterGrid letterGrid = new LetterGrid(grid);
        // Test invalid values
        try {
            letterGrid.getLetter(4, 1);
            fail("Exception not thrown");
        } catch (IllegalArgumentException e) {
            assertEquals(e.getMessage(), "Invalid row.");
        }
        try {
            letterGrid.getLetter(-1, 1);
            fail("Exception not thrown");
        } catch (IllegalArgumentException e) {
            assertEquals(e.getMessage(), "Invalid row.");
        }
        try {
            letterGrid.getLetter(2, 5);
            fail("Exception not thrown");
        } catch (IllegalArgumentException e) {
            assertEquals(e.getMessage(), "Invalid column.");
        }
        try {
            letterGrid.getLetter(2, -1);
            fail("Exception not thrown");
        } catch (IllegalArgumentException e) {
            assertEquals(e.getMessage(), "Invalid column.");
        }

        // Test getting a Letter
        Letter letterF = letterGrid.getLetter(0, 1);
        assertEquals(letterF, new Letter("F"));
        // Test getting null
        Letter letterNull = letterGrid.getLetter(1, 1);
        assertNull(letterNull);
    }

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
        letterGrid.getGrid()[3][1] = letterW;
        assertTrue(letterGrid.contains(new Letter("W")));
        assertFalse(letterGrid.contains(new Letter("B")));
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

    public void testRemoveLetterChain() {
        Letter letterA = new Letter("A", 0, 0);
        Letter extraLetterA = new Letter("A", 2, 2);
        Letter letterY = new Letter("Y", 1, 2);

        Letter grid[][] = {
                {letterA, null, null},
                {null, null, letterY},
                {null, null, extraLetterA}
        };
        LetterGrid letterGrid = new LetterGrid(grid);
        LetterChain letterChain = new LetterChain();
        letterChain.add(letterA);
        letterChain.add(letterY);
        letterGrid.removeLetterChain(letterChain);

        assertTrue(letterGrid.getLetter(0, 0) == null);
        assertTrue(letterGrid.getLetter(2, 2).equals(extraLetterA));
        assertTrue(letterGrid.getLetter(1, 2) == null);
    }
}
