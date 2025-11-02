package com.fitness.baseservice.exceptions;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ErrorResponse {

    private int statusCode;
    private String errorType;
    private List<String> errorMessage;

}
