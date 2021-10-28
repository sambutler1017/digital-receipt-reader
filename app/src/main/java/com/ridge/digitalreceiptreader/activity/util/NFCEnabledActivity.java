package com.ridge.digitalreceiptreader.activity.util;

import android.content.Intent;
import android.nfc.NdefMessage;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.ridge.digitalreceiptreader.activity.util.module.NfcModule;

/**
 * Activity that is meant to be extended from so the child activity can
 * have NFC capabilities enabled.
 *
 * @author Sam Butler
 * @author Luke Lengel
 * @since October 18, 2021
 */
public abstract class NFCEnabledActivity extends AppCompatActivity {
    private NfcModule nfcModule;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onResume() {
        super.onResume();
        nfcModule.enableNfcForegroundDispatch();
    }

    @Override
    protected void onPause() {
        super.onPause();
        nfcModule.disableNfcForegroundDispatch();
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        NdefMessage[] messages = nfcModule.readTag(intent);
        Log.i("Nfc Tag", "Receipt Id: " + nfcModule.parseMessage(messages[0]).getTransmittedId());
    }
}
