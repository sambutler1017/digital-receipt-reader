package com.ridge.digitalreceiptreader.app.user.client;

import android.app.Activity;

import com.ridge.digitalreceiptreader.app.user.domain.PasswordUpdate;
import com.ridge.digitalreceiptreader.app.user.domain.User;
import com.ridge.digitalreceiptreader.app.user.domain.request.UserGetRequest;
import com.ridge.digitalreceiptreader.app.user.service.UserService;

import org.springframework.http.ResponseEntity;

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
        Single<ResponseEntity<User>> observable = Single.create(s -> s.onSuccess(userService.getCurrentUser()));
        return observable.subscribeOn(Schedulers.io());
    }

    /**
     * Gets a list of users based of the request filter.
     *
     * @param request to filter on
     * @return list of user objects
     */
    public Single<ResponseEntity<User[]>> getUsers(UserGetRequest request) {
        Single<ResponseEntity<User[]>> observable = Single.create(s -> s.onSuccess(userService.getUsers(request)));
        return observable.subscribeOn(Schedulers.io());
    }

    /**
     * This will check to see if the email that is passed in already has an account associated to it.
     *
     * @param email Email to check if it is already in the database.
     * @return {@link Boolean} of the status of the email.
     */
    public Single<ResponseEntity<Boolean>> doesEmailExist(String email) {
        Single<ResponseEntity<Boolean>> observable = Single.create(s -> s.onSuccess(userService.doesEmailExist(email)));
        return observable.subscribeOn(Schedulers.io());
    }

    /**
     * This will create a new user account.
     *
     * @param user The user object to update too.
     * @return {@link ResponseEntity<User>} of the updated user.
     */
    public Single<ResponseEntity<User>> createUser(User user) {
        Single<ResponseEntity<User>> observable = Single.create(s -> s.onSuccess(userService.createUser(user)));
        return observable.subscribeOn(Schedulers.io());
    }

    /**
     * This gets called when a user forgets their password. This will check to see
     * if the passed in email exists as a user, if it does then the user will get an
     * email to reset their password.
     *
     * @return user associated to that id with the updated information
     */
    public Single<ResponseEntity<User>> forgotPassword(String email) {
        Single<ResponseEntity<User>> observable = Single.create(s -> s.onSuccess(userService.forgotPassword(email)));
        return observable.subscribeOn(Schedulers.io());
    }

    /**
     * This will update the given users information. User's can only update their
     * own information.
     *
     * @param user The user object to update too.
     * @return {@link ResponseEntity<User>} of the updated user.
     */
    public Single<ResponseEntity<User>> updateUser(User user) {
        Single<ResponseEntity<User>> observable = Single.create(s -> s.onSuccess(userService.updateUser(user)));
        return observable.subscribeOn(Schedulers.io());
    }

    /**
     * This will take in a {@link PasswordUpdate} object that will confirm that the
     * current password matches the database password. If it does then it will
     * update the password to the new password.
     *
     * @param passUpdate Object the holds the current password and new user password
     *                   to change it too.
     * @return {@link User} object of the user that was updated.
     */
    public Single<ResponseEntity<User>> updateUserPassword(PasswordUpdate passUpdate) {
        Single<ResponseEntity<User>> observable = Single.create(s -> s.onSuccess(userService.updateUserPassword(passUpdate)));
        return observable.subscribeOn(Schedulers.io());
    }

    /**
     * This will get called when a user has forgotten their password. This will
     * allow them to reset it.
     *
     * @param newPassword The new password to reset the current users account too.
     * @return {@link User} object of the user that was updated.
     */
    public Single<ResponseEntity<User>> resetUserPassword(String newPassword, String token) {
        Single<ResponseEntity<User>> observable = Single.create(s -> s.onSuccess(userService.resetUserPassword(new PasswordUpdate("", newPassword), token)));
        return observable.subscribeOn(Schedulers.io());
    }

    /**
     * This will allow a user to delete their account. This will delete all their
     * receipts, personal information, and login credentials.
     */
    public Single<ResponseEntity> deleteUserAccount() {
        Single<ResponseEntity> observable = Single.create(s -> s.onSuccess(userService.deleteUserAccount()));
        return observable.subscribeOn(Schedulers.io());
    }
}
