package com.ridge.digitalreceiptreader.app.auth.client;

import com.ridge.digitalreceiptreader.app.auth.domain.AuthenticationRequest;
import com.ridge.digitalreceiptreader.app.auth.domain.DigitalReceiptToken;
import com.ridge.digitalreceiptreader.app.auth.service.AuthService;

import org.springframework.http.ResponseEntity;

/**
 * Authentication Client class to be used accross the application.
 *
 * @author Sam Butler
 * @since July 31, 2021
 */
public class AuthClient {
    private AuthService AuthService;

    public AuthClient() {
        AuthService = new AuthService();
    }

    /**
     * Will authenticate a user for the given username and password.
     *
     * @param username The username to search for in the database
     * @param password The password with the corresponding user.
     * @return Auth token if the user credentials are correct.
     */
    public ResponseEntity<DigitalReceiptToken> authenticate(String username, String password) {
        return AuthService.authenticate(username, password);
    }

    /**
     * Will authenticate a user for the given AuthenticationRequest.
     *
     * @param authRequest The auth request with the given username and password
     * @return Auth token if the user credentials are correct.
     */
    public ResponseEntity<DigitalReceiptToken> authenticate(AuthenticationRequest authRequest) {
        return AuthService.authenticate(authRequest.getUsername(), authRequest.getPassword());
    }
}
