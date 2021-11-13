package com.ridge.digitalreceiptreader.activity.util;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.FragmentManager;

import com.ridge.digitalreceiptreader.activity.util.module.NFCEnabledActivityModule;
import com.ridge.digitalreceiptreader.common.abstracts.BaseActivity;

/**
 * Activity that is meant to be extended from so the child activity can
 * have NFC capabilities enabled.
 *
 * @author Sam Butler
 * @since October 18, 2021
 */
public abstract class NFCEnabledActivity extends BaseActivity {
    private NFCEnabledActivityModule nfcEnabledActivityModule;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initialization();
        nfcEnabledActivityModule.initAdapter();
    }

    /**
     * Initializes any services being used by the activity.
     */
    public void initServices() {
        nfcEnabledActivityModule = new NFCEnabledActivityModule(this);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onResume() {
        super.onResume();
        nfcEnabledActivityModule.enableNfcForegroundDispatch();
    }

    @Override
    protected void onPause() {
        super.onPause();
        nfcEnabledActivityModule.disableNfcForegroundDispatch();
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        FragmentManager fm = getSupportFragmentManager();
        nfcEnabledActivityModule.tagDetected(getVisibleFragment(), intent);
    }
}
