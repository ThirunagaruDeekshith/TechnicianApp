package com.technicanApp.technicanApp.entity;

import com.technicanApp.technicanApp.CountryErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class TechnicianRestExceptionHandler {
    @ExceptionHandler
    public ResponseEntity<CountryErrorResponse> handleException(TechnicianNotFoundException exp){
        CountryErrorResponse err= new CountryErrorResponse();
        err.setStatus(404);
        err.setMessage(exp.getMessage());
        err.setTimestamp(System.currentTimeMillis());

        return new ResponseEntity<>(err, HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler
    public ResponseEntity<CountryErrorResponse> handleException(Exception exc){
        CountryErrorResponse err= new CountryErrorResponse();
        err.setStatus(HttpStatus.BAD_REQUEST.value());
        err.setMessage(exc.getMessage());
        err.setTimestamp(System.currentTimeMillis());
        return new ResponseEntity<>(err, HttpStatus.BAD_REQUEST);
    }
}
