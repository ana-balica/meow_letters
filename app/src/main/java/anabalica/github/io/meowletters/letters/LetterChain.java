package anabalica.github.io.meowletters.letters;

import java.util.ArrayList;

/**
 * LetterChain class is a container for manipulating a sequence of Letter
 * objects. In the game it represents the chain of selected letters by the user.
 * Order matters. There can be valid chains (D -> E -> F -> G) and invalid
 * chains (B -> C -> R).
 *
 * @author Ana Balica
 */
public class LetterChain {
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

}
