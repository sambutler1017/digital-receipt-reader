package com.ridge.digitalreceiptreader.app.user.service;

import android.app.Activity;

import com.ridge.digitalreceiptreader.app.user.domain.PasswordUpdate;
import com.ridge.digitalreceiptreader.app.user.domain.User;
import com.ridge.digitalreceiptreader.app.user.domain.request.UserGetRequest;
import com.ridge.digitalreceiptreader.common.api.ApiClient;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.schedulers.Schedulers;

/**
 * User Service which will be the middle tier between the application and the
 * api.
 *
 * @author Sam Butler,
 * @since July 31, 2021
 */
public class UserService {
    private final ApiClient apiClient;

    public UserService(Activity act) {
        apiClient = new ApiClient(act, "/api/user-app/users");
    }

    /**
     * Method that will get the current user that is logged in.
     *
     * @return {@link User} of the current user object.
     */
    public ResponseEntity<User> getCurrentUser() {
        return apiClient.get("/current-user", User.class);
    }

    /**
     * Gets a list of users based of the request filter.
     *
     * @param request to filter on
     * @return list of user objects
     */
    public ResponseEntity<User[]> getUsers(UserGetRequest request) {
        return apiClient.get(request.getUrlParamPath(), User[].class);
    }

    /**
     * This will check to see if the email that is passed in already has an account associated to it.
     *
     * @param email Email to check if it is already in the database.
     * @return {@link Boolean} of the status of the email.
     */
    public ResponseEntity<Boolean> doesEmailExist(String email) {
        if(email == null || email.trim().equals("")) return new ResponseEntity(true, HttpStatus.OK);
        return apiClient.get(String.format("/check-email?email=%s", email), Boolean.class);
    }

    /**
     * This will create a new user account.
     *
     * @param user The user object to update too.
     * @return {@link ResponseEntity<User>} of the updated user.
     */
    public ResponseEntity<User> createUser(User user) {
        return apiClient.post("", user, User.class);
    }

    /**
     * This gets called when a user forgets their password. This will check to see
     * if the passed in email exists as a user, if it does then the user will get an
     * email to reset their password.
     *
     * @return user associated to that id with the updated information
     */
    public ResponseEntity<User> forgotPassword(String email) {
        return apiClient.post("/forgot-password", email, User.class);
    }

    /**
     * This will update the given users information. User's can only update their
     * own information.
     *
     * @param user The user object to update too.
     * @return {@link ResponseEntity<User>} of the updated user.
     */
    public ResponseEntity<User> updateUser(User user) {
        return apiClient.put("", user, User.class);
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
    public ResponseEntity<User> updateUserPassword(PasswordUpdate passUpdate) {
        return apiClient.put("/password", passUpdate, User.class);
    }

    /**
     * This will get called when a user has forgotten their password. This will
     * allow them to reset it.
     *
     * @param passUpdate Object the holds the new password to change it too.
     * @return {@link User} object of the user that was updated.
     */
    public ResponseEntity<User> resetUserPassword(PasswordUpdate passUpdate, String token) {
        return apiClient.put("/password/reset", passUpdate, User.class, token);
    }

    /**
     * This will allow a user to delete their account. This will delete all their
     * receipts, personal information, and login credentials.
     */
    public ResponseEntity deleteUserAccount() {
        return apiClient.delete("");
    }
}
