package com.ridge.digitalreceiptreader.common.abstracts;

import androidx.appcompat.app.AppCompatActivity;

import com.ridge.digitalreceiptreader.common.interfaces.ActivityInterface;

/**
 * Base activity class to implement common methods to be used.
 *
 * @author Sam Butler
 * @since October 29, 2021
 */
public abstract class BaseActivity extends AppCompatActivity implements ActivityInterface {
    /**
     * Base initialization of the activity
     */
    public void initialization() {
        initElements();
        initListeners();
        initServices();
    }

    /**
     * Empty initialization in case child class does not implement method.
     */
    public void initElements() {
    }

    /**
     * Empty initialization in case child class does not implement method.
     */
    public void initListeners() {
    }

    /**
     * Empty initialization in case child class does not implement method.
     */
    public void initServices() {
    }
}
