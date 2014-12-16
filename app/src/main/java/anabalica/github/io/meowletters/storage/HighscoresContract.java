package anabalica.github.io.meowletters.storage;

import android.provider.BaseColumns;

/**
 * Contract class for the table of highscores. 
 *
 * @author Ana Balica
 */
public final class HighscoresContract {
    public HighscoresContract() {}

    public static abstract class HighscoreEntry implements BaseColumns {
        public static final String TABLE_NAME = "highscores";
        public static final String COLUMN_NAME_HIGHSCORE_ID = "highscoreid";
        public static final String COLUMN_NAME_USERNAME = "username";
        public static final String COLUMN_NAME_SCORE = "score";
    }
}
