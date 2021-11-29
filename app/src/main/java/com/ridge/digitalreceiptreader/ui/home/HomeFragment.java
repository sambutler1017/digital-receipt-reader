package com.ridge.digitalreceiptreader.ui.home;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ridge.digitalreceiptreader.R;
import com.ridge.digitalreceiptreader.app.receipt.client.ReceiptClient;
import com.ridge.digitalreceiptreader.common.abstracts.BaseFragment;
import com.ridge.digitalreceiptreader.app.receipt.domain.Receipt;
import com.ridge.digitalreceiptreader.service.util.RouterService;
import com.ridge.digitalreceiptreader.ui.home.adaptar.ReceiptListAdaptar;

import java.util.ArrayList;

/**
 * Home fragment used to display the home page in the main activity.
 *
 * @author Sam Butler
 * @since October 30, 2021
 */
public class HomeFragment extends BaseFragment implements ReceiptListAdaptar.ItemClickListener{
    private HomeFragmentModule homeFragmentModule;
    private Activity a;
    private ReceiptListAdaptar adapter;
    private ReceiptClient receiptClient;
    private RouterService router;

    /**
     * This will create the view for the fragment from the given layout and the view
     * group.
     *
     * @param i  The layout to place the view.
     * @param c  The group the view is contained in
     * @param sI The bundle to use.
     * @return {@link View} data
     */
    public View onCreateView(@NonNull LayoutInflater i, ViewGroup c, Bundle sI) {
        initialization(i, c, R.layout.fragment_home);
        a = getActivity();
        initServices();

        ArrayList<Receipt> receiptArrayList = homeFragmentModule.getReceiptList();

        // This is a test list; this should be removed once the database calls
        // are confirmed to work.
        //ArrayList<Receipt> receiptArrayList = new ArrayList<>();
        //Receipt r1 = new Receipt(28,"location1", "label1", "note1");
        //Receipt r2 = new Receipt(29,"location2", "label2", "note2");
        //receiptArrayList.add(r1);
        //receiptArrayList.add(r2);

        // Inflate receipt list
        View view = i.inflate(R.layout.fragment_home, c, false);

        // Set up the RecyclerView
        RecyclerView recyclerView = view.findViewById(R.id.receipt_list__fragment_home);
        recyclerView.setLayoutManager(new LinearLayoutManager(a));
        adapter = new ReceiptListAdaptar(a, receiptArrayList);
        adapter.setClickListener(this);
        recyclerView.setAdapter(adapter);

        return view;
    }

    /**
     * Initializes any services being used by the activity.
     */
    public void initServices() {
        homeFragmentModule = new HomeFragmentModule(this, view);
        receiptClient = new ReceiptClient(a);
        router = new RouterService(a);
    }

    @Override
    public void onItemClick(View view, int position) {
        //Toast.makeText(a, "You clicked " + adapter.getItem(position) + " on row number " + position, Toast.LENGTH_SHORT).show();
        homeFragmentModule.openReceipt(adapter.getItem(position).getId());
    }
}