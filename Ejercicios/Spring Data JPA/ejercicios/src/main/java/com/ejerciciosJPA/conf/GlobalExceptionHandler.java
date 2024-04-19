package com.ejerciciosJPA.conf;

import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.server.ResponseStatusException;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<String> handleResponseStatusEx(ResponseStatusException ex){
        HttpStatusCode status = ex.getStatusCode();
        return ResponseEntity.status(status).body("Hola");
    }
}
