package com.ridge.digitalreceiptreader.app.receipt.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import java.sql.Date;


/**
 * Receipt object to map the receipt data to.
 *
 * @author Sam butler
 * @since November 1, 2021
 */
public class Receipt {
    private int id;

    @JsonInclude(Include.NON_DEFAULT)
    private int userId;

    private String location;

    private String label;

    private String filePublicId;

    private String url;

    private Date insertDate;

    public Receipt() {
    }

    public Receipt(String filePublicId, Date insertDate) {
        this.filePublicId = filePublicId;
        this.insertDate = insertDate;
    }

    public Receipt(int id, String filePublicId, Date insertDate) {
        this.id = id;
        this.filePublicId = filePublicId;
        this.insertDate = insertDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getFilePublicId() {
        return filePublicId;
    }

    public void setFilePublicId(String filePublicId) {
        this.filePublicId = filePublicId;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Date getInsertDate() {
        return insertDate;
    }

    public void setInsertDate(Date insertDate) {
        this.insertDate = insertDate;
    }
}
