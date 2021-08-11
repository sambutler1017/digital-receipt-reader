package com.ridge.digitalreceiptreader.app.auth.domain;

import org.jetbrains.annotations.NotNull;

/**
 * Authentication request object.
 *
 * @author Seth Hancock
 * @since August 1, 2020
 */
public class AuthenticationRequest {
    @NotNull
    private String email;

    @NotNull
    private String password;

    public AuthenticationRequest(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
