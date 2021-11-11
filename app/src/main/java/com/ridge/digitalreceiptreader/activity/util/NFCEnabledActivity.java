package com.ridge.digitalreceiptreader.activity.util;

import android.content.Intent;
import android.nfc.NdefMessage;
import android.os.Bundle;
import android.util.Log;
import com.ridge.digitalreceiptreader.R;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.ridge.digitalreceiptreader.activity.util.module.NFCEnabledModule;
import com.ridge.digitalreceiptreader.ui.nfc.NFCFragment;

/**
 * Activity that is meant to be extended from so the child activity can
 * have NFC capabilities enabled.
 *
 * @author Sam Butler
 * @since October 18, 2021
 */
public abstract class NFCEnabledActivity extends AppCompatActivity {
    private NFCEnabledModule nfcEnabledModule;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        nfcEnabledModule = new NFCEnabledModule(this);
        nfcEnabledModule.initAdapter();
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
        if(getVisibleFragment() instanceof NFCFragment) {
            NdefMessage[] messages = nfcEnabledModule.readTag(intent);
            Log.i("Nfc Tag", "Receipt Id: " + nfcEnabledModule.parseMessage(messages[0]).getTransmittedId());
        }
    }

    /**
     * This will get the current displaying fragment in the activity.
     *
     * @return {@link Fragment} of the one being displayed.
     */
    private Fragment getVisibleFragment() {
        Fragment navHostFragment = getSupportFragmentManager().findFragmentById(R.id.nav_host_fragment);
        return navHostFragment.getChildFragmentManager().getFragments().get(0);
    }
}
