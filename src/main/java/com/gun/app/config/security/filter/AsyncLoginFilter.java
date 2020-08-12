package com.gun.app.config.security.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gun.app.config.security.LoginDto;
import com.gun.app.config.security.exception.AuthMethodNotSupportedException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
public class AsyncLoginFilter extends AbstractAuthenticationProcessingFilter {
    private final AuthenticationSuccessHandler successHandler;
    private final AuthenticationFailureHandler failHandler;
    private final ObjectMapper objectMapper;
    public AsyncLoginFilter(String defaultFilterProcessesUrl, ObjectMapper objectMapper, AuthenticationSuccessHandler successHandler, AuthenticationFailureHandler failHandler) {
        super(defaultFilterProcessesUrl);
        this.objectMapper = objectMapper;
        this.successHandler = successHandler;
        this.failHandler = failHandler;
    }
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException, IOException, ServletException {
//        if(this.isNotPostMethod(request) || this.isNotAsync(request)){
//            log.debug("비동기 로그인 처리 지원이 되지 않는 메소드 요청입니다. :: "+request.getMethod());
//            throw new AuthMethodNotSupportedException("Authentication method not supported");
//        }
        LoginDto loginDto = objectMapper.readValue(request.getReader(), LoginDto.class);

        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(loginDto.getUsername(), loginDto.getPassword());
        return this.getAuthenticationManager().authenticate(token);
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        successHandler.onAuthenticationSuccess(request, response, authResult);
    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException, ServletException {
        failHandler.onAuthenticationFailure(request, response, failed);
    }
    /**
     * 비동기 요청이 아닌 지 확인
     * @param request
     * @return
     */
    private boolean isNotAsync(HttpServletRequest request) {
        return !"XMLHttpRequest".equals(request.getHeader("X-Requested-With"));
    }

    /**
     * POST 요청이 아닌 지 확인
     * @param request
     * @return
     */
    private boolean isNotPostMethod(HttpServletRequest request){
        return !HttpMethod.POST.name().equals(request.getMethod());
    }
}