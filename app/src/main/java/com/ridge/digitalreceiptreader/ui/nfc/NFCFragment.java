package com.ridge.digitalreceiptreader.ui.nfc;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;

import com.ridge.digitalreceiptreader.R;
import com.ridge.digitalreceiptreader.common.abstracts.BaseFragment;
import com.skyfishjy.library.RippleBackground;

/**
 * NFC fragment used to display the NFC page in the main
 * activity.
 *
 * @author Sam Butler
 * @since October 30, 2021
 */
public class NFCFragment extends BaseFragment {
    private NFCModule nfcModule;

    /**
     * This will create the view for the fragment from the given layout and the
     * view group.
     *
     * @param i The layout to place the view.
     * @param c The group the view is contained in
     * @param sI The bundle to use.
     * @return {@link View} data
     */
    public View onCreateView(@NonNull LayoutInflater i, ViewGroup c, Bundle sI) {
        initialization(i, c, R.layout.fragment_nfc);
        return view;
    }

    /**
     * Initializes any services being used by the activity.
     */
    public void initServices() {
        nfcModule = new NFCModule(view, getActivity());
    }

    /**
     * Initializes any elements being used by the activity.
     */
    public void initElements() {
        final RippleBackground rippleBackground = view.findViewById(R.id.content);
        rippleBackground.startRippleAnimation();
    }
}