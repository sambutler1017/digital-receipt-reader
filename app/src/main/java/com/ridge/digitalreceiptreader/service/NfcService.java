package com.ridge.digitalreceiptreader.service;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.nfc.NdefMessage;
import android.nfc.NfcAdapter;
import android.nfc.NfcManager;
import android.os.Parcelable;
import android.util.Log;

import java.io.UnsupportedEncodingException;

/**
 * Class for dealing with all initializations and readings from NFC.
 *
 * @author Sam Butler
 * @since September 10, 2021
 */
public class NfcService {
    private final Activity currentActivity;

    private NfcAdapter adapter = null;

    private ToastService toastService;

    /**
     * Sets default values for the class.
     *
     * @param a current activity.
     */
    public NfcService(Activity a) {
        currentActivity = a;
        toastService = new ToastService(currentActivity);
    }

    /**
     * This will initialize the nfc adapter in order to intercept any NFC tags being tapped to the phone.
     * If the adapter is null then the phone does not support nfc and a warning will display at the
     * top of the phone.
     */
    public void initAdapter() {
        NfcManager nfcManager = (NfcManager)currentActivity.getSystemService(Context.NFC_SERVICE);
        adapter = nfcManager.getDefaultAdapter();
        if(adapter == null) {
            toastService.showWarning("This device does not support NFC");
        }
    }

    /**
     * When the application starts and the processes are in the foreground. This will endable the dispatch 
     * of the NFC adapter so it can pick up the information from the tag.
     */
    public void enableNfcForegroundDispatch() {
        try {
            Intent intent = new Intent(currentActivity, currentActivity.getClass()).addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
            PendingIntent nfcPendingIntent = PendingIntent.getActivity(currentActivity, 0, intent, 0);
            adapter.enableForegroundDispatch(currentActivity, nfcPendingIntent, null, null);
        } catch (IllegalStateException ex) {
            Log.e("NFC", "Error enabling NFC foreground dispatch");
        }
    }

    /**
     * When the application is closed or clossing or we don't want to read NFC tags at this time then
     * we need to disable the adapter.
     */
    public void disableNfcForegroundDispatch() {
        try {
            adapter.disableForegroundDispatch(currentActivity);
        } catch (IllegalStateException ex) {
            Log.e("NFC", "Error disabling NFC foreground dispatch");
        }
    }

    /**
     * This will read the data from the tag and store it in an {@link NdefMessage} array
     * object. If nothing can be pulled from the tag, then the method will return null.
     *
     * @param intent The intent the tag was triggered on.
     * @return {@link NdefMessage} of the data read from the tag.
     */
    public NdefMessage[] readTag(Intent intent) {
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
     * Will build the tag view into a {@link String} object from the given {@link NdefMessage}
     * object.
     *
     * @param msg The message to get data from.
     * @return {@link String} of the data in the message.
     */
    public String buildTagView(NdefMessage msg) {
        if (msg == null) return "";

        String text = "";
        byte[] payload = msg.getRecords()[0].getPayload();
        String textEncoding = ((payload[0] & 128) == 0) ? "UTF-8" : "UTF-16";
        int languageCodeLength = payload[0] & 0063;

        try {
            text = new String(payload, languageCodeLength + 1, payload.length - languageCodeLength - 1, textEncoding);
        } catch (UnsupportedEncodingException e) {
            Log.e("UnsupportedEncoding", e.toString());
        }

        return text;
    }
}
