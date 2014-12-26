package anabalica.github.io.meowletters;

import android.app.Activity;
import android.os.Bundle;

/**
 * This is the activity that contains information about the game, the authors,
 * the open source project behind it.
 *
 * @author Ana Balica
 */
public class AboutActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
    }
}
