package com.gun.app.config.security.exception;

import javax.security.sasl.AuthenticationException;

public class AuthMethodNotSupportedException extends AuthenticationException {
    public AuthMethodNotSupportedException(String msg) {
        super(msg);
    }
}