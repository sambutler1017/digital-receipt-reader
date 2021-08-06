package com.ridge.digitalreceiptreader.app.email.client;

import com.ridge.digitalreceiptreader.app.email.service.EmailService;
import com.ridge.digitalreceiptreader.app.user.domain.User;

import org.springframework.http.ResponseEntity;

import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class EmailClient {
    private EmailService emailService;

    public EmailClient() {
        emailService = new EmailService();
    }

    /**
     * Send forgot password link to the user if they exist for that email.
     *
     * @param email The email to search for in the database.
     * @return User object if the user exists for that email
     */
    public Single<ResponseEntity<User>> forgotPassword(String email) {
        Single<ResponseEntity<User>> observable = Single
                .create(s -> s.onSuccess(emailService.forgotPassword(email)));
        return observable.subscribeOn(Schedulers.newThread());
    }
}
