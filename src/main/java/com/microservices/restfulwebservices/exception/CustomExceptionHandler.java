package com.microservices.restfulwebservices.exception;

import java.util.Date;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestController
@ControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {
@ExceptionHandler(Exception.class)
    private final ResponseEntity<Object> handleAllExceptions(Exception exp, WebRequest request) {
        ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), exp.getMessage(),
            request.getDescription(false));
        return new ResponseEntity<Object>(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(UsernameNotFoundException.class)
    private final ResponseEntity<Object> handleUserNotFoundException(UsernameNotFoundException exception, WebRequest webRequest) {
        ExceptionResponse expResponse = new ExceptionResponse(new Date(), exception.getMessage(), webRequest.getDescription(false));
        return new ResponseEntity<Object>(expResponse, HttpStatus.NOT_FOUND);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException exception, HttpHeaders headers, HttpStatus status, WebRequest req) {
        ExceptionResponse eResponse = new ExceptionResponse(new Date(), "Validation failed", exception.getBindingResult().toString());
        return new ResponseEntity<Object>(eResponse, HttpStatus.BAD_REQUEST);
    }
}
