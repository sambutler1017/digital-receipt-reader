package com.ridge.digitalreceiptreader.activity.util;

import android.content.Intent;
import android.nfc.NdefMessage;
import android.os.Bundle;
import android.util.Log;
import com.ridge.digitalreceiptreader.R;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.ridge.digitalreceiptreader.activity.util.module.NFCService;
import com.ridge.digitalreceiptreader.ui.nfc.NFCFragment;

import java.util.List;

/**
 * Activity that is meant to be extended from so the child activity can
 * have NFC capabilities enabled.
 *
 * @author Sam Butler
 * @author Luke Lengel
 * @since October 18, 2021
 */
public abstract class NFCEnabledActivity extends AppCompatActivity {
    private NFCService nfcService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        nfcService = new NFCService(this);
        nfcService.initAdapter();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
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
        Fragment f = getSupportFragmentManager().findFragmentById(R.id.navigation_nfc);
        if(getVisibleFragment() instanceof NFCFragment) {
            NdefMessage[] messages = nfcService.readTag(intent);
            Log.i("Nfc Tag", "Receipt Id: " + nfcService.parseMessage(messages[0]).getTransmittedId());
        }
    }

    /**
     * This will get the current displaying fragment in the activity.
     *
     * @return {@link Fragment} of the one being displayed.
     */
    private Fragment getVisibleFragment() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        List<Fragment> fragments = fragmentManager.getFragments();
        if(fragments != null){
            for(Fragment fragment : fragments){
                if(fragment != null && fragment.isVisible())
                    return fragment;
            }
        }
        return null;
    }
}
