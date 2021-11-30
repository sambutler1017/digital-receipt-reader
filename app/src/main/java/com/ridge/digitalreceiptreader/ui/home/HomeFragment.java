package com.ridge.digitalreceiptreader.ui.home;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

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
    private EditText searchBar;

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

        homeFragmentModule.getReceiptList("");
        return view;
    }

    /**
     * Initializes any services being used by the activity.
     */
    public void initServices() {
        homeFragmentModule = new HomeFragmentModule(this, view);
        receiptClient = new ReceiptClient(a);
        router = new RouterService(a);
        searchBar = view.findViewById(R.id.search_bar__fragment_home);
    }

    public void initListeners() {
        searchBar.setOnEditorActionListener((v, actionId, event) -> {
            InputMethodManager imm = (InputMethodManager)getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
            homeFragmentModule.getReceiptList(v.getText().toString());
            return false;
        });
    }

    @Override
    public void onItemClick(View view, int position) {
        homeFragmentModule.openReceipt(adapter.getItem(position).getId());
    }
}