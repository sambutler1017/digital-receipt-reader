package com.ridge.digitalreceiptreader.common.abstracts;

import android.app.Activity;

/**
 * Common Activity module class to extend from to use common methods
 * across the application
 *
 * @author Sam Butler
 * @since November 13, 2021
 */
public class ActivityModule<T extends Activity> extends BaseModule<T> {
    /**
     * Base constructor for setting the appView to be the activity being used.
     *
     * @param activity The activity to set the current appView too.
     */
    public ActivityModule(T activity) {
        super(activity);
        init();
    }
}
