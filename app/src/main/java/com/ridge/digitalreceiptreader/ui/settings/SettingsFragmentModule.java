package com.ridge.digitalreceiptreader.ui.settings;

import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import com.ridge.digitalreceiptreader.R;
import com.ridge.digitalreceiptreader.activity.login.LoginActivity;
import com.ridge.digitalreceiptreader.common.abstracts.FragmentModule;
import com.ridge.digitalreceiptreader.service.jwt.JwtHolder;
import com.ridge.digitalreceiptreader.service.util.LocalStorageService;
import com.ridge.digitalreceiptreader.service.util.RouterService;

/**
 * Settings Module class to centralize methods being using in Settings fragment.
 *
 * @author Sam Butler
 * @since October 30, 2021
 */
public class SettingsFragmentModule extends FragmentModule<SettingsFragment> {
    private JwtHolder jwtHolder;
    private LocalStorageService localStorage;
    private RouterService router;

    private TextView name;
    private TextView accountNumber;
    private TextView email;
    private TextView webRole;

    /**
     * Sets default values for the class.
     *
     * @param f current fragment.
     */
    public SettingsFragmentModule(SettingsFragment f, View v) {
        super(f,v);
    }

    /**
     * Initialization the services being used.
     */
    public void initServices() {
        jwtHolder = new JwtHolder(activity);
        localStorage = new LocalStorageService(activity);
        router = new RouterService(activity);
    }

    /**
     * Initialization the elements being used.
     */
    public void initElements() {
        name = view.findViewById(R.id.settings__accountInfoContent_name__text);
        email = view.findViewById(R.id.settings__accountInfoContent_email__text);
        accountNumber = view.findViewById(R.id.settings__accountInfoContent_accountNumber__text);
        webRole = view.findViewById(R.id.settings__accountInfoContent_webRole_text);
    }

    /**
     * This will logout the user from the app. Which will remove the token from the
     * local storage and route them to login screen.
     */
    public void onLogOutClick() {
        localStorage.removeToken();
        Intent intent = new Intent(activity, LoginActivity.class);
        activity.startActivity(intent);
        router.navigate(LoginActivity.class);
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