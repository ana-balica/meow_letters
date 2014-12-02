package anabalica.github.io.meowletters.letters;

import java.util.ArrayList;
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
     * Check if the chain has only consecutive Letter(s) according to the
     * current alphabet.
     *
     * @return true if has only consecutive Letters(s), false otherwise
     */
    public boolean isValid() {
        int chainSize = chain.size();
        if (chainSize < 2) {
            return true;
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
     * Add a new letter to the chain
     *
     * @param letter Letter object
     */
    public void add(Letter letter) {
        chain.add(letter);
    }

    /**
     * Remove letter from the chain. All following letters are also removed.
     * The following method assumes that there are no duplicates in the chain
     * (2 or more Letter objects with the same string letter).
     *
     * @param letter Letter object
     * @throws IllegalArgumentException if letter is not in the chain
     */
    public void remove(Letter letter) throws IllegalArgumentException{
        if (!chain.contains(letter)) {
            throw new IllegalArgumentException("Letter not in chain.");
        }
        int letter_index = chain.indexOf(letter);
        int last_index = chain.size() - 1;
        for (int i = last_index; i >= letter_index; i--) {
            chain.remove(i);
        }
    }
}
