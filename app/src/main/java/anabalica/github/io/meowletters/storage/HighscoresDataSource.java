package anabalica.github.io.meowletters.storage;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

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

    /**
     * Select and return top 10 highscores from the highscores table.
     *
     * @return List of Highscore objects
     */
    public List<Highscore> getTopHighscores() {
        List<Highscore> highscores = new ArrayList<>();

        String[] projection = {
                HighscoresContract.HighscoreEntry.COLUMN_NAME_USERNAME,
                HighscoresContract.HighscoreEntry.COLUMN_NAME_SCORE
        };
        String limitBy = "10";
        String sortOrder = HighscoresContract.HighscoreEntry.COLUMN_NAME_SCORE + " DESC";
        Cursor c = db.query(
                HighscoresContract.HighscoreEntry.TABLE_NAME,
                projection,
                null,
                null,
                null,
                null,
                sortOrder,
                limitBy);

        int usernameIndex = c.getColumnIndex(HighscoresContract.HighscoreEntry.COLUMN_NAME_USERNAME);
        int scoreIndex = c.getColumnIndex(HighscoresContract.HighscoreEntry.COLUMN_NAME_SCORE);

        c.moveToFirst();
        do {
            String username = c.getString(usernameIndex);
            int score = c.getInt(scoreIndex);
            Highscore highscore = new Highscore(username, score);
            highscores.add(highscore);
        } while (c.moveToNext());

        return highscores;
    }
}
