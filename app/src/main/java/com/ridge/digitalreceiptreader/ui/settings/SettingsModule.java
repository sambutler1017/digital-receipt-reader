package com.ridge.digitalreceiptreader.ui.settings;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.ridge.digitalreceiptreader.R;
import com.ridge.digitalreceiptreader.activity.login.CreateAccountActivity;
import com.ridge.digitalreceiptreader.activity.login.LoginActivity;
import com.ridge.digitalreceiptreader.common.abstracts.BaseModule;
import com.ridge.digitalreceiptreader.service.jwt.JwtHolder;
import com.ridge.digitalreceiptreader.service.util.LocalStorageService;

/**
 * Settings Module class to centralize methods being using in Settings fragment.
 *
 * @author Sam Butler
 * @since October 30, 2021
 */
public class SettingsModule extends BaseModule {
    private JwtHolder jwtHolder;
    private LocalStorageService localStorage;

    private TextView name;
    private TextView accountNumber;
    private TextView email;
    private TextView webRole;

    /**
     * Sets default values for the class.
     *
     * @param v current view.
     */
    public SettingsModule(View v, Activity a) {
        super(v, a);
    }

    /**
     * Initialization the services being used.
     */
    public void initServices() {
        jwtHolder = new JwtHolder(currentActivity);
        localStorage = new LocalStorageService(currentActivity);
    }

    /**
     * Initialization the elements being used.
     */
    public void initElements() {
        name = currentView.findViewById(R.id.settings__accountInfoContent_name__text);
        email = currentView.findViewById(R.id.settings__accountInfoContent_email__text);
        accountNumber = currentView.findViewById(R.id.settings__accountInfoContent_accountNumber__text);
        webRole = currentView.findViewById(R.id.settings__accountInfoContent_webRole_text);
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

    /**
     * This will delete the currently logged in user and their account.
     */
    public void onDeleteAccount() {
        System.out.println("Account Deleted!");
    }

    /**
     * This will populate the account information for the settings
     * page.
     */
    public void populateAccountInfo() {
        name.setText(jwtHolder.get("firstName") + " " + jwtHolder.get("lastName"));
        email.setText(jwtHolder.getRequiredEmail());
        accountNumber.setText(String.valueOf(jwtHolder.getRequiredUserId()));
        webRole.setText(jwtHolder.getWebRole().toString());
    }
}