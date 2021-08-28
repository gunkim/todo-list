package dev.gunlog.security.exception;

import org.springframework.security.core.AuthenticationException;

public class JwtExpTokenException extends AuthenticationException {
    public JwtExpTokenException(String msg) {
        super(msg);
    }
}