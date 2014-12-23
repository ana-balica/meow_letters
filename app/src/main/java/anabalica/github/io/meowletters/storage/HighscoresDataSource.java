package anabalica.github.io.meowletters.storage;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

/**
 * A DAO (Data Access Object) class to highscores database
 *
 * @author Ana Balica
 */
public class HighscoresDataSource {

    private SQLiteDatabase db;
    private HighscoresDbHelper dbHelper;

    public HighscoresDataSource(Context context) {
        dbHelper = new HighscoresDbHelper(context);
    }

    public void open() {
        db = dbHelper.getReadableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    /**
     * Insert a new highscore into highscores table
     *
     * @param username String user game nickname
     * @param score int amount of game points accumulated by the end
     * @return true if the highscore was inserted, false otherwise
     */
    public boolean createHighscore(String username, int score) {
        if (score > 0) {
            ContentValues values = new ContentValues();
            values.put(HighscoresContract.HighscoreEntry.COLUMN_NAME_USERNAME, username);
            values.put(HighscoresContract.HighscoreEntry.COLUMN_NAME_SCORE, score);

            db.insert(HighscoresContract.HighscoreEntry.TABLE_NAME, null, values);
            return true;
        }
        return false;
    }
}
