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

    public void testAdd() {
        // Test adding one element to the chain
        letterChain.add(new Letter("A"));
        assertFalse(letterChain.isEmpty());
        assertEquals(letterChain.size(), 1);
        assertEquals(letterChain.get(0), new Letter("A"));

        // Test adding another element
        letterChain.add(new Letter("B"));
        assertEquals(letterChain.size(), 2);
        assertEquals(letterChain.get(1), new Letter("B"));
    }

    public void testRemove() {
        // Test removing from empty chain
        try {
            letterChain.remove(new Letter("P"));
            fail("Exception not thrown");
        } catch (IllegalArgumentException e) {
            assertEquals(e.getMessage(), "Letter not in chain.");
        }

        letterChain.add(new Letter("T"));
        letterChain.add(new Letter("Q"));
        letterChain.add(new Letter("W"));

        // Test removing non existing element
        try {
            letterChain.remove(new Letter("P"));
            fail("Exception not thrown");
        } catch (IllegalArgumentException e) {
            assertEquals(e.getMessage(), "Letter not in chain.");
        }

        // Test removing one element
        letterChain.remove(new Letter("W"));
        assertEquals(letterChain.size(), 2);
        assertEquals(letterChain.get(0), new Letter("T"));
        assertEquals(letterChain.get(1), new Letter("Q"));

        // Test removing middle element
        letterChain.add(new Letter("C"));
        letterChain.add(new Letter("Z"));
        letterChain.add(new Letter("Y"));
        letterChain.remove(new Letter("Z"));
        assertEquals(letterChain.size(), 3);
        assertEquals(letterChain.get(0), new Letter("T"));
        assertEquals(letterChain.get(1), new Letter("Q"));
        assertEquals(letterChain.get(2), new Letter("C"));

        // Test removing the first element
        letterChain.remove(new Letter("T"));
        assertTrue(letterChain.isEmpty());
    }

    public void testSort() {
        LetterChain letterChain = new LetterChain();
        letterChain.sort();
        Letter letterZ = new Letter("Z");
        Letter letterG = new Letter("G");
        Letter letterF = new Letter("F");
        letterChain.add(letterZ);
        letterChain.add(letterG);
        letterChain.add(letterF);
        letterChain.sort();

        assertEquals(letterChain.get(0), letterF);
        assertEquals(letterChain.get(1), letterG);
        assertEquals(letterChain.get(2), letterZ);
    }
}
