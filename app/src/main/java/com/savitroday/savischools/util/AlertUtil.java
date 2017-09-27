package com.savitroday.savischools.util;

import android.app.Activity;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.view.View;

/**
 * Created by Harshita Ahuja on 22/09/17.
 */

public class AlertUtil {
    
    public static void showSnackbarWithMessage(String message,View view) {
        final Snackbar snackbar = Snackbar.make(view, message,
                Snackbar.LENGTH_LONG)
                                          .setAction("OK", new View.OnClickListener() {
                                              @Override
                                              public void onClick(View view) {
                        
                                              }
                                          });
        snackbar.show();
    }

    public static void callAlert(Activity activity, String message) {
        new AlertDialog.Builder(activity)
                .setCancelable(false)
                .setMessage(message)
                .setPositiveButton("OK",
                        (dialog, whichButton) -> dialog.dismiss()).create().show();
    }
}
