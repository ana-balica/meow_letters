package anabalica.github.io.meowletters;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
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

    /**
     * Retrieve highscores from the highscores table
     *
     * @return List of Highscore objects
     */
    private List<Highscore> getHighscores() {
        HighscoresDataSource dataSource = new HighscoresDataSource(this);
        dataSource.open();
        List<Highscore> highscores = dataSource.getTopComments();
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
        int childrenCount = highscores.size() * 2;
        int highscoreCounter = 0;

        for (int i = 0; i < childrenCount; i += 2) {
            TextView playerTextView = (TextView) highscoresLayout.getChildAt(i);
            TextView scoreTextView = (TextView) highscoresLayout.getChildAt(i + 1);
            Highscore highscore = highscores.get(highscoreCounter);

            String username = String.valueOf(highscoreCounter + 1) + ". " + highscore.getUsername();
            playerTextView.setText(username);
            scoreTextView.setText(String.valueOf(highscore.getScore()));
            highscoreCounter++;
        }
    }
}
