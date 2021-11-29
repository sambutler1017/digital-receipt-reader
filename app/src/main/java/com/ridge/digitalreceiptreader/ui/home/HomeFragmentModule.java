package com.ridge.digitalreceiptreader.ui.home;

import android.view.View;

import com.ridge.digitalreceiptreader.activity.home.ReceiptDetailsActivity;
import com.ridge.digitalreceiptreader.activity.home.ReceiptDetailsEditActivity;
import com.ridge.digitalreceiptreader.app.receipt.client.ReceiptClient;
import com.ridge.digitalreceiptreader.app.receipt.domain.Receipt;
import com.ridge.digitalreceiptreader.common.abstracts.ActivityModule;
import com.ridge.digitalreceiptreader.common.abstracts.FragmentModule;
import com.ridge.digitalreceiptreader.service.util.RouterService;

import java.util.ArrayList;

/**
 * Home Module class to centralize methods being using in Home fragment.
 *
 * @author Sam Butler
 * @since October 30, 2021
 */
public class HomeFragmentModule extends FragmentModule<HomeFragment> {

    private ReceiptClient receiptClient;
    private RouterService routerService;
    private ArrayList<Receipt> receiptArrayList;

    /**
     * Sets default values for the class.
     *
     * @param f current fragment.
     */
    public HomeFragmentModule(HomeFragment f, View v) {
        super(f, v);
        initElements();
        initServices();
    }

    public void initElements() {
        receiptArrayList = new ArrayList<>();
    }

    public void initServices() {
        receiptClient = new ReceiptClient(activity);
        routerService = new RouterService(activity);
    }

    public ArrayList<Receipt> getReceiptList() {
        // Get list of receipts and put them in a list.
        receiptClient.getUserReceipts()
                .subscribe(res -> activity.runOnUiThread(() -> populateReceiptList(res.getBody())));
        return receiptArrayList;
    }

    public void openReceipt(int id) {
        routerService.navigate(ReceiptDetailsActivity.class, "receiptId", id);
    }

    private void populateReceiptList(Receipt[] receiptList) {
        for (Receipt r : receiptList) {
            receiptArrayList.add(r);
        }
    }
}