package com.ridge.digitalreceiptreader;

import androidx.appcompat.app.AppCompatActivity;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.nfc.NdefMessage;
import android.nfc.NfcAdapter;
import android.nfc.NfcManager;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import com.ridge.digitalreceiptreader.service.LoginService;

/**
 * Login Activity class for handling functionality with the login screen.
 *
 * @author Sam Butler
 * @since July 28, 2021
 */
public class LoginActivity extends AppCompatActivity {
    private LoginService loginService;

    private Button loginButton;

    private TextView forgotPassword;

    private NfcAdapter adapter = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initNfcAdapter();
        initialization();
    }

    @Override
    protected void onResume() {
        super.onResume();
        enableNfcForegroundDispatch();
    }

    @Override
    protected void onPause() {
        super.onPause();
        disableNfcForegroundDispatch();
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);

        if (NfcAdapter.ACTION_NDEF_DISCOVERED.equals(intent.getAction())) {
            Parcelable[] rawMessages = intent.getParcelableArrayExtra(NfcAdapter.EXTRA_NDEF_MESSAGES);
            if (rawMessages != null) {
                NdefMessage[] messages = new NdefMessage[rawMessages.length];
                for (int i = 0; i < rawMessages.length; i++) {
                    messages[i] = (NdefMessage) rawMessages[i];
                }
            }
        }
    }

    private void enableNfcForegroundDispatch() {
        try {
            Intent intent = new Intent(this, LoginActivity.class).addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
            PendingIntent nfcPendingIntent = PendingIntent.getActivity(this, 0, intent, 0);
            adapter.enableForegroundDispatch(this, nfcPendingIntent, null, null);
        } catch (IllegalStateException ex) {
            Log.e("NFC", "Error enabling NFC foreground dispatch");
        }
    }

    private void disableNfcForegroundDispatch() {
        try {
            adapter.disableForegroundDispatch(this);
        } catch (IllegalStateException ex) {
            Log.e("NFC", "Error disabling NFC foreground dispatch");
        }
    }

    private void initNfcAdapter() {
        NfcManager nfcManager = (NfcManager)getSystemService(Context.NFC_SERVICE);
        adapter = nfcManager.getDefaultAdapter();
    }

    /**
     * Starting initialization of the activity.
     */
    private void initialization() {
        initElements();
        initListeners();
        initServices();
    }

    /**
     * Initializes any elements that are being used in the activity.
     */
    private void initElements() {
        forgotPassword = findViewById(R.id.forgot_password_textView__login);
        loginButton = findViewById(R.id.login_button__login);
    }

    /**
     * Initializes any listeners that are being used in the activity.
     */
    private void initListeners() {
        forgotPassword.setOnClickListener(v -> loginService.onForgotPassword());
        loginButton.setOnClickListener(v -> loginService.onLogin());
    }

    /**
     * Initializes any services being used by the activity.
     */
    private void initServices() {
        loginService = new LoginService(LoginActivity.this);
    }
}