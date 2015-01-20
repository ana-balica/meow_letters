package anabalica.github.io.meowletters;

import android.app.Activity;
import android.os.Bundle;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;

import anabalica.github.io.meowletters.storage.Highscore;
import anabalica.github.io.meowletters.storage.HighscoresDataSource;


/**
 * This is the activity where user highscores are listed. Only the top 10
 * highscores are stored and visible to the user.
 *
 * @author Ana Balica
 */
public class HighscoresActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_highscores);

        List<Highscore> highscores = getHighscores();
        displayHighscores(highscores);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.pull_in_left, R.anim.push_out_right);
    }

    /**
     * Retrieve highscores from the highscores table
     *
     * @return List of Highscore objects
     */
    private List<Highscore> getHighscores() {
        HighscoresDataSource dataSource = new HighscoresDataSource(this);
        dataSource.open();
        List<Highscore> highscores = dataSource.getTopHighscores();
        dataSource.close();
        return highscores;
    }

    /**
     * Show the highscores in the layout
     *
     * @param highscores List of Highscore objects
     */
    private void displayHighscores(List<Highscore> highscores) {
        RelativeLayout highscoresLayout = (RelativeLayout) findViewById(R.id.highscores);
        int containerCounter = 0;

        for (Highscore highscore: highscores) {
            RelativeLayout container = (RelativeLayout) highscoresLayout.getChildAt(containerCounter);
            TextView playerTextView = (TextView) container.getChildAt(0);
            TextView scoreTextView = (TextView) container.getChildAt(1);

            String username = highscore.getUsername();
            playerTextView.setText(username);
            scoreTextView.setText(String.valueOf(highscore.getScore()));
            containerCounter++;
        }
    }
}
