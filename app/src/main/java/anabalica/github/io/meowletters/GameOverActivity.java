package anabalica.github.io.meowletters;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.TextView;

import anabalica.github.io.meowletters.storage.HighscoresDataSource;
import anabalica.github.io.meowletters.utils.SoundManager;
import anabalica.github.io.meowletters.utils.Status;


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
        String level = intent.getStringExtra(GameActivity.LEVEL);
        String score = intent.getStringExtra(GameActivity.SCORE);

        TextView finalLevel = (TextView) findViewById(R.id.finalLevel);
        TextView finalScore = (TextView) findViewById(R.id.finalScore);
        finalLevel.setText(level);
        finalScore.setText(score);

        int points = Integer.parseInt(score);
        insertNewHighscore(points);
    }

    /**
     * Launch menu on 'Back' press
     */
    public void onBackPressed() {
        MainActivity.bus.post(SoundManager.MENU_BUTTON_SOUND);
        GameActivity.status = Status.STOP;
        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        this.startActivity(intent);
        overridePendingTransition(R.anim.pull_in_left, R.anim.push_out_right);
    }

    /**
     * Start a new game
     */
    public void startNewGame(View view) {
        MainActivity.bus.post(SoundManager.MENU_BUTTON_SOUND);
        GameActivity.status = Status.RUN;
        Intent intent = new Intent(this, GameActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        this.startActivity(intent);
        overridePendingTransition(R.anim.pull_in_left, R.anim.push_out_right);
    }

    /**
     * Insert into the highscores table a new entry
     *
     * @param score int amount of game points
     */
    private void insertNewHighscore(int score) {
        SharedPreferences sharedPrefs = PreferenceManager.getDefaultSharedPreferences(this);
        String nickname = sharedPrefs.getString(SettingsActivity.PREF_NICKNAME, "");

        HighscoresDataSource dataSource = new HighscoresDataSource(this);
        dataSource.open();
        dataSource.createHighscore(nickname, score);
        dataSource.close();
    }
}
