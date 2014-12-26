package anabalica.github.io.meowletters.custompreferences;

import android.content.Context;
import android.preference.EditTextPreference;
import android.util.AttributeSet;

/**
 * Slightly modified version of EditTextPreference to display the text in the summary section.
 * See the usage of this class in meow_letters/app/src/main/res/xml/preferences.xml
 *
 * @author Ana Balica
 */
public class CustomEditTextPreference extends EditTextPreference {

    public CustomEditTextPreference(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public CharSequence getSummary() {
        return getText();
    }

    @Override
    public void onDialogClosed(boolean positiveResult) {
        super.onDialogClosed(positiveResult);

        setSummary(getSummary());
    }
}
