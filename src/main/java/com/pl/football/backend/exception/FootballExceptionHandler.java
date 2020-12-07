package com.pl.football.backend.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class FootballExceptionHandler {
    @ExceptionHandler(FootballException.class)
    public ResponseEntity<?> handelFootballException(FootballException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getErrMsg());
    }
}
