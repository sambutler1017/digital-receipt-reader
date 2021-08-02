package com.ridge.digitalreceiptreader.common.exceptions;

/**
 * Invalid Credentials Exception for when user tries to authenticate
 *
 * @author Sam Butler
 * @since July 31, 2021
 */
public class InvalidCredentialsException extends Exception {
    public InvalidCredentialsException(String message) {
        super(message);
    }
}
