package anabalica.github.io.meowletters;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import anabalica.github.io.meowletters.storage.HighscoresContract;
import anabalica.github.io.meowletters.storage.HighscoresDataSource;
import anabalica.github.io.meowletters.storage.HighscoresDbHelper;


/**
 * This is the Game Over activity. When there are no more free spots for letters to appear, the
 * game ends.
 *
 * @author Ana Balica
 */
public class GameOverActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_over);
        Intent intent = getIntent();
        String score = intent.getStringExtra(GameActivity.SCORE);

        TextView finalScore = (TextView) findViewById(R.id.finalScore);
        finalScore.setText("Score " + score);

        int points = Integer.parseInt(score);
        insertNewHighscore(points);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_game_over, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * Launch menu on 'Back' press
     */
    public void onBackPressed() {
        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        this.startActivity(intent);
    }

    /**
     * Start a new game
     */
    public void startNewGame(View view) {
        Intent intent = new Intent(this, GameActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        this.startActivity(intent);
    }

    /**
     * Insert into the highscores table a new entry
     *
     * @param score int amount of game points
     */
    private void insertNewHighscore(int score) {
        HighscoresDataSource dataSource = new HighscoresDataSource(this);
        dataSource.open();
        dataSource.createHighscore("Chubby bunny", score);
        dataSource.close();
    }
}
