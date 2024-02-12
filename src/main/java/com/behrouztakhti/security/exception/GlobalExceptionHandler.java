package com.behrouztakhti.security.exception;

import com.behrouztakhti.security.dto.GenericResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * This class is responsible for handling exceptions across the whole application in one global handling component.
 * @author behrouz.takhti@gmail.com
 * @version 1.0-SNAPSHOT
 */
@ControllerAdvice
@Component
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    /**
     * This ExceptionHandler catch UserAlreadyHasAccountException exception and translate it to application special error response.
     * @param  exp UserAlreadyHasAccountException.
     * @param  webRequest WebRequest.
     */
    @ExceptionHandler({UserAlreadyHasAccountException.class})
    public ResponseEntity<Object> userAlreadyHasAccountException(UserAlreadyHasAccountException exp, WebRequest webRequest){
        return this.handleExp(exp, webRequest);
    }

    /**
     * This ExceptionHandler catch RoleNotFoundException exception and translate it to application special error response.
     * @param  exp RoleNotFoundException.
     * @param  webRequest WebRequest.
     */
    @ExceptionHandler({RoleNotFoundException.class})
    public ResponseEntity<Object> roleNotFoundException(RoleNotFoundException exp, WebRequest webRequest){
        return this.handleExp(exp, webRequest);
    }

    /**
     * This ExceptionHandler catch BadCredentialException exception and translate it to application special error response.
     * @param  exp BadCredentialException.
     * @param  webRequest WebRequest.
     */
    @ExceptionHandler({BadCredentialException.class})
    public ResponseEntity<Object> badCredentialException(BadCredentialException exp, WebRequest webRequest){
        return this.handleExp(exp, webRequest);
    }

    private ResponseEntity<Object> handleExp(Exception exp, WebRequest webRequest){
        var response = new GenericResponse(HttpStatus.BAD_REQUEST.value(), exp.getMessage());
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        return handleExceptionInternal(exp, response, httpHeaders, HttpStatus.BAD_REQUEST, webRequest);
    }

}
