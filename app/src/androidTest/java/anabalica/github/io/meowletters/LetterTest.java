package anabalica.github.io.meowletters;

import junit.framework.*;

import anabalica.github.io.meowletters.letters.Letter;

/**
 * Test Letter class
 *
 * @author Ana Balica
 */
public class LetterTest extends TestCase {
    public void testEquals() {
        // Test Letter equality with letter set
        Letter letterP = new Letter("P");
        Letter letterO = new Letter("O");
        Letter otherLetterP = new Letter("P");
        assertFalse(letterP.equals(letterO));
        assertTrue(letterP.equals(otherLetterP));

        // Test Letter equality with letter and position set
        Letter letterZ = new Letter("Z", 1, 2);
        Letter otherLetterZ = new Letter("Z", 2, 1);
        Letter sameLetterZ = new Letter("Z", 1, 2);
        assertFalse(letterZ.equals(otherLetterZ));
        assertTrue(letterZ.equals(sameLetterZ));
    }

    public void testEqualsLetter() {
        Letter letterP = new Letter("P", 1, 2);
        Letter otherLetterP = new Letter("P", 3, 4);
        Letter letterQ = new Letter("Q");
        assertFalse(letterP.equalsLetter(letterQ));
        assertTrue(letterP.equalsLetter(otherLetterP));
    }

    public void testSetLetter() {
        // Test invalid letter
        try {
            Letter letter = new Letter("Ф");
            fail("Exception not thrown");
        } catch (IllegalArgumentException e) {
            assertEquals(e.getMessage(), "Letter missing from the alphabet.");
        }

        // Test empty string
        try {
            Letter letter = new Letter("");
            fail("Exception not thrown");
        } catch (IllegalArgumentException e) {
            assertEquals(e.getMessage(), "Letter must be exactly 1 char long.");
        }

        // Test longer string
        try {
            Letter letter = new Letter("EF");
            fail("Exception not thrown");
        } catch (IllegalArgumentException e) {
            assertEquals(e.getMessage(), "Letter must be exactly 1 char long.");
        }

        // Test lowercase valid string
        Letter letter = new Letter("b");
        assertEquals(letter.getLetter(), "B");

        // Test uppercase valid string
        letter.setLetter("Z");
        assertEquals(letter.getLetter(), "Z");
    }

    public void testNext() {
        Letter letterT = new Letter("T");
        assertEquals(letterT.next().getLetter(), "U");

        Letter letterZ = new Letter("Z");
        assertNull(letterZ.next());
    }

    public void testPrevious() {
        Letter letterN = new Letter("N");
        assertEquals(letterN.previous().getLetter(), "M");

        Letter letterA = new Letter("A");
        assertNull(letterA.previous());
    }

    public void testAdjacent() {
        Letter letterT = new Letter("T");
        assertEquals(letterT.adjacent().getLetter(), "U");

        Letter letterZ = new Letter("Z");
        assertEquals(letterZ.adjacent().getLetter(), "Y");
    }
}
