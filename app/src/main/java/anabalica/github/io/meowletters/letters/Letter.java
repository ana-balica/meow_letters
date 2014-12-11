package anabalica.github.io.meowletters.letters;

import java.util.Random;

/**
 * Representation of a letter from game grid
 *
 * @author Ana Balica
 */
public class Letter implements Comparable {
    private String letter;
    private boolean selected;
    private Cell position;

    public Letter(String letter) {
        setLetter(letter);
        selected = false;
        position = null;
    }

    public Letter(String letter, int row, int column) {
        setLetter(letter);
        selected = false;
        position = new Cell(row, column);
    }

    public String getLetter() {
        return letter;
    }

    /**
     * Set letter string of the Letter. Must be one-char string from the
     * current alphabet.
     *
     * @param letter string letter
     * @throws IllegalArgumentException if letter param is not from the current
     * alphabet or isn't one char long
     */
    public void setLetter(String letter) throws IllegalArgumentException {
        String upperCaseLetter = letter.toUpperCase();
        if (!Alphabet.getCurrent().contains(upperCaseLetter)) {
            throw new IllegalArgumentException("Letter missing from the alphabet.");
        }
        if (letter.length() != 1) {
            throw new IllegalArgumentException("Letter must be exactly 1 char long.");
        }
        this.letter = upperCaseLetter;
    }

    public boolean isSelected() {
        return selected;
    }

    public void select() {
        selected = true;
    }

    public void unselect() {
        selected = false;
    }

    public Cell getPosition() {
        return position;
    }

    public void setPosition(Cell position) {
        this.position = position;
    }

    public void setPosition(int row, int column) {
        this.position = new Cell(row, column);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Letter letter1 = (Letter) o;

        if (letter != null ? !letter.equals(letter1.letter) : letter1.letter != null) return false;
        if (position != null ? !position.equals(letter1.position) : letter1.position != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = letter != null ? letter.hashCode() : 0;
        result = 31 * result + (position != null ? position.hashCode() : 0);
        return result;
    }

    @Override
    public int compareTo(Object object) {
        Letter letter = (Letter) object;
        return this.letter.compareTo(letter.getLetter());
    }

    /**
     * Check if letters are equal according to the their inner letter strings. This method is
     * different from the equals(), since it takes into consideration only letter attribute.
     *
     * @param o Letter object
     * @return true if letters are equal, false otherwise
     */
    public boolean equalsLetter(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || Letter.class != o.getClass()) {
            return false;
        }

        Letter letter1 = (Letter) o;
        if (letter != null ? !letter.equals(letter1.letter) : letter1.letter != null) {
            return false;
        }
        return true;
    }

    /**
     * Get the next consecutive Letter from the alphabet.
     *
     * @return next letter or null if case there is no next letter
     */
    public Letter next() {
        String alphabet = Alphabet.getCurrent();
        if (alphabet.endsWith(letter)) {
            return null;
        }
        int current_index = alphabet.indexOf(letter);
        String next_char = Character.toString(alphabet.charAt(++current_index));
        return new Letter(next_char);
    }

    /**
     * Get the previous Letter from the alphabet.
     *
     * @return previous letter or null in case there is no previous letter
     */
    public Letter previous() {
        String alphabet = Alphabet.getCurrent();
        if (alphabet.startsWith(letter)) {
            return null;
        }
        int current_index = alphabet.indexOf(letter);
        String previous_char = Character.toString(alphabet.charAt(--current_index));
        return new Letter(previous_char);
    }

    /**
     * Get a random letter from the currently active alphabet.
     *
     * @return Letter object
     */
    public static Letter getRandomLetter() {
        String alphabet = Alphabet.getCurrent();
        int randomIndex = new Random().nextInt(alphabet.length());
        String letter = Character.toString(alphabet.charAt(randomIndex));
        return new Letter(letter);
    }
}
