package com.ridge.digitalreceiptreader.common.utils;

import android.text.TextUtils;

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
}
