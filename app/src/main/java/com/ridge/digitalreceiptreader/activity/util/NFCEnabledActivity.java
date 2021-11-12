package com.ridge.digitalreceiptreader.activity.util;

import android.content.Intent;
import android.nfc.NdefMessage;
import android.os.Bundle;
import android.util.Log;
import com.ridge.digitalreceiptreader.R;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

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
    private ToastService toastService;
    private boolean NFC_ENABLED = true;

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
        toastService = new ToastService(this);
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
        Fragment f = getVisibleFragment();
        if(NFC_ENABLED && f instanceof NFCFragment) {
            disableNFC((NFCFragment) f);
            NdefMessage[] messages = nfcEnabledModule.readTag(intent);
            Log.i("Nfc Tag", "Receipt Id: " + nfcEnabledModule.parseMessage(messages[0]).getTransmittedId());
            toastService.showSuccess("Receipt Scanned Successfully!");
            enableNFC((NFCFragment) f);
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

    /**
     * Disable the NFC so no tags can be read.
     */
    private void disableNFC(NFCFragment f) {
        NFC_ENABLED = false;
        f.stopScan();
    }

    /**
     * Enable the NFC so tags can be read.
     */
    private void enableNFC(NFCFragment f) {
        NFC_ENABLED = true;
        f.startScan();
    }
}
