package com.ridge.digitalreceiptreader.app.auth.service;

import com.ridge.digitalreceiptreader.app.auth.domain.AuthenticationRequest;
import com.ridge.digitalreceiptreader.app.auth.domain.DigitalReceiptToken;
import com.ridge.digitalreceiptreader.common.api.ApiClient;

import org.springframework.http.ResponseEntity;

/**
 * Authentication Service which will be the middle tier between
 * the application and the api.
 *
 * @author Sam Butler,
 * @since July 31, 2021
 */
public class AuthService {
    private ApiClient apiClient;

    public AuthService() {
        apiClient = new ApiClient();
    }

    /**
     * Will authenticate a user for the given username and password.
     *
     * @param username The username to search for in the database
     * @param password The password with the corresponding user.
     * @return Auth token if the user credentials are correct.
     */
    public ResponseEntity<DigitalReceiptToken> authenticate(String username, String password) {
        return apiClient.post("authenticate", new AuthenticationRequest(username, password), DigitalReceiptToken.class);
    }
}
