package com.quiz.msclient.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionControllerAdvice {

    @ExceptionHandler({Throwable.class})
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<ErrorMessage> handleRuntimeException(Throwable ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorMsg(HttpStatus.BAD_REQUEST, ex));
    }

    @ExceptionHandler({InvalidEmailExecption.class})
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ResponseEntity<ErrorMessage> handleInvalidEmailExecption(InvalidEmailExecption ex) {

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMsg(HttpStatus.BAD_REQUEST, ex));
    }

    @ExceptionHandler({InvalidPasswordException.class})
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ResponseEntity<ErrorMessage> handleInvalidPasswordExceptionn(InvalidPasswordException ex) {

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMsg(HttpStatus.BAD_REQUEST, ex));
    }

    private ErrorMessage errorMsg(HttpStatus status, Throwable ex){

        return new ErrorMessage(ex.getMessage());

    }
}
