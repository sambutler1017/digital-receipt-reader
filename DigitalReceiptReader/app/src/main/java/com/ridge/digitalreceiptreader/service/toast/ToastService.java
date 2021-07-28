package com.ridge.digitalreceiptreader.service.toast;

import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.text.Html;
import android.view.Gravity;
import android.widget.Toast;

/**
 * Toast service class to display toast messages on the current activity
 * screen.
 *
 * @author Sam Butler
 * @since July 28, 2021
 */
public class ToastService {
    private Context currentContext;

    /**
     * Sets the currentConext to the passed in activity context
     *
     * @param context Current context of the Activity
     */
    public ToastService(Context context) {
        currentContext = context;
    }

    /**
     * Display green success message at the top of the current activity.
     *
     * @param msg What the toast message should say
     */
    public void showSuccess(String msg) {
        Toast toast = Toast.makeText(currentContext, Html.fromHtml("<font color='#FFFFFF'><b>" + msg + "</b></font>"), Toast.LENGTH_LONG);
        toast.setGravity(Gravity.TOP, 0, 10);
        toast.getView().getBackground().setColorFilter(Color.parseColor("#04844B"), PorterDuff.Mode.SRC_ATOP);
        toast.show();
    }

    /**
     * Display red error message at the top of the current activity.
     *
     * @param msg What the toast message should say
     */
    public void showError(String msg) {
        Toast toast = Toast.makeText(currentContext, Html.fromHtml("<font color='#FFFFFF'><b>" + msg + "</b></font>"), Toast.LENGTH_LONG);
        toast.setGravity(Gravity.TOP, 0, 10);
        toast.getView().getBackground().setColorFilter(Color.parseColor("#DC4040"), PorterDuff.Mode.SRC_ATOP);
        toast.show();
    }

    /**
     * Display yellow warning message at the top of the current activity.
     *
     * @param msg What the toast message should say
     */
    public void showWarning(String msg) {
        Toast toast = Toast.makeText(currentContext, Html.fromHtml("<font color='#FFFFFF'><b>" + msg + "</b></font>"), Toast.LENGTH_LONG);
        toast.setGravity(Gravity.TOP, 0, 10);
        toast.getView().getBackground().setColorFilter(Color.parseColor("#FFB75D"), PorterDuff.Mode.SRC_ATOP);
        toast.show();
    }
}
