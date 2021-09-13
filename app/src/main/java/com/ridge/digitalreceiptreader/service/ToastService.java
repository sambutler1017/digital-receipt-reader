package com.ridge.digitalreceiptreader.service;

import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.ridge.digitalreceiptreader.R;

/**
 * Toast service class to display toast messages on the current activity screen.
 *
 * @author Sam Butler
 * @since July 28, 2021
 */
public class ToastService {
    private final Context currentContext;

    /**
     * Sets the current Context to the passed in activity context
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
        getToastMessage("#04844B", msg).show();
    }

    /**
     * Display red error message at the top of the current activity.
     *
     * @param msg What the toast message should say
     */
    public void showError(String msg) {
        getToastMessage("#DC4040", msg).show();
    }

    /**
     * Display yellow warning message at the top of the current activity.
     *
     * @param msg What the toast message should say
     */
    public void showWarning(String msg) {
        getToastMessage("#FFB75D", msg).show();
    }

    /**
     * Creates the toast message object for the given background color and message
     *
     * @param bgColor Color to set the background of the message too
     * @param msg     Message to be displayed
     * @return Toast message object with the given attributes
     */
    private Toast getToastMessage(String bgColor, String msg) {
        Toast toast = new Toast(currentContext);
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.setView(getDefaultLayout(bgColor, msg));
        toast.setGravity(Gravity.TOP, 0, 10);

        return toast;
    }

    /**
     * Get the default view layout for the toast message with he given message and
     * background color.
     *
     * @param backgroundColor Color to set the background of the toast message
     * @param message         Message to be displayed in the toast message
     * @return layout view with the set attributes
     */
    private View getDefaultLayout(String backgroundColor, String message) {
        LayoutInflater inflater = LayoutInflater.from(currentContext);
        View layout = inflater.inflate(R.layout.layout_custom_toast, null);

        setBackgroundColor(backgroundColor, layout);
        setTextViewMessage(message, layout);

        return layout;
    }

    /**
     * Sets the background color for the given color and layout to set the
     * background on.
     *
     * @param bgColor Color to set background too.
     * @param layout  View for color to be applied too.
     */
    private void setBackgroundColor(String bgColor, View layout) {
        LinearLayout square = layout.findViewById(R.id.toast_background);
        square.getBackground().setColorFilter(Color.parseColor(bgColor), PorterDuff.Mode.SRC_ATOP);
    }

    /**
     * Sets the text for the message toast message on the given view layout.
     *
     * @param message Message to attach to view.
     * @param layout  Layout to display message on.
     */
    private void setTextViewMessage(String message, View layout) {
        TextView textView = layout.findViewById(R.id.toast_message);
        textView.setText(message);
        textView.setTextColor(Color.WHITE);
    }
}
