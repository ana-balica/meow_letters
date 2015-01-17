package anabalica.github.io.meowletters;

import android.app.Activity;
import android.os.Bundle;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.widget.TextView;

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

    @Override
    public void onResume() {
        super.onResume();

        TextView appLicenseTextView = (TextView) findViewById(R.id.credits_app_license);
        String appLicense = getResources().getString(R.string.app_license);
        appLicenseTextView.setText(Html.fromHtml(appLicense));
        appLicenseTextView.setMovementMethod(LinkMovementMethod.getInstance());

        TextView freepikLicenseTextView = (TextView) findViewById(R.id.credits_freepik_license);
        String freepikLicense = getResources().getString(R.string.freepik_license);
        freepikLicenseTextView.setText(Html.fromHtml(freepikLicense));
        freepikLicenseTextView.setMovementMethod(LinkMovementMethod.getInstance());

        TextView fontLicenseTextView = (TextView) findViewById(R.id.credits_font_license);
        String fontLicense = getResources().getString(R.string.font_license);
        fontLicenseTextView.setText(Html.fromHtml(fontLicense));
        fontLicenseTextView.setMovementMethod(LinkMovementMethod.getInstance());
    }
}
