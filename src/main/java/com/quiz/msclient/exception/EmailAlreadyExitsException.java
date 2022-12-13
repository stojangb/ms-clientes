package com.quiz.msclient.exception;

public class EmailAlreadyExitsException  extends RuntimeException {

    public EmailAlreadyExitsException(String message) {
        super(message);
    }
}
