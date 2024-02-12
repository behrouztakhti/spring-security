package com.behrouztakhti.security.exception;

/**
 * This is a type of RuntimeException that used in a situation that User already has account then they can not register again.
 * @author behrouz.takhti@gmail.com
 * @version 1.0-SNAPSHOT
 * @see GlobalExceptionHandler
 */
public class UserAlreadyHasAccountException extends RuntimeException{

    public UserAlreadyHasAccountException() {
    }

    public UserAlreadyHasAccountException(String message) {
        super(message);
    }

    public UserAlreadyHasAccountException(String message, Throwable cause) {
        super(message, cause);
    }
}
