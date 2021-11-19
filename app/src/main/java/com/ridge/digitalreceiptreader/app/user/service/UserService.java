package com.ridge.digitalreceiptreader.app.user.service;

import android.app.Activity;

import com.ridge.digitalreceiptreader.app.user.domain.User;
import com.ridge.digitalreceiptreader.app.user.domain.request.UserGetRequest;
import com.ridge.digitalreceiptreader.common.api.ApiClient;

import org.springframework.http.ResponseEntity;

/**
 * User Service which will be the middle tier between
 * the application and the api.
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
     * This will update the given users information. User's can only
     * update their own information.
     *
     * @param user The user object to update too.
     * @return {@link ResponseEntity<User>} of the updated user.
     */
    public ResponseEntity<User> updateUser(User user) {
        return apiClient.put("", user, User.class);
    }
}
