package com.ridge.digitalreceiptreader.app.receipt.service;

import com.ridge.digitalreceiptreader.app.receipt.domain.Receipt;
import com.ridge.digitalreceiptreader.app.receipt.domain.ReceiptGetRequest;
import com.ridge.digitalreceiptreader.common.api.ApiClient;

import org.springframework.http.ResponseEntity;

import android.app.Activity;

/**
 * Receipt Service which will be the middle tier between the application and the
 * api.
 *
 * @author Sam Butler,
 * @since July 31, 2021
 */
public class ReceiptService {
    private ApiClient apiClient;

    public ReceiptService(Activity act) {
        apiClient = new ApiClient(act, "/api/receipt-app/receipt");
    }

    /**
     * Get a user receipt for the given id.
     *
     * @param id The identifier of the receipt to get.
     * @return {@link ResponseEntity} of the receipt.
     */
    public ResponseEntity<Receipt> getUserReceiptById(int id) {
        return apiClient.get(String.format("/current-user/%d", id), Receipt.class);
    }

    /**
     * This will get a list of receipts that a user currently has associated to
     * their account. If they user has no receipts yet then it will return an empty
     * list.
     *
     * @return {@link Receipt[]}
     */
    public ResponseEntity<Receipt[]> getUserReceipts(ReceiptGetRequest r) {
        String params = "";
        String label = r.getLabel() != null ? r.getLabel().stream().findFirst().orElse("") : "";
        String location = r.getLocation() != null ? r.getLocation().stream().findFirst().orElse("") : "";
        String notes = r.getNotes() != null ?  r.getNotes().stream().findFirst().orElse("") : "";

        if(!label.equals("")) params += "label=" + label + "&";
        if(!location.equals("")) params += "location=" + location + "&";
        if(!notes.equals("")) params += "notes=" + notes + "&";
        params =  params.trim().equals("") ? "" : params.substring(0, params.length() - 1);
        return apiClient.get(String.format("/current-user%s%s", params.trim().equals("") ? "" : "?", params), Receipt[].class);
    }

    /**
     * Get the public id url path of the receipt. If the pid does not exist in the
     * S3 bucket then it will return an empty string.
     *
     * @param pid The public id of the receipt.
     * @return {@link String} path to receipt.
     */
    public ResponseEntity<String> getPublicIdUrlPath(String pid) {
        return apiClient.get(String.format("/url/%s", pid), String.class);
    }

    /**
     * This will associate the current user to the receipt id that is passed in.
     *
     * @param receiptId Id of the receipt to associate the user to.
     * @return {@link Receipt}
     */
    public ResponseEntity<Receipt> associateUserToReceipt(int receiptId) {
        return apiClient.post(String.format("/associate/%d", receiptId), null, Receipt.class);
    }

    /**
     * This will update information for a users association for the given receipt id
     * in in the request body.
     *
     * @param receipt The receipt to be updated.
     * @return {@link Receipt} of the updated receipt.
     */
    public ResponseEntity<Receipt> updateUserReceipt(Receipt receipt) {
        return apiClient.put("/associate", receipt, Receipt.class);
    }

    /**
     * Delete the receipt for the given id. It will first check to make sure that
     * the receipt belongs to that user. If it does not then it will throw an
     * exception. Otherwise it will continue through the process of removing the
     * receipt from the user and removing it from the S3 bucket.
     *
     * @param receiptId The id of the receipt that needs deleted.
     */
    public ResponseEntity deleteUserReceipt(int receiptId) {
        return apiClient.delete(String.format("/current-user/%d", receiptId));
    }
}
