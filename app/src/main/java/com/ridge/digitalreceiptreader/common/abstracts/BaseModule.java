package com.ridge.digitalreceiptreader.common.abstracts;

import android.app.Activity;
import android.view.View;

import androidx.fragment.app.Fragment;

import com.ridge.digitalreceiptreader.common.interfaces.ModuleInterface;

/**
 * Base module class to implement common methods to be used.
 *
 * @author Sam Butler
 * @since October 29, 2021
 */
public abstract class BaseModule<T> implements ModuleInterface {
    protected T appContext;

    /**
     * Common constructor use to set the appView. Which can be either a
     * {@link Fragment} or {@link Activity}
     *
     * @param a The reference to the appView.
     */
    public BaseModule(T a) {
        appContext = a;
    }

    /**
     * Methods that are called when the module is being initialized. The 3 init
     * methods are are to be implemented in the child classes.
     */
    protected void init() {
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
