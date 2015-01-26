package anabalica.github.io.meowletters;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.preference.PreferenceManager;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.github.amlcurran.showcaseview.OnShowcaseEventListener;
import com.github.amlcurran.showcaseview.ShowcaseView;
import com.github.amlcurran.showcaseview.targets.ViewTarget;

import anabalica.github.io.meowletters.customviews.SquareButton;
import anabalica.github.io.meowletters.letters.Letter;
import anabalica.github.io.meowletters.letters.LetterChain;
import anabalica.github.io.meowletters.letters.LetterGrid;
import anabalica.github.io.meowletters.metrics.Level;
import anabalica.github.io.meowletters.metrics.Score;
import anabalica.github.io.meowletters.utils.SoundManager;
import anabalica.github.io.meowletters.utils.Status;
import anabalica.github.io.meowletters.utils.State;


/**
 * This is the activity that starts or continues the game. It is an educational
 * game for children to practice the knowledge of an alphabet.
 *
 * @author Ana Balica
 */
public class GameActivity extends Activity implements OnShowcaseEventListener, AdapterView.OnItemClickListener {
    public static final int gridRows = 5;
    public static final int gridColumns = 5;
    private ProgressBar timerBar;
    private GameCountDownTimer timer;
    private State state;

    private int millisTotal = 5000;
    private int millisInterval = 5;
    private int millisReset = 0;
    private long millisPenalty = 1000;
    private long timerMillisUntilFinished;

    public final static String LEVEL = "anabalica.github.io.meowletters.level";
    public final static String SCORE = "anabalica.github.io.meowletters.score";
    public final static String FIRST_LAUNCH = "first_launch";

    private LetterGrid letterGrid;
    private LetterChain selectedLetterChain = new LetterChain();
    private LetterChain firstLetterChain;

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

