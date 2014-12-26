package anabalica.github.io.meowletters;

import android.app.Activity;
import android.os.Bundle;


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
}
