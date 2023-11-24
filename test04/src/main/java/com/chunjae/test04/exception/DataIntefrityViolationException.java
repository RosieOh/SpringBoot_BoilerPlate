package com.chunjae.test04.exception;

public class DataIntefrityViolationException extends RuntimeException {
    public DataIntefrityViolationException(String message){
        super(message);
    }
}
