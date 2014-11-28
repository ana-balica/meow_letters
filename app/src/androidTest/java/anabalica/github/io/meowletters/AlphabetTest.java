package anabalica.github.io.meowletters;

import junit.framework.*;

import anabalica.github.io.meowletters.letters.Alphabet;

/**
 * Test Alphabet class
 *
 * @author Ana Balica
 */
public class AlphabetTest extends TestCase {
    public void testSetCurrent() {
        try {
            Alphabet.setCurrent("FOBAR");
            fail("Exception not thrown");
        } catch (IllegalArgumentException e) {
            assertEquals(e.getMessage(), "Illegal alphabet");
        }

        assertEquals(Alphabet.getCurrent(), Alphabet.ENGLISH);
        Alphabet.setCurrent(Alphabet.ROMANIAN);
        assertEquals(Alphabet.getCurrent(), Alphabet.ROMANIAN);
        Alphabet.setCurrent(Alphabet.ENGLISH);
        assertEquals(Alphabet.getCurrent(), Alphabet.ENGLISH);
    }
}
