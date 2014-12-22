package anabalica.github.io.meowletters.storage;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Database helper class for manipulating Highscores database.
 *
 * @author Ana Balica
 */
public class HighscoresDbHelper extends SQLiteOpenHelper {
    public HighscoresDbHelper(Context context) {
        super(context, HighscoresContract.DATABASE_NAME, null, HighscoresContract.DATABASE_VERSION);
    }

    public void onCreate(SQLiteDatabase db) {
        db.execSQL(HighscoresContract.HighscoreEntry.SQL_CREATE_TABLE);
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // do nothing so far
    }

    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // do nothing so far
    }
}
