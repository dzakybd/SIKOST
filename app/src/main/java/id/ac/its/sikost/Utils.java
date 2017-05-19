package id.ac.its.sikost;

import android.text.TextUtils;
import android.widget.EditText;

/**
 * Created by Ilham Aulia Majid on 19-May-17.
 */

public class Utils {

    public static boolean isEmpty(EditText editText){
        String text = editText.getText().toString().trim();
        return TextUtils.isEmpty(text);
    }
}
