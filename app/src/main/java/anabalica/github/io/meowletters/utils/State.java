package anabalica.github.io.meowletters.utils;

import android.content.SharedPreferences;

import anabalica.github.io.meowletters.GameActivity;
import anabalica.github.io.meowletters.letters.Cell;
import anabalica.github.io.meowletters.letters.Letter;
import anabalica.github.io.meowletters.metrics.Level;
import anabalica.github.io.meowletters.metrics.Score;

/**
 * Helper class to store and load GameActivity instance for persistence.
 * Kind of serialization/deserialization in shared preferences.
 *
 * @author Ana Balica
 */
public class State {
    private SharedPreferences sharedPreferences;

    public static final String STATE_LEVEL = "anabalica.github.io.meowletters.state_level";
    public static final String STATE_SCORE = "anabalica.github.io.meowletters.state_score";

    public State(SharedPreferences sharedPreferences) {
        this.sharedPreferences = sharedPreferences;
    }

    public void save(GameActivity gameActivity) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        // save metrics
        Level level = gameActivity.getLevel();
        Score score = gameActivity.getScore();
        editor.putInt(STATE_LEVEL, level.getLevel());
        editor.putInt(STATE_SCORE, score.getPoints());
        editor.apply();
    }
    /**
     * Encode a Letter object as a single string to be stored in shared preferences as
     * "<row><column><letter char><1 if selected, 0 otherwise>".
     *
     * @param letter Letter object
     * @return String encoded letter
     */
    private String encodeLetter(Letter letter) {
        StringBuilder letterStringBuilder = new StringBuilder();
        Cell position = letter.getPosition();
        letterStringBuilder.append(String.valueOf(position.getRow()));
        letterStringBuilder.append(String.valueOf(position.getColumn()));
        letterStringBuilder.append(letter.getLetter());
        String isSelected = letter.isSelected() ? "1" : "0";
        letterStringBuilder.append(isSelected);
        return letterStringBuilder.toString();
    }

    /**
     * Decode a Letter object from a string
     *
     * @param letterString String containing encoded information about Letter object
     * @return Letter object
     */
    private Letter decodeLetter(String letterString) {

        int row = Integer.parseInt(Character.toString(letterString.charAt(0)));
        int column = Integer.parseInt(Character.toString(letterString.charAt(1)));
        String letterChar = Character.toString(letterString.charAt(2));
        boolean isSelected = Integer.parseInt(Character.toString(letterString.charAt(3))) == 1;

        return new Letter(letterChar, row, column, isSelected);
    }

}
