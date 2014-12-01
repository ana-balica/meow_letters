package anabalica.github.io.meowletters;

import junit.framework.*;

import anabalica.github.io.meowletters.letters.Letter;
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
}
