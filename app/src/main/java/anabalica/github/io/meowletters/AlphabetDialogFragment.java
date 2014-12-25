package anabalica.github.io.meowletters;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;

/**
 * Fragment dialog to allow the user to pick an alphabet from a list.
 *
 * @author Ana Balica
 */
public class AlphabetDialogFragment extends DialogFragment {
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(R.string.choose_alphabet)
                .setItems(R.array.alphabets, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // The 'which' argument contains the index position
                        // of the selected item
                        System.out.println(which);
                    }
                });
        return builder.create();
    }
}
