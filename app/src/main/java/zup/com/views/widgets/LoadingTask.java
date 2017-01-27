package zup.com.views.widgets;

import android.app.AlertDialog;
import android.content.Context;

public class LoadingTask extends AlertDialog {

    public LoadingTask(Context context) {
        super(context);
    }

    public static LoadingTask setupLoading(Context context, String title, String message, boolean canceable) {
        LoadingTask dialog = new LoadingTask(context);
        dialog.setTitle(title);
        dialog.setMessage(message);
        dialog.setCancelable(canceable);
        return dialog;
    }

}