    public static Status status = Status.RUN;
    private Boolean firstLaunch;
    private ShowcaseView nextShowcaseView;
    private static int showcaseViewCounter = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
    }

    public Level getLevel() {
        return level;
    }

    public Score getScore() {
        return score;
    }

    public boolean hasPenalty() {
        return penalty;
    }

    public boolean isCreateNewTimer() {
        return createNewTimer;
    }

    public LetterChain getSelectedLetterChain() {
        return selectedLetterChain;
    }

    public LetterGrid getLetterGrid() {
        return letterGrid;
    }

    public long getTimerMillisUntilFinished() {
        return timerMillisUntilFinished;
    }

    private void startGame() {
        timerBar = (ProgressBar) findViewById(R.id.timerBar);
        timerBar.setMax(millisTotal);
        timerBar.setProgress(millisTotal);

        // Initialize the letter grid and initial letter chain
        letterGrid = new LetterGrid(gridRows, gridColumns);
        firstLetterChain  = LetterChain.generateChain(2, 1);
        letterGrid.addLetterChain(firstLetterChain);
        drawLetterButtons();

        SharedPreferences sharedPrefs = PreferenceManager.getDefaultSharedPreferences(this);
        firstLaunch = sharedPrefs.getBoolean(FIRST_LAUNCH, true);
        if (firstLaunch) {
            new ShowcaseView.Builder(this, true)
                    .setContentText(R.string.step_1)
                    .setStyle(R.style.CustomShowcaseTheme)
                    .setShowcaseEventListener(this)
                    .build();
        } else {
            timer = new GameCountDownTimer(millisTotal, millisInterval);
            timer.start();
        }
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
        System.out.println("OnItemClick");
    }

    @Override
    public void onShowcaseViewHide(ShowcaseView showcaseView) {
        System.out.println("OnShowcaseViewHide");
        System.out.println(showcaseView);
        showcaseViewCounter++;
        int row;
        int column;
        ViewTarget target;
        Letter secondLetter = firstLetterChain.get(1);
        switch (showcaseViewCounter) {
            case 1:
                Letter firstLetter = firstLetterChain.get(0);
                row = firstLetter.getPosition().getRow();
                column = firstLetter.getPosition().getColumn();
                target = new ViewTarget(letterButtons[row][column], this);
                nextShowcaseView = new ShowcaseView.Builder(this, true)
                        .setContentText(R.string.step_2)
                        .setStyle(R.style.CustomShowcaseTheme)
                        .setTarget(target)
                        .setShowcaseEventListener(this)
                        .build();
                nextShowcaseView.hideButton();
                break;
            case 2:
                row = secondLetter.getPosition().getRow();
                column = secondLetter.getPosition().getColumn();
                target = new ViewTarget(letterButtons[row][column], this);
                nextShowcaseView = new ShowcaseView.Builder(this, true)
                        .setContentText(R.string.step_3)
                        .setStyle(R.style.CustomShowcaseTheme)
                        .setTarget(target)
                        .setShowcaseEventListener(this)
                        .build();
                nextShowcaseView.hideButton();
                break;
            case 3:
                row = secondLetter.getPosition().getRow();
                column = secondLetter.getPosition().getColumn();
                target = new ViewTarget(letterButtons[row][column], this);
                nextShowcaseView = new ShowcaseView.Builder(this, true)
                        .setContentText(R.string.step_4)
                        .setStyle(R.style.CustomShowcaseTheme)
                        .setTarget(target)
                        .setShowcaseEventListener(this)
                        .build();
                nextShowcaseView.hideButton();
                break;
            case 4:
                nextShowcaseView = new ShowcaseView.Builder(this, true)
                        .setContentText(R.string.step_5)
                        .setStyle(R.style.CustomShowcaseTheme)
                        .setShowcaseEventListener(this)
                        .build();
                break;

            case 5:
                target = new ViewTarget(R.id.timerBar, this);
                nextShowcaseView = new ShowcaseView.Builder(this, true)
                        .setContentText(R.string.step_6)
                        .setStyle(R.style.CustomShowcaseTheme)
                        .setTarget(target)
                        .setShowcaseEventListener(this)
                        .build();
                break;
            case 6:
                nextShowcaseView = new ShowcaseView.Builder(this, true)
                        .setContentText(R.string.step_7)
                        .setStyle(R.style.CustomShowcaseTheme)
                        .setShowcaseEventListener(this)
                        .build();
                break;
            case 7:
                nextShowcaseView = new ShowcaseView.Builder(this, true)
                        .setContentText(R.string.step_8)
                        .setStyle(R.style.CustomShowcaseTheme)
                        .setShowcaseEventListener(this)
                        .build();
                break;
            case 8:
                nextShowcaseView = new ShowcaseView.Builder(this, true)
                        .setContentText(R.string.step_9)
                        .setStyle(R.style.FinalCustomShowcaseTheme)
                        .setShowcaseEventListener(this)
                        .build();
                break;
            case 9:
                SharedPreferences sharedPrefs = PreferenceManager.getDefaultSharedPreferences(this);
                SharedPreferences.Editor editor = sharedPrefs.edit();
                editor.putBoolean(FIRST_LAUNCH, false);
                editor.apply();

                timer = new GameCountDownTimer(millisTotal, millisInterval);
                timer.start();
                break;
        }
    }

    @Override
    public void onShowcaseViewShow(ShowcaseView showcaseView) {
        // do nothing
    }

    @Override
    public void onShowcaseViewDidHide(ShowcaseView showcaseView) {
        // do nothing
    }

    private void resumeGame() {
        status = Status.RESUME;
        setScoreText();
        setLevelText();
        drawLetterButtons();

        timerBar = (ProgressBar) findViewById(R.id.timerBar);
        timerBar.setMax(millisTotal);
        timerBar.setProgress((int) timerMillisUntilFinished);

        // Start the countdown timer
        timer = new GameCountDownTimer(millisTotal, millisInterval);
        timer.start();
    }

    private void loadState() {
        SharedPreferences sharedPreferences = getPreferences(MODE_PRIVATE);
        state = new State(sharedPreferences);
        level = state.getLevel();
        score = state.getScore();
        letterGrid = state.getLetterGrid();
        selectedLetterChain = state.getSelectedLetterChain();
        penalty = state.getPenalty();
        createNewTimer = state.getCreateNewTimer();
        timerMillisUntilFinished = state.getTimerMillisUntilFinished();
    }

    @Override
    public void onPause() {
        timer.cancel();
        status = Status.PAUSE;
        SharedPreferences sharedPreferences = getPreferences(MODE_PRIVATE);
        state = new State(sharedPreferences);
        state.save(this);
        super.onPause();
    }

    @Override
    public void onResume() {
        if (status == Status.RUN) {
            startGame();
        } else if (status == Status.RESUME) {
            loadState();
            resumeGame();
        } else if (status == Status.PAUSE) {
            onBackPressed();
        }
        super.onResume();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.pull_in_left, R.anim.push_out_right);
    }

    /**
     * Select a letter from the grid add it to the letter chain and
     * display it on the grid.
     */
    public void selectLetter(View view) {
        SquareButton button = (SquareButton) view;
        int row = button.getRow();
        int column = button.getColumn();
        Letter letter = letterGrid.getLetter(row, column);

        // select/deselect letter(s)
        if (letter != null) {
            SplashActivity.bus.post(SoundManager.LETTER_SOUND);
            if (letter.isSelected()) {
                LetterChain removedLetters = selectedLetterChain.remove(letter);
                deselectLetterButtons(removedLetters);
            } else {
                letter.select();
                selectedLetterChain.add(letter);
                selectLetterButton(button);
            }
        }

        if (showcaseViewCounter == 1 || showcaseViewCounter == 2 || showcaseViewCounter == 3) {
            nextShowcaseView.hide();
            return;
        }

        // check if valid and if final
        if (selectedLetterChain.size() > 1) {
            if (selectedLetterChain.isValid()) {
                if (selectedLetterChain.isFinal(letterGrid)) {
                    timer.onFinish();
                }
            } else {
                deselectLetterButtons(selectedLetterChain);
                shakeLetterButtons(selectedLetterChain);
                penalty = true;
            }
        }
    }

    /**
     * Create shake/wobble animation for letters on the grid.
     *
     * @param letterChain LetterChain object
     */
    private void shakeLetterButtons(LetterChain letterChain) {
        for (Letter letter: letterChain) {
            int row = letter.getPosition().getRow();
            int column = letter.getPosition().getColumn();
            Button button = (Button) findViewById(letterButtons[row][column]);
            Animation shakeAnimation = AnimationUtils.loadAnimation(this, R.anim.shake);
            button.startAnimation(shakeAnimation);
        }
    }

    /**
     * Selecte in the UI a letter button.
     *
     * @param button
     */
    private void selectLetterButton(Button button) {
        button.setTextColor(getResources().getColor(R.color.beige));
        button.setBackground(getResources().getDrawable(R.drawable.letter_button_selected));
    }

    /**
     * Deselect in the UI a chain of letters.
     *
     * @param chain LetterChain object of letters to be unselected
     */
    private void deselectLetterButtons(LetterChain chain) {
        for (Letter letter : chain) {
            int row = letter.getPosition().getRow();
            int column = letter.getPosition().getColumn();
            letter.deselect();
            letterGrid.getLetter(row, column).deselect();

            Button button = (Button) findViewById(letterButtons[row][column]);
            button.setTextColor(getResources().getColor(R.color.red));
            button.setBackground(getResources().getDrawable(R.drawable.letter_button_unselected));
        }
    }

    /**
     * Translate the LetterGrid to the actual UI grid by drawing the buttons,
     * their letter and their correct position.
     */
    private void drawLetterButtons() {
        for (int row = 0; row < gridRows; row++) {
            for (int column = 0; column < gridColumns; column++) {
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
    private void drawLetterButton(Letter letter, int row, int column) {
        Button button = (Button) findViewById(letterButtons[row][column]);
        button.setText(letter.getLetter());
        if (letter.isSelected()) {
            button.setBackground(getResources().getDrawable(R.drawable.letter_button_selected));
            button.setTextColor(getResources().getColor(R.color.beige));
        } else {
            button.setBackground(getResources().getDrawable(R.drawable.letter_button_unselected));
            button.setTextColor(getResources().getColor(R.color.red));
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
     * Hide a LetterButton from the UI grid
     *
     * @param row int the row position
     * @param column int the column position
     */
    private void hideLetterButton(int row, int column) {
        Button button = (Button) findViewById(letterButtons[row][column]);
        button.setBackground(getResources().getDrawable(R.drawable.letter_button_unselected));
        button.setText("");
    }

    /**
     * Update the game score.
     *
     * @param letterChainSize int size of the letter chain
     */
    private void updateScore(int letterChainSize) {
        score.update(letterChainSize);
        setScoreText();
    }

    /**
     * Set the game score in the UI
     */
    private void setScoreText() {
        TextView scoreView = (TextView) findViewById(R.id.score);
        String points = Integer.toString(score.getPoints());
        scoreView.setText(points);
    }

    /**
     * Update the game level.
     *
     * @param points int amount of score points
     */
    private void updateLevel(int points) {
        level.updateLevel(points);
        setLevelText();
    }

    /**
     * Set the game level in the UI
     */
    private void setLevelText() {
        TextView levelView = (TextView) findViewById(R.id.level);
        String currentLevel = Integer.toString(level.getLevel());
        levelView.setText(currentLevel);
    }

    /**
     * Add a letter chain to the grid.
     */
    private void addLetterChain() {
        LetterChain letterChain = LetterChain.generateChain(level.getLevel(), letterGrid);
        letterGrid.addLetterChain(letterChain);
        drawLetterButtons();
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

            // check if game over
            if (letterGrid.getEmptyCellsCount() == 0) {
                SplashActivity.bus.post(SoundManager.GAME_OVER_SOUND);
                Intent intent = new Intent(GameActivity.this, GameOverActivity.class);
                String points = String.valueOf(score.getPoints());
                intent.putExtra(SCORE, points);
                intent.putExtra(LEVEL, String.valueOf(level.getLevel()));
                GameActivity.this.startActivity(intent);
                overridePendingTransition(R.anim.pull_in_right, R.anim.push_out_left);
                return;
            }

            selectedLetterChain.clear();
            addLetterChain();
            SplashActivity.bus.post(SoundManager.TIMER_OUT_SOUND);

            // reset the timer
            timerBar.setProgress(millisReset);
            timerBar.setProgress(millisTotal);
            timer.start();
        }

        @Override
        public void onTick(long millisUntilFinished) {
            if (penalty) {
                addPenalty(millisUntilFinished);
                penalty = false;
            }
            if (status == Status.RESUME) {
                startFrom(timerMillisUntilFinished);
                status = Status.RUN;
            }
            timerBar.setProgress((int) millisUntilFinished);
            timerMillisUntilFinished = millisUntilFinished;
        }

        public void startFrom(long millisUntilFinished) {
            timer.cancel();
            timer = new GameCountDownTimer(millisUntilFinished, millisInterval);
            timer.start();
            createNewTimer = true;
        }

        /**
         * Add a timer penalty, i.e. decrement some amount of time from the countdown timer.
         *
         * @param millisUntilFinished long amount of milliseconds until timer finishes
         */
        private void addPenalty(long millisUntilFinished) {
            SplashActivity.bus.post(SoundManager.PENALTY_SOUND);
            millisUntilFinished -= millisPenalty;
            if (millisUntilFinished < 0) {
                timer.onFinish();
                return;
            }
            timer.cancel();
            timer = new GameCountDownTimer(millisUntilFinished, millisInterval);
            timer.start();
            createNewTimer = true;
            deselectLetterButtons(selectedLetterChain);
            selectedLetterChain.clear();
        }
    }
}
