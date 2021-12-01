package com.ridge.digitalreceiptreader.app.receipt.domain;

import java.util.Set;

/**
 * Request object used to filter out receipts on a get call.
 *
 * @author Luke Lengel
 * @since November 12, 2021
 */
public class ReceiptGetRequest {
    private Set<Integer> id;

    private Set<Integer> userId;

    private Set<String> location;

    private Set<String> label;

    private Set<String> notes;

    public ReceiptGetRequest() {}

    public ReceiptGetRequest(Set<String> location, Set<String> label, Set<String> notes) {
        this.location = location;
        this.label = label;
        this.notes = notes;
    }

    public Set<Integer> getId() {
        return id;
    }

    public void setId(Set<Integer> id) {
        this.id = id;
    }

    public Set<Integer> getUserId() {
        return userId;
    }

    public void setUserId(Set<Integer> userId) {
        this.userId = userId;
    }

    public Set<String> getLocation() {
        return location;
    }

    public void setLocation(Set<String> location) {
        this.location = location;
    }

    public Set<String> getLabel() {
        return label;
    }

    public void setLabel(Set<String> label) {
        this.label = label;
    }

    public Set<String> getNotes() {
        return notes;
    }

    public void setNotes(Set<String> notes) {
        this.notes = notes;
    }
}