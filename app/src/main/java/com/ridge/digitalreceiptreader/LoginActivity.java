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
import com.ridge.digitalreceiptreader.service.NfcService;

import java.io.UnsupportedEncodingException;

/**
 * Login Activity class for handling functionality with the login screen.
 *
 * @author Sam Butler
 * @since July 28, 2021
 */
public class LoginActivity extends AppCompatActivity {
    private LoginService loginService;

    private NfcService nfcService;

    private Button loginButton;

    private TextView forgotPassword;

    private NfcAdapter adapter = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initialization();

        nfcService.initAdapter();
    }

    @Override
    protected void onResume() {
        super.onResume();
        nfcService.enableNfcForegroundDispatch();
    }

    @Override
    protected void onPause() {
        super.onPause();
        nfcService.disableNfcForegroundDispatch();
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        NdefMessage[] messages = nfcService.readTag(intent);
        Log.i("Nfc Tag", nfcService.buildTagView(messages[0]));
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
        loginService = new LoginService(this);
        nfcService = new NfcService(this);
    }
}