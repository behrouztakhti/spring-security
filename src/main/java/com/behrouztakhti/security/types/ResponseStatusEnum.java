package com.behrouztakhti.security.types;

/**
 * This enum is used for determine the response code.
 * @author behrouz.takhti@gmail.com
 * @version 1.0-SNAPSHOT
 */
public enum ResponseStatusEnum {

    FAILED((byte) 0, "Operation failed !"),
    SUCCESS((byte) 1, "Operation succeeded !");

    private byte value;
    private String message;

    private ResponseStatusEnum(byte value, String message){
        this.value = value;
        this.message = message;
    }

    public byte toValue(){
        return this.value;
    }
    public String toMessage(){
        return this.message;
    }
}
