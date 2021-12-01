package com.ridge.digitalreceiptreader.ui.settings;

import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Color;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.ridge.digitalreceiptreader.R;
import com.ridge.digitalreceiptreader.activity.home.MainActivity;
import com.ridge.digitalreceiptreader.activity.login.LoginActivity;
import com.ridge.digitalreceiptreader.app.user.client.UserClient;
import com.ridge.digitalreceiptreader.common.abstracts.FragmentModule;
import com.ridge.digitalreceiptreader.service.jwt.JwtHolder;
import com.ridge.digitalreceiptreader.service.util.LocalStorageService;
import com.ridge.digitalreceiptreader.service.util.RouterService;
import com.ridge.digitalreceiptreader.service.util.ToastService;

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
    private ToastService toastService;

    private UserClient userClient;

    private TextView name;
    private TextView accountNumber;
    private TextView email;
    private TextView webRole;
    private ProgressBar loader;

    /**
     * Sets default values for the class.
     *
     * @param f current fragment.
     */
    public SettingsFragmentModule(SettingsFragment f, View v) {
        super(f, v);
    }

    /**
     * Initialization the services being used.
     */
    public void initServices() {
        jwtHolder = new JwtHolder(activity);
        localStorage = new LocalStorageService(activity);
        router = new RouterService(activity);
        toastService = new ToastService(activity);
    }

    /**
     * Initialization the clients being used.
     */
    public void initClients() {
        userClient = new UserClient(activity);
    }

    /**
     * Initialization the elements being used.
     */
    public void initElements() {
        name = view.findViewById(R.id.settings__accountInfoContent_name__text);
        email = view.findViewById(R.id.settings__accountInfoContent_email__text);
        accountNumber = view.findViewById(R.id.settings__accountInfoContent_accountNumber__text);
        webRole = view.findViewById(R.id.settings__accountInfoContent_webRole_text);
        loader = view.findViewById(R.id.settings__loader__progressbar);
    }

    /**
     * This will logout the user from the app. Which will remove the token from the
     * local storage and route them to login screen.
     */
    public void onLogOutClick() {
        localStorage.removeToken();
        router.navigate(LoginActivity.class);
    }

    /**
     * This will delete the currently logged in user and their account.
     */
    public void onDeleteAccount() {
        TextView title = new TextView(activity);
        title.setText("Delete Account?");
        title.setPadding(20, 30, 20, 30);
        title.setTextSize(20F);
        title.setBackgroundColor(Color.parseColor("#E83345"));
        title.setTextColor(Color.WHITE);

        AlertDialog.Builder alert = new AlertDialog.Builder(activity);
        alert.setCustomTitle(title);
        alert.setMessage("This will delete all personal information on your account and all " +
                "receipts associated to your user. Are you sure you want to continue?");
        alert.setPositiveButton("Delete", (dialog, i) -> deleteAccount());
        alert.setNegativeButton("Cancel", null);
        alert.setCancelable(false);

        AlertDialog dialog = alert.create();
        dialog.setOnShowListener(
                arg0 -> dialog.getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(Color.parseColor("#E83345")));
        dialog.show();
    }

    /**
     * Delete the user account.
     */
    private void deleteAccount() {
        show(loader);
        userClient.deleteUserAccount().subscribe(res -> activity.runOnUiThread(() -> {
            localStorage.removeToken();
            hide(loader);
            toastService.showSuccess("Account Successfully Deleted!");
            router.navigate(LoginActivity.class);
        }));
    }

    /**
     * This will populate the account information for the settings page.
     */
    public void populateAccountInfo() {
        name.setText(jwtHolder.get("firstName") + " " + jwtHolder.get("lastName"));
        email.setText(jwtHolder.getRequiredEmail());
        accountNumber.setText(String.valueOf(jwtHolder.getRequiredUserId()));
        webRole.setText(jwtHolder.getWebRole().toString());
    }
}