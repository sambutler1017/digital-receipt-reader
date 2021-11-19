package com.ridge.digitalreceiptreader.activity.util.module;

import android.app.AlertDialog;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.nfc.NdefMessage;
import android.nfc.NfcAdapter;
import android.nfc.NfcManager;
import android.os.Parcelable;
import android.util.Log;

import androidx.fragment.app.Fragment;

import com.ridge.digitalreceiptreader.activity.util.NFCEnabledActivity;
import com.ridge.digitalreceiptreader.app.receipt.client.ReceiptClient;
import com.ridge.digitalreceiptreader.common.abstracts.ActivityModule;
import com.ridge.digitalreceiptreader.common.domain.NfcData;
import com.ridge.digitalreceiptreader.service.jwt.JwtHolder;
import com.ridge.digitalreceiptreader.service.util.LocalStorageService;
import com.ridge.digitalreceiptreader.service.util.ToastService;
import com.ridge.digitalreceiptreader.ui.nfc.NFCFragment;

import java.util.Date;

/**
 * Class for dealing with all initializations and readings from NFC.
 *
 * @author Sam Butler
 * @author Luke Lengel
 * @since October 18, 2021
 */
public class NFCEnabledActivityModule extends ActivityModule<NFCEnabledActivity> {
    private boolean NFC_ENABLED = true;

    private NfcAdapter adapter = null;
    private LocalStorageService localStorage;
    private ToastService toastService;
    private JwtHolder jwtHolder;
    private ReceiptClient receiptClient;

    /**
     * Sets default values for the class.
     *
     * @param a current activity.
     */
    public NFCEnabledActivityModule(NFCEnabledActivity a) {
        super(a);
    }

    /**
     * Initializes any services being used by the activity.
     */
    public void initServices() {
        jwtHolder = new JwtHolder(appContext);
        localStorage = new LocalStorageService(appContext);
        toastService = new ToastService(appContext);
    }

    public void initClients() {
        receiptClient = new ReceiptClient(appContext);
    }

    /**
     * This will initialize the nfc adapter in order to intercept any NFC tags being
     * tapped to the phone. If the adapter is null then the phone does not support
     * nfc and a warning will display at the top of the phone.
     */
    public void initAdapter() {
        NfcManager nfcManager = (NfcManager) appContext.getSystemService(Context.NFC_SERVICE);
        adapter = nfcManager.getDefaultAdapter();
        if (adapter == null && !localStorage.getBoolean("nfcNotSupportedFlag")) {
            localStorage.setBoolean("nfcNotSupportedFlag", true);
            AlertDialog.Builder alert = new AlertDialog.Builder(appContext);
            alert.setTitle("Warning!");
            alert.setMessage(
                    "This device does not support NFC. You will not be able to scan in receipts. Do you want to continue?");
            alert.setPositiveButton("Continue", null);
            alert.setNegativeButton("Close", (dialog, id) -> appContext.finish());
            alert.setCancelable(false);
            alert.create().show();
        }
    }

    /**
     * When the application starts and the processes are in the foreground. This
     * will enable the dispatch of the NFC adapter so it can pick up the information
     * from the tag.
     */
    public void enableNfcForegroundDispatch() {
        if (adapter == null) {
            return;
        }

        try {
            Intent intent = new Intent(appContext, appContext.getClass()).addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
            PendingIntent nfcPendingIntent = PendingIntent.getActivity(appContext, 0, intent, 0);
            adapter.enableForegroundDispatch(appContext, nfcPendingIntent, null, null);
        } catch (IllegalStateException ex) {
            Log.e("NFC", "Error enabling NFC foreground dispatch");
        }
    }

    /**
     * When the application is closed or closing or we don't want to read NFC tags
     * at this time then we need to disable the adapter.
     */
    public void disableNfcForegroundDispatch() {
        if (adapter == null) {
            return;
        }

        try {
            adapter.disableForegroundDispatch(appContext);
        } catch (IllegalStateException ex) {
            Log.e("NFC", "Error disabling NFC foreground dispatch");
        }
    }

    /**
     * Method for when a tag is detected. If NFC is enabled then it will not run
     * this method or if the fragment is not of type {@link NFCFragment}. If the
     * other two conditions hold then it pop a dialog to associate the receipt to
     * the user if the user wishes too.
     *
     * @param f The fragment currently visible to the user.
     * @param i The intent the tag created.
     */
    public void tagDetected(Fragment f, Intent i) {
        if (NFC_ENABLED && f instanceof NFCFragment) {
            NFCFragment NFCFrag = (NFCFragment) f;
            disableNFC(NFCFrag);
            NdefMessage[] messages = readTag(i);
            parseMessage(messages[0]);
            receiptClient.associateUserToReceipt(parseMessage(messages[0]).getTransmittedId()).subscribe(
                    res -> appContext.runOnUiThread(() -> NFCFrag.routeToReceiptDetails(this, res.getBody())));
        }
    }

    /**
     * Disable the NFC so no tags can be read.
     */
    public void disableNFC(NFCFragment f) {
        NFC_ENABLED = false;
        f.stopScan();
    }

    /**
     * Enable the NFC so tags can be read.
     */
    public void enableNFC(NFCFragment f) {
        NFC_ENABLED = true;
        f.startScan();
    }

    /**
     * This will read the data from the tag and store it in an {@link NdefMessage}
     * array object. If nothing can be pulled from the tag, then the method will
     * return null.
     *
     * @param intent The intent the tag was triggered on.
     * @return {@link NdefMessage} of the data read from the tag.
     */
    private NdefMessage[] readTag(Intent intent) {
        if (adapter == null) {
            return null;
        }

        if (NfcAdapter.ACTION_NDEF_DISCOVERED.equals(intent.getAction())) {
            Parcelable[] rawMessages = intent.getParcelableArrayExtra(NfcAdapter.EXTRA_NDEF_MESSAGES);
            if (rawMessages != null) {
                NdefMessage[] messages = new NdefMessage[rawMessages.length];
                for (int i = 0; i < rawMessages.length; i++) {
                    messages[i] = (NdefMessage) rawMessages[i];
                }
                return messages;
            }
        }
        return null;
    }

    /**
     * Get the id from the nfc tag.
     *
     * @param msg The message to get data from.
     * @return {@link Integer} of the data in the message.
     */
    private NfcData parseMessage(NdefMessage msg) {
        if (adapter == null) {
            return null;
        }
        if (msg == null)
            return null;

        byte[] payload = msg.getRecords()[0].getPayload();
        int byte1 = (payload[0] << 24) & 0xFF000000;
        int byte2 = (payload[1] << 16) & 0x00FF0000;
        int byte3 = (payload[2] << 8) & 0x0000FF00;
        int byte4 = payload[3] & 0x000000FF;

        return new NfcData(byte1 | byte2 | byte3 | byte4, jwtHolder.getRequiredUserId(), new Date());
    }
}
