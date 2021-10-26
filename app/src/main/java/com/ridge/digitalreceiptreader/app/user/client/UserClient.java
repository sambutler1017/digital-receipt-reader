package com.ridge.digitalreceiptreader.app.user.client;

import android.app.Activity;

import com.ridge.digitalreceiptreader.app.user.domain.User;
import com.ridge.digitalreceiptreader.app.user.domain.request.UserGetRequest;
import com.ridge.digitalreceiptreader.app.user.service.UserService;

import org.springframework.http.ResponseEntity;

import java.util.List;

import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.schedulers.Schedulers;

/**
 * User Client class to be used across the application.
 *
 * @author Sam Butler
 * @since July 31, 2021
 */
public class UserClient {
    private final UserService userService;

    public UserClient(Activity act) {
        userService = new UserService(act);
    }

    /**
     * Method that will get the current user that is logged in.
     *
     * @return {@link User} of the current user object.
     */
    public Single<ResponseEntity<User>> getCurrentUser() {
        Single<ResponseEntity<User>> observable = Single
                .create(s -> s.onSuccess(userService.getCurrentUser()));
        return observable.subscribeOn(Schedulers.newThread());
    }

    /**
     * Gets a list of users based of the request filter.
     *
     * @param request to filter on
     * @return list of user objects
     */
    public Single<ResponseEntity<User[]>> getUsers(UserGetRequest request) {
        Single<ResponseEntity<User[]>> observable = Single
                .create(s -> s.onSuccess(userService.getUsers(request)));
        return observable.subscribeOn(Schedulers.newThread());
    }
}
