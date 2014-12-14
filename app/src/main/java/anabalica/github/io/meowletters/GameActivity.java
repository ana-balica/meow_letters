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
import android.widget.TextView;

import anabalica.github.io.meowletters.customviews.SquareButton;
import anabalica.github.io.meowletters.letters.Letter;
import anabalica.github.io.meowletters.letters.LetterChain;
import anabalica.github.io.meowletters.letters.LetterGrid;
import anabalica.github.io.meowletters.metrics.Level;
import anabalica.github.io.meowletters.metrics.Score;


/**
 * This is the activity that starts or continues the game. It is an educational
 * game for children to practice the knowledge of an alphabet.
 *
 * @author Ana Balica
 */
public class GameActivity extends Activity {
    private ProgressBar timerBar;
    private GameCountDownTimer timer;

    private int millisTotal = 5000;
    private int millisInterval = 5;
    private int millisReset = 0;
    private long millisPenalty = 1000;

    private LetterGrid letterGrid;
    private LetterChain selectedLetterChain = new LetterChain();

    private Score score = new Score();
    private Level level = new Level();

    private boolean penalty = false;
    private boolean createNewTimer = false;

    private final Integer[][] letterButtons = {
            {R.id.letter_button_00, R.id.letter_button_01, R.id.letter_button_02, R.id.letter_button_03, R.id.letter_button_04},
            {R.id.letter_button_10, R.id.letter_button_11, R.id.letter_button_12, R.id.letter_button_13, R.id.letter_button_14},
            {R.id.letter_button_20, R.id.letter_button_21, R.id.letter_button_22, R.id.letter_button_23, R.id.letter_button_24},
            {R.id.letter_button_30, R.id.letter_button_31, R.id.letter_button_32, R.id.letter_button_33, R.id.letter_button_34},
            {R.id.letter_button_40, R.id.letter_button_41, R.id.letter_button_42, R.id.letter_button_43, R.id.letter_button_44}};

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

    /**
     * Select a letter from the grid and add it to the letter chain.
     */
    public void selectLetter(View view) {
        SquareButton button = (SquareButton) view;
        int row = button.getRow();
        int column = button.getColumn();
        Letter letter = letterGrid.getLetter(row, column);

        // select/unselect letter(s)
        if (letter != null) {
            if (letter.isSelected()) {
                LetterChain removedLetters = selectedLetterChain.remove(letter);
                deselectLetterButtons(removedLetters);
            } else {
                letter.select();
                selectedLetterChain.add(letter);
                button.setBackgroundColor(Color.RED);
            }
        }

        // check if valid and if final
        if (selectedLetterChain.size() > 1) {
            if (selectedLetterChain.isValid()) {
                if (selectedLetterChain.isFinal(letterGrid)) {
                    timer.onFinish();
                }
            } else {
                deselectLetterButtons(selectedLetterChain);
                penalty = true;
            }
        }
    }

    /**
     * Deselect in the UI a chain of letters.
     *
     * @param chain LetterChain object of letters to be unselected
     */
    private void deselectLetterButtons(LetterChain chain) {
        for (Letter letter : chain) {
            letter.deselect();
            int row = letter.getPosition().getRow();
            int column = letter.getPosition().getColumn();

            Button button = (Button) findViewById(letterButtons[row][column]);
            button.setBackgroundColor(Color.GRAY);
        }
    }

    /**
     * Translate the LetterGrid to the actual UI grid by drawing the buttons,
     * their letter and their correct position.
     */
    private void drawLetterButtons() {
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
     * Hide from the UI grid all letters of a letter chain.
     *
     * @param letterChain LetterChain object
     */
    private void hideLetterButtons(LetterChain letterChain) {
        for (Letter letter : letterChain) {
            int row = letter.getPosition().getRow();
            int column = letter.getPosition().getColumn();
            hideLetterButton(row, column);
        }

    }

    /**
     * Draw a LetterButton on the UI grid in the provided position
     *
     * @param letter Letter object
     * @param row int the row position
     * @param column int the column position
     */
    private void drawLetterButton(Letter letter, int row, int column) {
        Button button = (Button) findViewById(letterButtons[row][column]);
        button.setVisibility(View.VISIBLE);
        button.setText(letter.getLetter());
    }

    /**
     * Hide a LetterButton from the UI grid
     *
     * @param row int the row position
     * @param column int the column position
     */
    private void hideLetterButton(int row, int column) {
        Button button = (Button) findViewById(letterButtons[row][column]);
        button.setVisibility(View.INVISIBLE);
        button.setText("");
        button.setBackgroundColor(Color.GRAY);
    }

    /**
     * Update the game score.
     *
     * @param letterChainSize int size of the letter chain
     */
    private void updateScore(int letterChainSize) {
        score.update(letterChainSize);
        TextView scoreView = (TextView) findViewById(R.id.score);
        String points = Integer.toString(score.getPoints());
        scoreView.setText("Score " + points);
    }

    /**
     * Update the game level.
     *
     * @param points int amount of score points
     */
    private void updateLevel(int points) {
        level.updateLevel(points);
        TextView levelView = (TextView) findViewById(R.id.level);
        String currentLevel = Integer.toString(level.getLevel());
        levelView.setText("Level " + currentLevel);
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
            if (selectedLetterChain.isValid()) {
                updateScore(selectedLetterChain.size());
                updateLevel(score.getPoints());
                hideLetterButtons(selectedLetterChain);
                letterGrid.removeLetterChain(selectedLetterChain);
            } else {
                deselectLetterButtons(selectedLetterChain);
            }

            if (createNewTimer) {
                timer.cancel();
                timer = new GameCountDownTimer(millisTotal, millisInterval);
                timer.start();
                createNewTimer = false;
            }

            selectedLetterChain.clear();

            // generate a new chain
            LetterChain letterChain = LetterChain.generateChain(2, 1);
            letterGrid.addLetterChain(letterChain);
            drawLetterButtons();

            // reset the timer
            timerBar.setProgress(millisReset);
            timerBar.setProgress(millisTotal);
            timer.start();
        }

        @Override
        public void onTick(long millisUntilFinished) {
            if (penalty) {
                millisUntilFinished -= millisPenalty;
                penalty = false;
                if (millisUntilFinished < 0) {
                    timer.onFinish();
                    return;
                }
                timer.cancel();
                timer = new GameCountDownTimer(millisUntilFinished, millisInterval);
                timer.start();
                createNewTimer = true;
            }
            timerBar.setProgress((int) millisUntilFinished);
        }
    }
}
