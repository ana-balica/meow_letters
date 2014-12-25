package anabalica.github.io.meowletters;

import android.app.Activity;
import android.app.DialogFragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;


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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_settings, menu);
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
     * Display the dialog to choose an alphabet.
     */
    public void chooseAlphabet(View view) {
        DialogFragment alphabetDialog = new AlphabetDialogFragment();
        alphabetDialog.show(getFragmentManager(), "alphabet");
    }
}
