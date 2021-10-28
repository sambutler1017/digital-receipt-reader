package com.ridge.digitalreceiptreader.ui.nfc;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.ridge.digitalreceiptreader.R;

public class NFCFragment extends Fragment {

    private NFCViewModel NFCViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        NFCViewModel =
                new ViewModelProvider(this).get(NFCViewModel.class);
        View root = inflater.inflate(R.layout.fragment_nfc, container, false);
        final TextView textView = root.findViewById(R.id.text_nfc);
        NFCViewModel.getText().observe(getViewLifecycleOwner(), s -> textView.setText(s));
        return root;
    }
}