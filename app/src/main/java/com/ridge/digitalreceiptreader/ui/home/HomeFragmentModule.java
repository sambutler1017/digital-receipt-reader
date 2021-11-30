package com.ridge.digitalreceiptreader.ui.home;

import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import java.util.Set;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.common.collect.Sets;
import com.ridge.digitalreceiptreader.R;
import com.ridge.digitalreceiptreader.activity.home.ReceiptDetailsActivity;
import com.ridge.digitalreceiptreader.activity.home.ReceiptDetailsEditActivity;
import com.ridge.digitalreceiptreader.app.receipt.client.ReceiptClient;
import com.ridge.digitalreceiptreader.app.receipt.domain.Receipt;
import com.ridge.digitalreceiptreader.app.receipt.domain.ReceiptGetRequest;
import com.ridge.digitalreceiptreader.common.abstracts.ActivityModule;
import com.ridge.digitalreceiptreader.common.abstracts.FragmentModule;
import com.ridge.digitalreceiptreader.service.util.RouterService;
import com.ridge.digitalreceiptreader.ui.home.adaptar.ReceiptListAdaptar;

import java.util.ArrayList;

/**
 * Home Module class to centralize methods being using in Home fragment.
 *
 * @author Sam Butler
 * @since October 30, 2021
 */
public class HomeFragmentModule extends FragmentModule<HomeFragment> implements ReceiptListAdaptar.ItemClickListener {
    private ReceiptListAdaptar adapter;
    private ReceiptClient receiptClient;
    private RouterService routerService;
    private ProgressBar loadingIndicator;
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
        loadingIndicator = view.findViewById(R.id.loading_indicator__fragment_home);
    }

    public void initServices() {
        receiptClient = new ReceiptClient(activity);
        routerService = new RouterService(activity);
    }

    public void getReceiptList(String search) {
        // Get list of receipts and put them in a list.
        clearReceipts();
        show(loadingIndicator);
        receiptClient.getUserReceipts(
                search.trim().equals("") ? new ReceiptGetRequest() : new ReceiptGetRequest(Sets.newHashSet(search), Sets.newHashSet(search), Sets.newHashSet(search)))
                .subscribe(res -> activity.runOnUiThread(() ->
                        populateReceiptList(res.getBody())));
    }

    public void openReceipt(int id) {
        routerService.navigate(ReceiptDetailsActivity.class, "receiptId", id);
    }

    private void populateReceiptList(Receipt[] receiptList) {
        for (Receipt r : receiptList) {
            receiptArrayList.add(r);
        }

        buildReceiptList();
        hide(loadingIndicator);
    }

    private void buildReceiptList() {
        // Set up the RecyclerView
        RecyclerView recyclerView = view.findViewById(R.id.receipt_list__fragment_home);
        recyclerView.setLayoutManager(new LinearLayoutManager(activity));
        adapter = new ReceiptListAdaptar(activity, receiptArrayList);
        adapter.setClickListener(this);
        recyclerView.setAdapter(adapter);
        view.invalidate();
    }

    private void clearReceipts() {
        receiptArrayList.removeAll(receiptArrayList);
        buildReceiptList();
        view.invalidate();
    }

    @Override
    public void onItemClick(View view, int position) {
        openReceipt(adapter.getItem(position).getId());
    }
}