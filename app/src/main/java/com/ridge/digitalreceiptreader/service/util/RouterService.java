package com.ridge.digitalreceiptreader.service.util;

import android.app.Activity;
import android.content.Intent;

import com.ridge.digitalreceiptreader.activity.home.MainActivity;
import com.ridge.digitalreceiptreader.activity.login.LoginActivity;

/**
 * Router class that will be used to route to different activities and
 * fragments.
 *
 * @author Sam Butler
 * @since Novemeber 9, 2021
 */
public class RouterService {
    private final Activity currentActivity;

    /**
     * Sets default values for the class.
     *
     * @param a current activity.
     */
    public RouterService(Activity a) {
        currentActivity = a;
    }

    /**
     * This will navigate to the given class activity based on the passed
     * in class.
     *
     * @param clazz The class object of the activity to route too.
     * @param <T> Object type of class.
     */
    public <T> void navigate(Class<T> clazz) {
        Intent intent = new Intent(currentActivity, clazz);
        currentActivity.startActivity(intent);
    }

    /**
     * This will route to the home page of the application.
     */
    public void navigateHome() {
        navigate(MainActivity.class);
    }

    /**
     * This will route to the login page of the application.
     */
    public void navigateLogin() {
        navigate(LoginActivity.class);
    }
}
