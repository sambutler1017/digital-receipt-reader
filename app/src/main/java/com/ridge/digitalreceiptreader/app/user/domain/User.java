package com.ridge.digitalreceiptreader.app.user.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ridge.digitalreceiptreader.common.enums.WebRole;

import java.util.Date;

/**
 * Class to create a user profile object
 *
 * @author Sam Butler
 * @since June 25, 2021
 */
public class User {
    @JsonProperty("id")
    private int id;

    @JsonProperty("firstName")
    private String firstName;

    @JsonProperty("lastName")
    private String lastName;

    @JsonProperty("email")
    private String email;

    @JsonProperty("webRole")
    private WebRole webRole;

    @JsonProperty("password")
    private String password;

    @JsonProperty("forgotPassword")
    private boolean forgotPassword;

    @JsonProperty("insertDate")
    private Date insertDate;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public WebRole getWebRole() {
        return webRole;
    }

    public void setWebRole(WebRole webRole) {
        this.webRole = webRole;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isForgotPassword() {
        return forgotPassword;
    }

    public void setForgotPassword(boolean forgotPassword) {
        this.forgotPassword = forgotPassword;
    }

    public Date getInsertDate() {
        return insertDate;
    }

    public void setInsertDate(Date insertDate) {
        this.insertDate = insertDate;
    }
}
