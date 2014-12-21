package anabalica.github.io.meowletters.letters;

import android.util.Log;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

/**
 * LetterChain class is a container for manipulating a sequence of Letter
 * objects. In the game it represents the chain of selected letters by the user.
 * Order matters. There can be valid chains (D -> E -> F -> G) and invalid
 * chains (B -> C -> R).
 *
 * @author Ana Balica
 */
public class LetterChain implements Iterable<Letter> {
    private ArrayList<Letter> chain;

    public LetterChain() {
        chain = new ArrayList<Letter>();
    }

    public LetterChain(ArrayList<Letter> chain) {
        setChain(chain);
    }

    public ArrayList<Letter> getChain() {
        return chain;
    }

    public Iterator<Letter> iterator() {
        Iterator<Letter> letter = chain.iterator();
        return letter;
    }

    public void setChain(ArrayList<Letter> chain) {
        this.chain = chain;
    }

    /**
     * Check if the chain is empty
     *
     * @return true if is empty, otherwise false
     */
    public boolean isEmpty() {
        return chain.isEmpty();
    }

    /**
     * Get the size of the chain
     *
     * @return chain size
     */
    public int size() {
        return chain.size();
    }

    /**
     * Get the i-th element from the chain
     *
     * @param i index of the element
     * @return Letter object
     */
    public Letter get(int i) {
        return chain.get(i);
    }

    /**
     * Concatenate two LetterChain(s) into one.
     *
     * @param letterChain LetterChain object
     * @return concatenated LetterChain object
     */
    public LetterChain concat(LetterChain letterChain) {
        chain.addAll(letterChain.getChain());
        return this;
    }

    /**
     * Clear letter chain contents. Now the letter chain should be empty.
     */
    public void clear() {
        chain.clear();
    }

    /**
     * Check if the chain has only consecutive Letter(s) according to the
     * current alphabet. Special case: empty chain and one-letter chain
     * are considered not valid.
     *
     * @return true if has only consecutive Letters(s), false otherwise
     */
    public boolean isValid() {
        int chainSize = chain.size();
        if (chainSize < 2) {
            return false;
        }

        String currentAlphabet = Alphabet.getCurrent();
        if (chainSize >= currentAlphabet.length()) {
            return false;
        }

        StringBuilder letterChain = new StringBuilder();
        for (Letter letter : chain) {
            letterChain.append(letter.getLetter());
        }
        String finalLetterChain = letterChain.toString();
        return currentAlphabet.contains(finalLetterChain);
    }

    /**
     * Check if letter chain is final within a letter grid, i.e. there are no
     * other letters on the grid that can be appended to the chain and keep it
     * valid.
     *
     * @param letterGrid LetterGrid object
     * @return true if the chain is final, false otherwise
     */
    public boolean isFinal(LetterGrid letterGrid) {
        if (isEmpty()) {
            return false;
        }

        Letter lastLetter = get(size()-1);
        Letter nextLetter = lastLetter.next();
        if (nextLetter == null) {
            return true;
        } else {
            return !letterGrid.contains(nextLetter);
        }
    }

    /**
     * Add a new letter to the chain
     *
     * @param letter Letter object
     */
    public void add(Letter letter) {
        chain.add(letter);
    }

    /**
     * Add to the beginning of the chain
     *
     * @param letter Letter object
     */
    public void prepend(Letter letter) {
        chain.add(0, letter);
    }

    /**
     * Remove letter from the chain. All following letters are also removed.
     * The following method assumes that there are no duplicates in the chain
     * (2 or more Letter objects with the same string letter).
     *
     * @param letter Letter object
     * @return LetterChain of removed objects
     * @throws IllegalArgumentException if letter is not in the chain
     */
    public LetterChain remove(Letter letter) throws IllegalArgumentException {
        if (!chain.contains(letter)) {
            throw new IllegalArgumentException("Letter not in chain.");
        }
        int letter_index = chain.indexOf(letter);
        int last_index = chain.size() - 1;
        LetterChain removedLetters = new LetterChain();
        for (int i = last_index; i >= letter_index; i--) {
            removedLetters.add(chain.get(i));
            chain.remove(i);
        }
        return removedLetters;
    }

