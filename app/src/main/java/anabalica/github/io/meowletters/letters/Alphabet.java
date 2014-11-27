package anabalica.github.io.meowletters.letters;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Alphabet class stores all the alphabets that can be used in the game. It
 * contains constants with alphabet strings and the currently active alphabet.
 *
 * @author Ana Balica
 */
public class Alphabet {
    // Game alphabets
    public static final String ENGLISH = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    public static final String ROMANIAN = "AĂÂBCDEFGHIÎJKLMNOPQRSŞTŢUVWXYZ";

    // All available alphabets
    private static ArrayList<String> alphabets = new ArrayList<String>(
            Arrays.asList(ENGLISH, ROMANIAN));

    // Currently active/enabled alphabet
    private static String CURRENT = ENGLISH;


    /**
     * Get currently active/enabled alphabet
     *
     * @return alphabet
     */
    public static String getCurrent() {
        return CURRENT;
    }

    /**
     * Set the currently active alphabet. There can be only one alphabet in use.
     * If you want to add a new alphabet to the game, don't forget to add it to
     * the Alphabet.alphabets list.
     *
     * @param alphabet valid language alphabet
     */
    public static void setCurrent(String alphabet) {
        if (!alphabets.contains(alphabet)) {
            throw new IllegalArgumentException("Illegal alphabet");
        }
        CURRENT = alphabet;
    }
}
