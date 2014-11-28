package anabalica.github.io.meowletters;

import junit.framework.*;

import java.util.ArrayList;

import anabalica.github.io.meowletters.letters.Letter;
import anabalica.github.io.meowletters.letters.LetterChain;

/**
 * Test LetterChain class
 *
 * @author Ana Balica
 */
public class LetterChainTest extends TestCase {
    protected LetterChain letterChain = new LetterChain();
    protected ArrayList<Letter> chain = new ArrayList<Letter>();

    public void testIsValid() {
        // Test empty chain
        assertTrue(letterChain.isValid());

        // Test chain with a single element
        chain.add(new Letter("K"));
        letterChain.setChain(chain);
        assertTrue(letterChain.isValid());

        // Test a very long chain
        chain.clear();
        String longChain = "QWFPGJLUYARSTDHNEZXCVBKMWQFPGJLRSTDHNE";
        for (int i = 0; i < longChain.length(); i++) {
            chain.add(new Letter(Character.toString(longChain.charAt(i))));
        }
        assertFalse(letterChain.isValid());

        // Test two consecutive elements
        chain.clear();
        chain.add(new Letter("G"));
        chain.add(new Letter("H"));
        assertTrue(letterChain.isValid());

        // Test three non consecutive elements
        chain.add(new Letter("B"));
        assertFalse(letterChain.isValid());

        // Test circular chain elements
        chain.clear();
        chain.add(new Letter("Z"));
        chain.add(new Letter("A"));
        assertFalse(letterChain.isValid());
    }
}
