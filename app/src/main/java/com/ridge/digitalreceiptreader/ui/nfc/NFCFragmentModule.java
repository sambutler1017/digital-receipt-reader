package com.ridge.digitalreceiptreader.ui.nfc;

import android.app.AlertDialog;
import android.view.View;

import com.ridge.digitalreceiptreader.activity.util.module.NFCEnabledActivityModule;
import com.ridge.digitalreceiptreader.common.abstracts.FragmentModule;
import com.ridge.digitalreceiptreader.service.util.LocalStorageService;
import com.ridge.digitalreceiptreader.service.util.ToastService;

/**
 * NFC Module class to centralize methods being using in NFC fragment.
 *
 * @author Sam Butler
 * @since October 30, 2021
 */
public class NFCFragmentModule extends FragmentModule<NFCFragment> {


    private LocalStorageService localStorage;
    private ToastService toastService;

    /**
     * Sets default values for the class.
     *
     * @param f current fragment.
     */
    public NFCFragmentModule(NFCFragment f, View v) {
        super(f,v);
    }

    /**
     * Initialization for common service classes being used.
     */
    public void initServices() {
        localStorage = new LocalStorageService(activity);
        toastService = new ToastService(activity);
    }

    /**
     * This will check to see if the phone is able to read in NFC tags
     * if not then it will disable the scan and show a toast message
     * saying that NFC is not supported on this device.
     *
     * @param f The NFC fragment.
     */
    public void hasNFCSupport(NFCFragment f) {
        if(localStorage.getBoolean("nfcNotSupportedFlag")) {
            f.stopScan();
            toastService.showError("NFC not Supported!");
        }
    }

    /**
     * Shows the save receipt dialog so that a user is able to save the receipt in their
     * account.
     */
    public void showDialog(NFCEnabledActivityModule m) {
        AlertDialog.Builder alert = new AlertDialog.Builder(activity);
        alert.setTitle("Save Receipt");
        alert.setMessage("Inputs Where user will set label and location for the receipt");
        alert.setPositiveButton("Save", (dialog, id) -> saveReceipt(m));
        alert.setNegativeButton("Cancel", (dialog, id) -> m.enableNFC(appContext));
        alert.setCancelable(false);
        alert.create().show();
    }

    /**
     * Method that will go through the process of saving a receipt to a user.
     */
    public void saveReceipt(NFCEnabledActivityModule m) {
        m.enableNFC(appContext);
        toastService.showSuccess("Receipt Saved Successfully!");
    }
}