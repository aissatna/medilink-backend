package com.aissatna.medilinkbackend.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.ErrorResponseException;

import java.time.Instant;

public class ResourceNotFoundException extends ErrorResponseException {
    public ResourceNotFoundException(String message) {
        super(HttpStatus.NOT_FOUND, asProblemDetail(message), null);

    }
    private static ProblemDetail asProblemDetail(String message) {
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, message);
        problemDetail.setTitle("Data not found");
        problemDetail.setProperty("timestamp", Instant.now());
        return problemDetail;
    }
}
