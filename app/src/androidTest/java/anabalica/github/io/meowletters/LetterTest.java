package anabalica.github.io.meowletters;

import junit.framework.*;

import anabalica.github.io.meowletters.letters.Letter;

/**
 * Test Letter class
 *
 * @author Ana Balica
 */
public class LetterTest extends TestCase {
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
}
