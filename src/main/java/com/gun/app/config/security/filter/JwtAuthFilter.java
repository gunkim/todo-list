package com.gun.app.config.security.filter;

import com.gun.app.config.security.model.JwtAuthToken;
import com.gun.app.config.security.SecurityConfig;
import com.gun.app.config.security.util.JwtUtil;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
public class JwtAuthFilter extends AbstractAuthenticationProcessingFilter {
    private final JwtUtil jwtUtil;
    private final AuthenticationFailureHandler failureHandler;
    public JwtAuthFilter(JwtUtil jwtUtil, AuthenticationFailureHandler failureHandler) {
        super("/**");
        this.jwtUtil = jwtUtil;
        this.failureHandler = failureHandler;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException, IOException, ServletException {
        String token = request.getHeader(SecurityConfig.AUTHENTICATION_HEADER_NAME);
        Jws<Claims> claimsJws = jwtUtil.parserToken(token);
        return getAuthenticationManager().authenticate(new JwtAuthToken(claimsJws));
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        log.info("성공");
        SecurityContext context = SecurityContextHolder.createEmptyContext();
        context.setAuthentication(authResult);

        SecurityContextHolder.setContext(context);
        chain.doFilter(request, response);
    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException, ServletException {
        log.info("실패");
        SecurityContextHolder.clearContext();
        failureHandler.onAuthenticationFailure(request, response, failed);
    }
}