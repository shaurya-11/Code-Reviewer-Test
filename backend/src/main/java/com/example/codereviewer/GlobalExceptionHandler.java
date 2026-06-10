package com.example.codereviewer;

import java.util.Map;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, String>> handle(Exception exception) {
        return ResponseEntity.internalServerError().body(Map.of("message", exception.getMessage()));
    }
}

