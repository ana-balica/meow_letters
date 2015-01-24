package anabalica.github.io.meowletters.letters;

import java.util.HashMap;

/**
 * Alphabet class stores all the alphabets that can be used in the game. It
 * contains constants with alphabet strings and the currently active alphabet.
 *
 * @author Ana Balica
 */
public class Alphabet {
    // Game alphabets
    public static final String ENGLISH = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    public static final String RUSSIAN = "АБВГДЕЁЖЗИЙКЛМНОПРСТУФХЦЧШЩЪЫЬЭЮЯ";
    public static final String UKRAINIAN = "АБВГҐДЕЄЖЗИІЇЙКЛМНОПРСТУФХЦЧШЩЬЮЯ";
    public static final String TURKISH = "ABCÇDEFGĞHIİJKLMNOÖPRSŞTUÜVYZ";
    public static final String GREEK = "ΑΒΓΔΕΖΗΘΙΚΛΜΝΞΟΠΡΣΤΥΦΧΨΩ";


    // All available alphabets
    private static final HashMap<String, String> alphabets;
    static {
        alphabets = new HashMap<>();
        alphabets.put("ENGLISH", ENGLISH);
        alphabets.put("RUSSIAN", RUSSIAN);
        alphabets.put("UKRAINIAN", UKRAINIAN);
        alphabets.put("TURKISH", TURKISH);
        alphabets.put("GREEK", GREEK);
    }

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
     * @param alphabetName String alphabet name
     */
    public static void setCurrent(String alphabetName) {
        if (!alphabets.containsKey(alphabetName)) {
            throw new IllegalArgumentException("Illegal alphabet name");
        }
        CURRENT = alphabets.get(alphabetName);
    }
}
