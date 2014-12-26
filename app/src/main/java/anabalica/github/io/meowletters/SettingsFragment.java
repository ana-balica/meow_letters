package anabalica.github.io.meowletters;

import android.os.Bundle;
import android.preference.PreferenceFragment;

/**
 * Fragment to display user game settings.
 *
 * @author Ana Balica
 */
public class SettingsFragment extends PreferenceFragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        addPreferencesFromResource(R.xml.preferences);
    }
}
