package anabalica.github.io.meowletters.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Build;
import android.preference.PreferenceManager;
import android.util.Log;

import com.squareup.otto.Subscribe;

import anabalica.github.io.meowletters.MainActivity;
import anabalica.github.io.meowletters.R;
import anabalica.github.io.meowletters.SettingsActivity;

/**
 * Class that manages loading sounds and playing them.
 *
 * @author Ana Balica
 */
public class SoundManager {
    private SoundPool soundPool;
    private int menuButtonSoundId;
    private int letterButtonSoundId;
    private int penaltySoundId;
    private int timerOutSoundId;
    private int gameOverSoundId;

    private int soundsCount = 5;
    private int soundsLoaded = 0;

    public final static String SOUND_LOADED = "anabalica.github.io.meowletter.sound_loaded";
    public final static String MENU_BUTTON_SOUND = "anabalica.github.io.meowletters.menu_button_sound";
    public final static String LETTER_SOUND = "anabalica.github.io.meowletters.letter_sound";
    public final static String PENALTY_SOUND = "anabalica.github.io.meowletters.penalty_sound";
    public final static String TIMER_OUT_SOUND = "anabalica.github.io.meowletters.timer_out_sound";
    public final static String GAME_OVER_SOUND = "anabalica.github.io.meowletters.game_over_sound";

    SharedPreferences sharedPrefs;

    public SoundManager(final Context context) {
        // Initialize the sound pool
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
            soundPool = new SoundPool(10, AudioManager.STREAM_MUSIC, 0);
        } else {
            SoundPool.Builder soundPoolBuilder = new SoundPool.Builder();
            soundPool = soundPoolBuilder.build();
        }

        // Load sounds
        menuButtonSoundId = soundPool.load(context, R.raw.menu_click, 1);
        letterButtonSoundId = soundPool.load(context, R.raw.letter_tap, 1);
        timerOutSoundId = soundPool.load(context, R.raw.timer_out, 1);
        gameOverSoundId = soundPool.load(context, R.raw.game_over, 1);
        penaltySoundId = soundPool.load(context, R.raw.penalty, 1);

        soundPool.setOnLoadCompleteListener(new SoundPool.OnLoadCompleteListener() {
            @Override
            public void onLoadComplete(SoundPool innerSoundPool, int sampleId, int status) {
                soundsLoaded++;
                if (soundsLoaded == soundsCount) {
                    // start MainActivity
                    Intent mainIntent = new Intent(context, MainActivity.class);
                    Activity activity = (Activity) context;
                    activity.startActivity(mainIntent);
                    activity.overridePendingTransition(R.anim.pull_in_right, R.anim.push_out_left);
                }
            }
        });

        sharedPrefs = PreferenceManager.getDefaultSharedPreferences(context);
    }

    @Subscribe public void playSound(String event) {
        boolean sounds = sharedPrefs.getBoolean(SettingsActivity.PREF_SOUND, true);
        if (!sounds) {
            return;
        }

        if (event.equals(MENU_BUTTON_SOUND)) {
            soundPool.play(menuButtonSoundId, 1, 1, 0, 0, 1);
        } else if (event.equals(LETTER_SOUND)) {
            soundPool.play(letterButtonSoundId, 1, 1, 0, 0, 1);
        } else if (event.equals(PENALTY_SOUND)) {
            soundPool.play(penaltySoundId, 1, 1, 0, 0, 1);
        }  else if (event.equals(TIMER_OUT_SOUND)) {
            soundPool.play(timerOutSoundId, 1, 1, 0, 0, 1);
        } else if (event.equals(GAME_OVER_SOUND)) {
            soundPool.play(gameOverSoundId, 1, 1, 0, 0, 1);
        } else {
            Log.w(this.getClass().toString(), "Unknown sound name");
        }
    }
}
