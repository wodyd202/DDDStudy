package com.ljy.dddstudy.services.core.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class CommonExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<String>> error(MethodArgumentNotValidException e) {
        return ResponseEntity.badRequest().body(e.getAllErrors().stream()
                        .map(ObjectError::getDefaultMessage)
                .collect(Collectors.toList()));
    }
}
