package com.anhnhv.unit.server.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(RuntimeException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<Map<String, String>> handleRuntimeException(RuntimeException ex) {
        return new ResponseEntity<>(Map.of("message", ex.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(BadCredentialsException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<Map<String, String>> handleBadCredentialsException(BadCredentialsException ex) {
        return new ResponseEntity<>(Map.of("message", "Invalid Username or Password"), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(RefreshTokenException.class)
    public ResponseEntity<Map<String, String>> handleRefreshTokenException(RefreshTokenException ex) {
        return new ResponseEntity<>(Map.of("message", ex.getMessage()), HttpStatus.FORBIDDEN);
    }
}
