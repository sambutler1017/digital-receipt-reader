package com.ridge.digitalreceiptreader.common.abstracts;

import android.app.Activity;
import android.view.View;
import android.widget.ProgressBar;

import com.ridge.digitalreceiptreader.common.interfaces.ModuleInterface;

/**
 * Base module class to implement common methods to be used.
 *
 * @author Sam Butler
 * @since October 29, 2021
 */
public abstract class BaseModule implements ModuleInterface {

    protected View currentView;
    protected Activity currentActivity;

    /**
     * Called when an activity is being used to manage the UI.
     *
     * @param a The reference to the activity.
     */
    public BaseModule(Activity a) {
        currentActivity = a;
        currentView = null;
        init();
    }

    /**
     * Called when a view is being used to manage the UI.
     *
     * @param v The view being used in the UI.
     */
    public BaseModule(View v) {
        currentView = v;
        currentActivity = null;
        init();
    }

    /**
     * Called when a view or activity is being used to manage the UI.
     *
     * @param v The view being used in the UI.
     * @param a The reference to the activity.
     */
    public BaseModule(View v, Activity a) {
        currentView = v;
        currentActivity = a;
        init();
    }

    /**
     * Methods that are called when the module is being initialized. The 3 init methods are
     * are to be implemented in the child classes.
     */
    private void init() {
        initElements();
        initServices();
        initClients();
    }

    /**
     * Empty initialization in case child class does not implement method.
     */
    public void initClients() {
    }

    /**
     * Empty initialization in case child class does not implement method.
     */
    public void initServices() {
    }

    /**
     * Empty initialization in case child class does not implement method.
     */
    public void initElements() {
    }

    /**
     * This will hide the passed in element.
     *
     * @param v The view to be hidden.
     */
    public void hide(View v) {
        v.setVisibility(View.GONE);
    }

    /**
     * This will show the passed in element.
     *
     * @param v The view to be hidden.
     */
    public void show(View v) {
        v.setVisibility(View.VISIBLE);
    }
}
