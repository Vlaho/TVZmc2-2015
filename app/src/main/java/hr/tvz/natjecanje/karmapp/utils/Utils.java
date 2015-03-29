package hr.tvz.natjecanje.karmapp.utils;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;
import hr.tvz.natjecanje.R;

/**
 * Created by Tomislav on 29.3.2015..
 */
public class Utils {
    public static void showAndLogError(Context context, String tag, String message) {
        String messageToDisplay = context.getResources().getString(R.string.error);
        Toast.makeText(context, messageToDisplay, Toast.LENGTH_LONG).show();
        Log.e(tag, message);
    }
}
