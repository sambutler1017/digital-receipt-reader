package com.ridge.digitalreceiptreader.app.receipt.client;

import android.app.Activity;

import com.ridge.digitalreceiptreader.app.receipt.domain.Receipt;
import com.ridge.digitalreceiptreader.app.receipt.service.ReceiptService;

import org.springframework.http.ResponseEntity;

import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.schedulers.Schedulers;

/**
 * Receipt Client class to be used across the application.
 *
 * @author Sam Butler
 * @since July 31, 2021
 */
public class ReceiptClient {
    private ReceiptService service;

    public ReceiptClient(Activity act) {
        service = new ReceiptService(act);
    }

    /**
     * Get a user receipt for the given id.
     *
     * @param id The identifier of the receipt to get.
     * @return {@link Single} observable of the receipt.
     */
    public Single<ResponseEntity<Receipt>> getUserReceiptById(int id) {
        Single<ResponseEntity<Receipt>> observable = Single.create(s -> s.onSuccess(service.getUserReceiptById(id)));
        return observable.subscribeOn(Schedulers.io());
    }

    /**
     * This will get a list of receipts that a user currently has associated to
     * their account. If they user has no receipts yet then it will return an empty
     * list.
     *
     * @return {@link Receipt[]}
     */
    public Single<ResponseEntity<Receipt[]>> getUserReceipts() {
        Single<ResponseEntity<Receipt[]>> observable = Single.create(s -> s.onSuccess(service.getUserReceipts()));
        return observable.subscribeOn(Schedulers.io());
    }

    /**
     * Get the public id url path of the receipt. If the pid does not exist in the
     * S3 bucket then it will return an empty string.
     *
     * @param pid The public id of the receipt.
     * @return {@link String} path to receipt.
     */
    public Single<ResponseEntity<String>> getPublicIdUrlPath(String pid) {
        Single<ResponseEntity<String>> observable = Single.create(s -> s.onSuccess(service.getPublicIdUrlPath(pid)));
        return observable.subscribeOn(Schedulers.io());
    }

    /**
     * This will associate the current user to the receipt id that is passed in.
     *
     * @param receiptId Id of the receipt to associate the user to.
     * @return {@link Receipt}
     */
    public Single<ResponseEntity<Receipt>> associateUserToReceipt(int receiptId) {
        Single<ResponseEntity<Receipt>> observable = Single
                .create(s -> s.onSuccess(service.associateUserToReceipt(receiptId)));
        return observable.subscribeOn(Schedulers.io());
    }
}
