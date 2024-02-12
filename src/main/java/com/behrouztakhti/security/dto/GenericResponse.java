package com.behrouztakhti.security.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import java.util.Calendar;


/**
 * This is a generic response of application.
 * @author behrouz.takhti@gmail.com
 * @version 1.0-SNAPSHOT
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class GenericResponse<T> {

    private int code;
    private String status;
    private String message;
    private T responseBody;

    private String time;

    public GenericResponse() {
    }

    public GenericResponse(int code, String message) {
        this.code = code;
        this.message = message;
        this.time = Calendar.getInstance().getTimeInMillis() + "";
    }
    public GenericResponse(int code, String status, String message) {
        this.code = code;
        this.status = status;
        this.message = message;
        this.time = Calendar.getInstance().getTimeInMillis() + "";
    }
    public GenericResponse(int code, String status, String message, T responseBody) {
        this.code = code;
        this.status = status;
        this.message = message;
        this.responseBody = responseBody;
        this.time = Calendar.getInstance().getTimeInMillis() + "";
    }



    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getResponseBody() {
        return responseBody;
    }

    public void setResponseBody(T responseBody) {
        this.responseBody = responseBody;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
