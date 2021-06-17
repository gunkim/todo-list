package com.gun.app.config.security.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gun.app.config.security.exception.AuthMethodNotSupportedException;
import com.gun.app.config.security.exception.JwtExpTokenException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@RequiredArgsConstructor
public class CommonFailHandler implements AuthenticationFailureHandler {
    private final ObjectMapper objectMapper;
    @Override
    public void onAuthenticationFailure(HttpServletRequest req, HttpServletResponse res, AuthenticationException e) throws IOException {
        res.setStatus(HttpStatus.UNAUTHORIZED.value());
        res.setContentType(MediaType.APPLICATION_JSON_VALUE);
        res.setCharacterEncoding("UTF-8");

        String msg = "인증 실패";
        if (e instanceof BadCredentialsException) {
            msg = "유효하지 않은 자격 증명";
        } else if (e instanceof AuthMethodNotSupportedException) {
            msg = "해당 요청으로 인한 로그인 미지원";
        } else if(e instanceof JwtExpTokenException){
            msg = "JWT 토큰 유효기간 만료";
        }
        objectMapper.writeValue(res.getWriter(), msg);
    }
}