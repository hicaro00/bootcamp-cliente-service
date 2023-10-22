package com.lizana.customermicroservice.application.customexeption;

public class CustomException extends RuntimeException {

    public CustomException(String message) {
        super(message);
    }
}
