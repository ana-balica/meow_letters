package anabalica.github.io.meowletters;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

import anabalica.github.io.meowletters.customviews.SquareButton;
import anabalica.github.io.meowletters.letters.Letter;
import anabalica.github.io.meowletters.letters.LetterChain;
import anabalica.github.io.meowletters.letters.LetterGrid;


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

    private LetterGrid letterGrid;
    private LetterChain letterChain;

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

        // Initialize the letter grid and initial letter chain
        letterGrid = new LetterGrid(5, 5);
        LetterChain chain = LetterChain.generateChain(2, 1);
        letterGrid.addLetterChain(chain);
        drawLetterButtons();

        // Initialize user defined letter chain
        letterChain = new LetterChain();
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
        SquareButton button = (SquareButton) view;
        int row = button.getRow();
        int column = button.getColumn();
        Letter letter = letterGrid.getLetter(row, column);
        if (letter != null) {
            if (letter.isSelected()) {
                letter.unselect();
                letterChain.remove(letter);
                button.setBackgroundColor(Color.GRAY);
            } else {
                letter.select();
                letterChain.add(letter);
                button.setBackgroundColor(Color.RED);
            }
        }
    }

    /**
     * Translate the LetterGrid to the actual UI grid by drawing the buttons,
     * their letter and their correct position.
     */
    public void drawLetterButtons() {
        int rows = letterGrid.getROWS();
        int columns = letterGrid.getCOLUMNS();
        for (int row = 0; row < rows; row++) {
            for (int column = 0; column < columns; column++) {
                if (letterGrid.getGrid()[row][column] != null) {
                    drawLetterButton(letterGrid.getGrid()[row][column], row, column);
                }
            }
        }
    }

    /**
     * Draw a LetterButton on the UI grid in the provided position
     *
     * @param letter Letter object
     * @param row int the row position
     * @param column int the column position
     */
    public void drawLetterButton(Letter letter, int row, int column) {
        String letterButtonId = buildLetterButtonId(row, column);

        int id = this.getResources().getIdentifier(letterButtonId, "id", this.getPackageName());
        Button button = (Button) findViewById(id);
        button.setVisibility(View.VISIBLE);
        button.setText(letter.getLetter());
    }


    /**
     * Pick the position of a letter button and create the string ID of this
     * button
     *
     * @param row int the row position
     * @param column int the column position
     * @return String button ID
     */
    private String buildLetterButtonId(int row, int column) {
        return "letter_button_" + row + column;
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
            LetterChain letterChain = LetterChain.generateChain(2, 1);
            letterGrid.addLetterChain(letterChain);
            drawLetterButtons();

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
