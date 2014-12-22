package anabalica.github.io.meowletters.storage;

import android.provider.BaseColumns;

/**
 * Contract class for the highscores database.
 *
 * @author Ana Balica
 */
public final class HighscoresContract {
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "highscores.db";
    private static final String TEXT_TYPE = " TEXT";
    private static final String INTEGER_TYPE = " INTEGER";
    private static final String COMMA_SEP = ",";

    // To prevent someone from accidentally instantiating the contract class
    public HighscoresContract() {}

    public static abstract class HighscoreEntry implements BaseColumns {
        public static final String TABLE_NAME = "highscores";
        public static final String COLUMN_NAME_USERNAME = "username";
        public static final String COLUMN_NAME_SCORE = "score";

        public static final String SQL_CREATE_TABLE =
                "CREATE TABLE " + HighscoreEntry.TABLE_NAME + " (" +
                _ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_NAME_USERNAME + TEXT_TYPE + COMMA_SEP +
                COLUMN_NAME_SCORE + INTEGER_TYPE + " );";

        public static final String SQL_DELETE_TABLE =
                "DROP TABLE IF EXISTS " + TABLE_NAME;
    }
}
