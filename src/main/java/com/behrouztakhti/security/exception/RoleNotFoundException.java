package com.behrouztakhti.security.exception;

/**
 * RoleNotFoundException.
 * @author behrouz.takhti@gmail.com
 * @version 1.0-SNAPSHOT
 * @see GlobalExceptionHandler
 */
public class RoleNotFoundException extends RuntimeException{

    public RoleNotFoundException() {
    }

    public RoleNotFoundException(String message) {
        super(message);
    }

    public RoleNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
