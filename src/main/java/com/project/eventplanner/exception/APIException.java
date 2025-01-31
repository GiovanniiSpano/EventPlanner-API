package com.project.eventplanner.exception;

public class APIException extends RuntimeException {

    public APIException() {

    }

    public APIException(String message) {
        super(message);
    }
}
