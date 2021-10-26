package com.ridge.digitalreceiptreader.app.auth.client;

import android.app.Activity;

import com.ridge.digitalreceiptreader.app.auth.domain.AuthenticationRequest;
import com.ridge.digitalreceiptreader.app.auth.domain.DigitalReceiptToken;
import com.ridge.digitalreceiptreader.app.auth.service.AuthService;

import org.springframework.http.ResponseEntity;

import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.schedulers.Schedulers;

/**
 * Authentication Client class to be used accross the application.
 *
 * @author Sam Butler
 * @since July 31, 2021
 */
public class AuthClient {
    private AuthService authService;

    public AuthClient(Activity act) {
        authService = new AuthService(act);
    }

    /**
     * Will authenticate a user for the given username and password.
     *
     * @param username The username to search for in the database
     * @param password The password with the corresponding user.
     * @return Auth token if the user credentials are correct.
     */
    public Single<ResponseEntity<DigitalReceiptToken>> authenticate(String username, String password) {
        Single<ResponseEntity<DigitalReceiptToken>> observable = Single
                .create(s -> s.onSuccess(authService.authenticate(username, password)));
        return observable.subscribeOn(Schedulers.newThread());
    }

    /**
     * Will authenticate a user for the given AuthenticationRequest.
     *
     * @param authRequest The auth request with the given username and password
     * @return Auth token if the user credentials are correct.
     */
    public Single<ResponseEntity<DigitalReceiptToken>> authenticate(AuthenticationRequest authRequest) {
        Single<ResponseEntity<DigitalReceiptToken>> observable = Single.create(
                s -> s.onSuccess(authService.authenticate(authRequest.getEmail(), authRequest.getPassword())));
        return observable.subscribeOn(Schedulers.newThread());
    }
}
