package com.ridge.digitalreceiptreader.app.user.domain;

/**
 * Object used to update a users password. This will hold a current password and
 * new password fields.
 *
 * @author Sam Butler
 * @since October 29, 2021
 */
public class PasswordUpdate {

    private String currentPassword;

    private String newPassword;

    public PasswordUpdate() {
    }

    public PasswordUpdate(String currentPassword, String newPassword) {
        this.currentPassword = currentPassword;
        this.newPassword = newPassword;
    }

    public String getCurrentPassword() {
        return currentPassword;
    }

    public void setCurrentPassword(String currentPassword) {
        this.currentPassword = currentPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }
}
