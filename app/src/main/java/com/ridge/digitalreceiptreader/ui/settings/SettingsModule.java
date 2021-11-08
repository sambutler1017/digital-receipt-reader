package com.ridge.digitalreceiptreader.ui.settings;

import android.content.Intent;
import android.view.View;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.ridge.digitalreceiptreader.activity.login.CreateAccountActivity;
import com.ridge.digitalreceiptreader.activity.login.LoginActivity;
import com.ridge.digitalreceiptreader.common.abstracts.BaseModule;
import com.ridge.digitalreceiptreader.service.util.LocalStorageService;

/**
 * Settings Module class to centralize methods being using in Settings fragment.
 *
 * @author Sam Butler
 * @since October 30, 2021
 */
public class SettingsModule extends BaseModule {
    private LocalStorageService localStorage;

    /**
     * Sets default values for the class.
     *
     * @param v current view.
     */
    public SettingsModule(View v) {
        super(v);
    }

    /**
     * Initialization the services being used.
     */
    public void initServices() {
        localStorage = new LocalStorageService(currentActivity);
    }

    /**
     * This will logout the user from the app. Which will remove the token from the
     * local storage and route them to login screen.
     */
    public void onLogOutClick() {
        localStorage.removeToken();
        Intent intent = new Intent(currentActivity, LoginActivity.class);
        currentActivity.startActivity(intent);
    }
}