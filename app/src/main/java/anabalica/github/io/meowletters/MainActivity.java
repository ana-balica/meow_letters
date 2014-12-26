package anabalica.github.io.meowletters;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;


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

        SharedPreferences sharedPrefs = PreferenceManager.getDefaultSharedPreferences(this);
        String nickname = sharedPrefs.getString(SettingsActivity.PREF_NICKNAME, "");

        if (nickname.equals("")) {
            String randomNickname = pickRandomNickname();
            SharedPreferences.Editor editor = sharedPrefs.edit();
            editor.putString(SettingsActivity.PREF_NICKNAME, randomNickname);
            editor.apply();
        }
    }

    /**
     * Start a new game
     */
    public void startNewGame(View view) {
        Intent intent = new Intent(this, GameActivity.class);
        this.startActivity(intent);
    }

    /**
     * Start game settings activity
     */
    public void startSettings(View view) {
        Intent intent = new Intent(this, SettingsActivity.class);
        this.startActivity(intent);
    }

    /**
     * Start highscores activity
     */
    public void startHighscores(View view) {
        Intent intent = new Intent(this, HighscoresActivity.class);
        this.startActivity(intent);
    }

    /**
     * Start about activity
     */
    public void startAbout(View view) {
        Intent intent = new Intent(this, AboutActivity.class);
        this.startActivity(intent);
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
