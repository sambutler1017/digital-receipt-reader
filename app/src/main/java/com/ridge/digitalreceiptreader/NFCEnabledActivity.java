package com.ridge.digitalreceiptreader;

import android.content.Intent;
import android.nfc.NdefMessage;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.ridge.digitalreceiptreader.service.NfcService;

/**
 * Activity that is meant to be extended from so the child activity can
 * have NFC capabilities enabled.
 *
 * @author Sam Butler
 * @since September 13, 2021
 */
public class NFCEnabledActivity extends AppCompatActivity {
    private NfcService nfcService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        nfcService = new NfcService(this);
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
}
