package com.ridge.digitalreceiptreader.activity.home.module;

import com.ridge.digitalreceiptreader.activity.home.ReceiptDetailsEditActivity;
import com.ridge.digitalreceiptreader.app.receipt.client.ReceiptClient;
import com.ridge.digitalreceiptreader.common.abstracts.BaseModule;
import com.ridge.digitalreceiptreader.service.util.RouterService;
import com.ridge.digitalreceiptreader.service.util.ToastService;

/**
 * Module class for handling the behind the scenes work of
 * the receipt details edit page.
 *
 * @author Sam Butler
 * @since November 24
 */
public class ReceiptDetailsEditModule extends BaseModule<ReceiptDetailsEditActivity> {
    private ReceiptClient receiptClient;
    private RouterService router;
    private ToastService toastService;

    /**
     * Sets default values for the class.
     *
     * @param a current activity.
     */
    public ReceiptDetailsEditModule(ReceiptDetailsEditActivity a) {
        super(a);
    }

    /**
     * Initializes any elements that are being used in the activity.
     */
    public void initElements() {
    }

    /**
     * Initialization for clients.
     */
    public void initClients() {
        receiptClient = new ReceiptClient(appContext);
    }

    /**
     * Initialization for the services.
     */
    public void initServices() {
        router = new RouterService(appContext);
        toastService = new ToastService(appContext);
    }
}
