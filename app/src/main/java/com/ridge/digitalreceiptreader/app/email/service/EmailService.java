package com.ridge.digitalreceiptreader.app.email.service;

import android.app.Activity;

import com.ridge.digitalreceiptreader.app.user.domain.User;
import com.ridge.digitalreceiptreader.common.api.ApiClient;

import org.springframework.http.ResponseEntity;

/**
 * Email Service which will be the middle tier between
 * the application and the api.
 *
 * @author Sam Butler,
 * @since July 31, 2021
 */
public class EmailService {
    private ApiClient apiClient;

    public EmailService(Activity act) {
        apiClient = new ApiClient(act);
    }

    /**
     * Send forgot password link to the user if they exist for that email.
     *
     * @param email The email to search for in the database.
     * @return User object if the user exists for that email
     */
    public ResponseEntity<User> forgotPassword(String email) {
        return apiClient.put("api/mail-app/email/forgot-password", email, User.class);
    }
}
