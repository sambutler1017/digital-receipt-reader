package com.ridge.digitalreceiptreader.app.auth.domain;

import com.ridge.digitalreceiptreader.common.enums.Environment;

import java.util.Date;

/**
 * Authentication token to be used within the app.
 *
 * @author Sam Butler
 * @since July 31, 2021
 */
public class DigitalReceiptToken {
    private String token;

    private Environment environment;

    private Date createDate;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Environment getEnvironment() {
        return environment;
    }

    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
}
