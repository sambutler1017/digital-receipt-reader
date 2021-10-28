package com.ridge.digitalreceiptreader.ui.nfc;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class NFCViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public NFCViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("NFC Scanner Fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}