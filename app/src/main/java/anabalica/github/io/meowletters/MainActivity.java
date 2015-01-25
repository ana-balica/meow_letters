package anabalica.github.io.meowletters;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import anabalica.github.io.meowletters.utils.SoundManager;
import anabalica.github.io.meowletters.utils.Status;


/**
 * This is the launcher activity of the game and represents a simple game menu
 * with buttons to start or continue a game, access settings, highscores or
 * about game information.
 *
 * @author Ana Balica
 */
public class MainActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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
        SplashActivity.bus.post(SoundManager.MENU_BUTTON_SOUND);
        GameActivity.status = Status.RESUME;
        Intent intent = new Intent(this, GameActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        this.startActivity(intent);
        overridePendingTransition(R.anim.pull_in_right, R.anim.push_out_left);
    }

    /**
     * Start a new game
     */
    public void startNewGame(View view) {
        SplashActivity.bus.post(SoundManager.MENU_BUTTON_SOUND);
        GameActivity.status = Status.RUN;
        Intent intent = new Intent(this, GameActivity.class);
        this.startActivity(intent);
        overridePendingTransition(R.anim.pull_in_right, R.anim.push_out_left);
    }

    /**
     * Start game settings activity
     */
    public void startSettings(View view) {
        SplashActivity.bus.post(SoundManager.MENU_BUTTON_SOUND);
        Intent intent = new Intent(this, SettingsActivity.class);
        this.startActivity(intent);
        overridePendingTransition(R.anim.pull_in_right, R.anim.push_out_left);
    }

    /**
     * Start highscores activity
     */
    public void startHighscores(View view) {
        SplashActivity.bus.post(SoundManager.MENU_BUTTON_SOUND);
        Intent intent = new Intent(this, HighscoresActivity.class);
        this.startActivity(intent);
        overridePendingTransition(R.anim.pull_in_right, R.anim.push_out_left);
    }

    /**
     * Start about activity
     */
    public void startAbout(View view) {
        SplashActivity.bus.post(SoundManager.MENU_BUTTON_SOUND);
        Intent intent = new Intent(this, AboutActivity.class);
        this.startActivity(intent);
        overridePendingTransition(R.anim.pull_in_right, R.anim.push_out_left);
    }

    /**
     * Toggle the visibility of the Continue button
     */
    private void toggleResumeButton() {
        Button continue_button = (Button) findViewById(R.id.continue_game_button);
        if (GameActivity.status == Status.PAUSE) {
            continue_button.setVisibility(View.VISIBLE);
            SplashActivity.bus.post(SoundManager.MENU_BUTTON_SOUND);
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
