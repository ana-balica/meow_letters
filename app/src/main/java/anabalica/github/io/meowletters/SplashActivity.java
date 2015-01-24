package anabalica.github.io.meowletters;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;


import anabalica.github.io.meowletters.utils.SoundManager;


/**
 * Splash activity for beautiful intro and loading of sounds.
 *
 * @author Ana Balica
 */
public class SplashActivity extends Activity {
    private final int SPLASH_DISPLAY_LENGTH = 1000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
//        bus.register(new SoundManager(this));

        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {
                /* Create an Intent that will start the Menu-Activity. */
                Intent mainIntent = new Intent(SplashActivity.this, MainActivity.class);
                SplashActivity.this.startActivity(mainIntent);
                SplashActivity.this.finish();
                overridePendingTransition(R.anim.pull_in_right, R.anim.push_out_left);
            }
        }, SPLASH_DISPLAY_LENGTH);

    }
}
