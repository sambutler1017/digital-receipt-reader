package com.ridge.digitalreceiptreader.common.domain;

import java.util.Date;

/**
 * Object used when storing data read from an nfc tag.
 *
 * @author Sam Butler
 * @since September 14, 2021
 */
public class NfcData {
    private long transmittedId;

    private int associatedUserId;

    private Date dateRead;

    public NfcData() {}

    public NfcData(long transmittedId, int userId, Date dateRead) {
        this.transmittedId = transmittedId;
        this.associatedUserId = userId;
        this.dateRead = dateRead;
    }

    public long getTransmittedId() {
        return transmittedId;
    }

    public void setTransmittedId(long transmittedId) {
        this.transmittedId = transmittedId;
    }

    public int getAssociatedUserId() {
        return associatedUserId;
    }

    public void setAssociatedUserId(int associatedUserId) {
        this.associatedUserId = associatedUserId;
    }

    public Date getDateRead() {
        return dateRead;
    }

    public void setDateRead(Date dateRead) {
        this.dateRead = dateRead;
    }
}
