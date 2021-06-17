package dev.gunlog.config.security.exception;

import org.springframework.security.core.AuthenticationException;

public class AuthMethodNotSupportedException extends AuthenticationException {
    public AuthMethodNotSupportedException(String msg) {
        super(msg);
    }
}