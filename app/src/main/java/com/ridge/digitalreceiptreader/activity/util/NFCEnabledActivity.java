package com.ridge.digitalreceiptreader.activity.util;

import android.content.Intent;
import android.nfc.NdefMessage;
import android.os.Bundle;
import android.util.Log;
import com.ridge.digitalreceiptreader.R;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.ridge.digitalreceiptreader.activity.util.module.NFCEnabledModule;
import com.ridge.digitalreceiptreader.activity.util.module.StartModule;
import com.ridge.digitalreceiptreader.common.abstracts.BaseActivity;
import com.ridge.digitalreceiptreader.service.util.ToastService;
import com.ridge.digitalreceiptreader.ui.nfc.NFCFragment;

/**
 * Activity that is meant to be extended from so the child activity can
 * have NFC capabilities enabled.
 *
 * @author Sam Butler
 * @since October 18, 2021
 */
public abstract class NFCEnabledActivity extends BaseActivity {
    private NFCEnabledModule nfcEnabledModule;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initialization();
        nfcEnabledModule.initAdapter();
    }

    /**
     * Initializes any services being used by the activity.
     */
    public void initServices() {
        nfcEnabledModule = new NFCEnabledModule(this);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onResume() {
        super.onResume();
        nfcEnabledModule.enableNfcForegroundDispatch();
    }

    @Override
    protected void onPause() {
        super.onPause();
        nfcEnabledModule.disableNfcForegroundDispatch();
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        FragmentManager fm = getSupportFragmentManager();
        nfcEnabledModule.tagDetected(getVisibleFragment(), intent);
    }
}
