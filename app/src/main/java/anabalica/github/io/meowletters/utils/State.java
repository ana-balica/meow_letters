package anabalica.github.io.meowletters.utils;

import android.content.SharedPreferences;

import anabalica.github.io.meowletters.GameActivity;
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
}
