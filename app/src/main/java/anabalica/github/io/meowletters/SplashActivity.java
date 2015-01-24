package anabalica.github.io.meowletters;

import android.app.Activity;
import android.os.Bundle;

import com.squareup.otto.Bus;

import anabalica.github.io.meowletters.utils.SoundManager;


/**
 * Splash activity for beautiful intro and initialization and registration of a bus (which
 * should be kept as a singleton).
 *
 * @author Ana Balica
 */
public class SplashActivity extends Activity {
    public static Bus bus = new Bus();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        bus.register(new SoundManager(this));
    }
}
