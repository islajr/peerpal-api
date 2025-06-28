package org.project.peerpalapi.exceptions.handler;

import org.project.peerpalapi.exceptions.ErrorResponse;
import org.project.peerpalapi.exceptions.auth.AuthException;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = AuthException.class)
    public ResponseEntity<ErrorResponse> handleAuthException(AuthException e) {
        return ResponseEntity.status(HttpStatusCode.valueOf(e.getStatusCode())).body(new ErrorResponse(e.getMessage()));
    }


}
