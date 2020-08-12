package com.gun.app.config.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gun.app.config.security.filter.AsyncLoginFilter;
import com.gun.app.config.security.handler.AsyncLoginSuccessHandler;
import com.gun.app.config.security.handler.CommonFailHandler;
import com.gun.app.config.security.provider.AsyncLoginProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@RequiredArgsConstructor
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private static String AUTHENTICATION_HEADER_NAME = "Authorization";
    private static String AUTHENTICATION_LOGIN_URL = "/api/auth/login";

    private final ObjectMapper objectMapper;

    private final AsyncLoginProvider asyncLoginProvider;

    private final AsyncLoginSuccessHandler asyncLoginSuccessHandler;
    private final CommonFailHandler commonFailHandler;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .exceptionHandling()
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .addFilterBefore(getAsyncLoginFilter(), UsernamePasswordAuthenticationFilter.class);
    }
    private AsyncLoginFilter getAsyncLoginFilter() throws Exception {
        AsyncLoginFilter filter = new AsyncLoginFilter(AUTHENTICATION_LOGIN_URL, objectMapper, asyncLoginSuccessHandler, commonFailHandler);
        filter.setAuthenticationManager(this.authenticationManager());
        return filter;
    }
}