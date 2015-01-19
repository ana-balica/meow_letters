package anabalica.github.io.meowletters;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import anabalica.github.io.meowletters.utils.Status;


/**
 * This is the launcher activity of the game and represents a simple game menu
 * with buttons to start or continue a game, access settings, highscores or
 * about game information.
 *
 * @author Ana Balica
 */
public class MainActivity extends Activity {
    SoundPool soundPool;
    int menuButtonSoundId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize the sound pool
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
            soundPool = new SoundPool(1, AudioManager.STREAM_MUSIC, 0);
        } else {
            SoundPool.Builder soundPoolBuilder = new SoundPool.Builder();
            soundPool = soundPoolBuilder.build();
        }
        menuButtonSoundId = soundPool.load(this, R.raw.menu_click, 1);

        // Set custom font
        TextView title = (TextView) findViewById(R.id.title);
        Typeface pacificotf = Typeface.createFromAsset(getAssets(), "fonts/Pacifico.ttf");
        title.setTypeface(pacificotf);

        toggleResumeButton();

        SharedPreferences sharedPrefs = PreferenceManager.getDefaultSharedPreferences(this);
        String nickname = sharedPrefs.getString(SettingsActivity.PREF_NICKNAME, "");

        if (nickname.equals("")) {
            String randomNickname = pickRandomNickname();
            SharedPreferences.Editor editor = sharedPrefs.edit();
            editor.putString(SettingsActivity.PREF_NICKNAME, randomNickname);
            editor.apply();
        }
    }

    @Override
    public void onResume() {
        toggleResumeButton();
        super.onResume();
    }

    /**
     * Continue previous game
     */
    public void resumeGame(View view) {
        soundPool.play(menuButtonSoundId, 1, 1, 0, 0, 1);
        GameActivity.status = Status.RESUME;
        Intent intent = new Intent(this, GameActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        this.startActivity(intent);
    }

    /**
     * Start a new game
     */
    public void startNewGame(View view) {
        soundPool.play(menuButtonSoundId, 1, 1, 0, 0, 1);
        GameActivity.status = Status.RUN;
        Intent intent = new Intent(this, GameActivity.class);
        this.startActivity(intent);
    }

    /**
     * Start game settings activity
     */
    public void startSettings(View view) {
        soundPool.play(menuButtonSoundId, 1, 1, 0, 0, 1);
        Intent intent = new Intent(this, SettingsActivity.class);
        this.startActivity(intent);
    }

    /**
     * Start highscores activity
     */
    public void startHighscores(View view) {
        soundPool.play(menuButtonSoundId, 1, 1, 0, 0, 1);
        Intent intent = new Intent(this, HighscoresActivity.class);
        this.startActivity(intent);
    }

    /**
     * Start about activity
     */
    public void startAbout(View view) {
        soundPool.play(menuButtonSoundId, 1, 1, 0, 0, 1);
        Intent intent = new Intent(this, AboutActivity.class);
        this.startActivity(intent);
    }

    /**
     * Toggle the visibility of the Continue button
     */
    private void toggleResumeButton() {
        Button continue_button = (Button) findViewById(R.id.continue_game_button);
        if (GameActivity.status == Status.PAUSE) {
            continue_button.setVisibility(View.VISIBLE);
            soundPool.play(menuButtonSoundId, 1, 1, 0, 0, 1);
        } else {
            continue_button.setVisibility(View.GONE);
        }
    }

    /**
     * Pick a random nickname from the predefined array of nicknames.
     *
     * @return String cute nickname
     */
    private String pickRandomNickname() {
        Resources res = getResources();
        String[] nicknames = res.getStringArray(R.array.nicknames);

        int randomIndex = (int) (Math.random() * nicknames.length);
        return nicknames[randomIndex];
    }
}
