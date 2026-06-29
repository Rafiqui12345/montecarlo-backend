package com.montecarlo.exception;

import com.montecarlo.dto.ErrorResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ErrorResponseDTO> manejarRuntime(RuntimeException ex){

        ErrorResponseDTO error = ErrorResponseDTO.builder()
                .fecha(LocalDateTime.now())
                .status(HttpStatus.BAD_REQUEST.value())
                .mensaje(ex.getMessage())
                .build();

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(error);

    }


}