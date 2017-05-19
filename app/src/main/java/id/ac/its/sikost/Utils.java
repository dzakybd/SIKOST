package id.ac.its.sikost;

import android.text.TextUtils;
import android.widget.EditText;

import java.util.List;

/**
 * Created by Ilham Aulia Majid on 19-May-17.
 */

public class Utils {

    public static boolean isEmpty(EditText editText){
        String text = editText.getText().toString().trim();
        return TextUtils.isEmpty(text);
    }

    public static void setEmptyErrorMessage(EditText editText, CharSequence message){
        if (isEmpty(editText)) {
            editText.setError(message);
        }
    }

    public static boolean setEmptyErrorMessage(List<EditText> editTexts, CharSequence message){
        boolean empty = false;
        for (EditText editText:editTexts){
            setEmptyErrorMessage(editText, message);
            if (isEmpty(editText)) empty = true;
        }
        return empty;
    }
}
