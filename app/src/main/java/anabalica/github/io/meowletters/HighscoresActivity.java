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


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_highscores, menu);
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
