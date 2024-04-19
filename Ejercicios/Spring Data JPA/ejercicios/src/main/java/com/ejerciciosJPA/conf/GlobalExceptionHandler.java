package com.ejerciciosJPA.conf;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<String> handleResponseStatusEx(ResponseStatusException ex){
        HttpStatusCode status = ex.getStatusCode();
        String message = ex.getReason();
        if (status == HttpStatus.NOT_FOUND){
            message = "No se encuentra la ruta solicitada";
        }
        else if (status == HttpStatus.NO_CONTENT){
            message = "No hay coincidencias con la consulta";
        }
        return new ResponseEntity<>(message,new HttpHeaders(),status);
    }
}
