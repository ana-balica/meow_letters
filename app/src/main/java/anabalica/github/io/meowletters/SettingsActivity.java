package anabalica.github.io.meowletters;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;

import anabalica.github.io.meowletters.letters.Alphabet;


/**
 * This is the activity that allows to access, view and change game settings.
 * Settings include username setup, sound on/off.
 *
 * For future, possible settings:
 *  - setup for colorblind
 *  - choice of alphabet
 *  - interface language
 *  - inversion of the alphabet (practise it backwards)
 *
 * @author Ana Balica
 */
public class SettingsActivity extends Activity {

    public final static String PREF_NICKNAME = "pref_nickname";
    public final static String PREF_ALPHABET = "pref_alphabet";
    public final static String PREF_SOUND = "pref_sound";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getFragmentManager().beginTransaction()
                .replace(android.R.id.content, new SettingsFragment())
                .commit();
    }

    @Override
    protected void onPause() {
        super.onPause();

        SharedPreferences sharedPrefs = PreferenceManager.getDefaultSharedPreferences(this);
        String alphabetName = sharedPrefs.getString(SettingsActivity.PREF_ALPHABET, "");

        Alphabet.setCurrent(alphabetName);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.pull_in_left, R.anim.push_out_right);
    }
}
