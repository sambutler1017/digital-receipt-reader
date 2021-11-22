package com.ridge.digitalreceiptreader.common.utils;

import android.text.TextUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Common class to store functionality that can be used in multiple places.
 *
 * @author Sam Butler
 * @since October 30, 2021
 */
public abstract class CommonUtils {

    /**
     * Validates email address. NOTE: Taken from
     * https://stackoverflow.com/questions/1819142/how-should-i-validate-an-e-mail-address
     *
     * @param target email address
     * @return if email address is valid or not
     */
    public final static boolean isValidEmail(CharSequence target) {
        return !TextUtils.isEmpty(target) && android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches();
    }

    /**
     * This will format the date into the base date format for the application.
     *
     * @param d The date to format
     * @return {@link String} Of the formatted date.
     */
    public final static String formatDate(Date d) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date newDate = formatter.parse(d.toInstant().toString());
            return new SimpleDateFormat("MMMM dd, yyyy").format(newDate);
        } catch (ParseException e) {
            return "";
        }
    }
}
