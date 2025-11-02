package com.fitness.baseservice.exceptions;

import java.util.List;
import java.util.stream.Collectors;
import java.util.Collections;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleValidationExceptions(MethodArgumentNotValidException ex) {
    List<String> messages = ex.getBindingResult().getAllErrors().stream()
        .map(error -> error.getDefaultMessage())
        .collect(Collectors.toList());
    return new ErrorResponse(400, "Bad Request", messages);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleIllegalArgumentException(IllegalArgumentException ex) {
    return new ErrorResponse(400, "Bad Request", List.of(ex.getMessage()));
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorResponse handleAllExceptions(Exception ex) {
        return new ErrorResponse(
            500,
            "Internal Server Error",
            ex.getMessage() != null ? List.of(ex.getMessage()) : Collections.singletonList("Unexpected error")
        );
    }

}