    /**
     * Remove letter from the chain. The letter is being pulled out. All the other letters stay.
     *
     * @param letter Letter object
     * @throws IllegalArgumentException if letter is not in the chain
     */
    public void erase(Letter letter) throws IllegalArgumentException {
        if (!chain.contains(letter)) {
            throw new IllegalArgumentException("Letter not in chain.");
        }
        int letterIndex = chain.indexOf(letter);
        chain.remove(letterIndex);
    }

    /**
     * Wrapper for sorting the chain in ascending order.
     */
    public void sort() {
        Collections.sort(chain);
    }

    /**
     * Get adjacent letter of the letter chain.
     *
     * @return Letter object
     */
    public Letter adjacent() {
        if (isEmpty()) {
            throw new IllegalArgumentException("Letter chain must not be empty.");
        }
        sort();
        int size = size();
        boolean next = (Math.random() < 0.5);

        Letter firstLetter = chain.get(0);
        Letter lastLetter = chain.get(size - 1);
        if (next) {
            Letter nextLetter = lastLetter.next();
            if (nextLetter == null) {
                return firstLetter.previous();
            }
            return nextLetter;
        } else {
            Letter previousLetter = firstLetter.previous();
            if (previousLetter == null) {
                return lastLetter.next();
            }
            return previousLetter;
        }
    }

    /**
     * Generate a chain that contains random letters from the currently active
     * alphabet.
     *
     * @param length int length of the chain
     * @return LetterChain object
     * @throws java.lang.IllegalArgumentException if the requested length is a negative number
     */
    public static LetterChain generateRandomChain(int length) throws IllegalArgumentException {
        if (length < 0) {
            throw new IllegalArgumentException("The requested chain length can't be negative.");
        }
        LetterChain chain = new LetterChain();
        Letter letter;
        for (int i = 0; i < length; i++) {
            letter = Letter.getRandomLetter();
            chain.add(letter);
        }
        return chain;
    }

    /**
     * Generate a chain that contains consecutive letters
     *
     * @param length int required chain length
     * @return valid (has all Letter(s) in consecutive order) LetterChain object
     * @throws java.lang.IllegalArgumentException if the requested length  is a
     * negative number or exceeds the length of the alphabet
     */
    public static LetterChain generateValidChain(int length) throws IllegalArgumentException {
        if (length < 0) {
            throw new IllegalArgumentException("The requested chain length can't be negative.");
        }
        if (length > Alphabet.getCurrent().length()) {
            throw new IllegalArgumentException("The requested chain length exceeds the " +
                    "alphabet length.");
        }
        LetterChain chain = new LetterChain();
        Letter letter = Letter.getRandomLetter();
        chain.add(letter);
        for (int i = 1; i < length; i++) {
            Letter lastLetter = chain.get(i-1);
            Letter nextLetter = lastLetter.next();

            if (nextLetter != null) {
                chain.add(nextLetter);
            } else {
                Letter firstLetter = chain.get(0);
                Letter previousLetter = firstLetter.previous();

                if (previousLetter != null) {
                    chain.prepend(previousLetter);
                }
            }
        }
        return chain;
    }

    /**
     * Generate a letter chain that will have a specific number of consecutive letters
     * and additional random letters.
     *
     * @param consecutiveLettersCount int number of required consecutive letters
     * @param randomLettersCount int number of required random letters
     * @return LetterChain object
     */
    public static LetterChain generateChain(int consecutiveLettersCount, int randomLettersCount) {
        if (consecutiveLettersCount == 0) {
            return generateRandomChain(randomLettersCount);
        }
        LetterChain chain = generateValidChain(consecutiveLettersCount);
        LetterChain randomChain = generateRandomChain(randomLettersCount);
        return chain.concat(randomChain);
    }

    /**
     * Generate a letter chain according to the game level.
     *
     * @param level int game level
     * @return LetterChain object
     */
    public static LetterChain generateChain(int level, LetterGrid letterGrid) {
        int lettersCount = (level + 1) / 4 + 1;

        if (lettersCount < 3) {
            int gridLettersCount = letterGrid.lettersCount();
            if (gridLettersCount == 0) {
                return LetterChain.generateChain(2, 1);
            }
            if (gridLettersCount == 1) {
                return LetterChain.generateValidChain(2);
            }
            Letter letter = letterGrid.getRandomLetter();
            LetterChain letterChain = new LetterChain();
            letterChain.add(letter.adjacent());
            return letterChain;
        } else {
            return LetterChain.generateValidChain(lettersCount);
        }
    }
}
