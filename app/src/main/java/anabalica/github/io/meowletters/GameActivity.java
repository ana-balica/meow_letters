package anabalica.github.io.meowletters;

import android.app.Activity;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;


/**
 * This is the activity that starts or continues the game. It is an educational
 * game for children to practice the knowledge of an alphabet.
 *
 * @author Ana Balica
 */
public class GameActivity extends Activity {
    private ProgressBar timerBar;
    private GameCountDownTimer timer;

    private int millisTotal = 3000;
    private int millisInterval = 5;
    private int millisReset = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        timerBar = (ProgressBar) findViewById(R.id.timerBar);
        timerBar.setMax(millisTotal);
        timerBar.setProgress(millisTotal);

        // Start the countdown timer
        timer = new GameCountDownTimer(millisTotal, millisInterval);
        timer.start();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_game, menu);
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

    public void selectLetter(View view) {
        System.out.println("Button pressed");
    }

    /**
     * Custom countdown timer that runs infinitely and updates it's state to
     * the progress bar.
     */
    public class GameCountDownTimer extends CountDownTimer {

        public GameCountDownTimer(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        @Override
        public void onFinish() {
            timerBar.setProgress(millisReset);
            timerBar.setProgress(millisTotal);
            timer.start();
        }

        @Override
        public void onTick(long millisUntilFinished) {
            timerBar.setProgress((int) millisUntilFinished);
        }
    }
}
