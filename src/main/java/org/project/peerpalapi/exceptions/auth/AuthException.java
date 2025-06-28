package org.project.peerpalapi.exceptions.auth;

import lombok.Getter;

public class AuthException extends RuntimeException {

    @Getter
    int statusCode;
    String message;

    public AuthException(int statusCode, String message) {
        this.statusCode = statusCode;
        this.message = message;
    }

}
