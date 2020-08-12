package com.gun.app.config.security.exception;

import javax.security.sasl.AuthenticationException;

public class JwtExpTokenException extends AuthenticationException {
    public JwtExpTokenException(String msg) {
        super(msg);
    }
}