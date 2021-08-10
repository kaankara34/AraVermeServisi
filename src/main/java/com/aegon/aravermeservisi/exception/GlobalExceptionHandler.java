package com.aegon.aravermeservisi.exception;

import com.aegon.cnf.support.rest.ResponseBuilder;
import com.aegon.cnf.support.rest.dto.ServiceResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<ServiceResponse<String>> handleAnyExceptions(Exception ex, WebRequest req){
        return ResponseBuilder.warning(null, ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }


}
