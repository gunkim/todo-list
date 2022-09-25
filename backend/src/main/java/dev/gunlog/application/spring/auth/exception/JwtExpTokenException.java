package dev.gunlog.application.spring.auth.exception;

import org.springframework.security.core.AuthenticationException;

public class JwtExpTokenException extends AuthenticationException {
    public JwtExpTokenException(String msg) {
        super(msg);
    }
}