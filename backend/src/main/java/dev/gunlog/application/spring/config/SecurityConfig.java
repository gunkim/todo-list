package dev.gunlog.application.spring.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import dev.gunlog.application.spring.auth.util.JwtUtil;
import dev.gunlog.domain.member.Role;
import dev.gunlog.application.spring.auth.filter.AsyncLoginFilter;
import dev.gunlog.application.spring.auth.filter.JwtAuthFilter;
import dev.gunlog.application.spring.auth.handler.AsyncLoginSuccessHandler;
import dev.gunlog.application.spring.auth.handler.CommonFailHandler;
import dev.gunlog.application.spring.auth.provider.AsyncLoginProvider;
import dev.gunlog.application.spring.auth.provider.JwtAuthProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    public static String AUTHENTICATION_HEADER_NAME = "Authorization";
    private static String AUTHENTICATION_LOGIN_URL = "/api/auth/login";

    private final ObjectMapper objectMapper;
    private final JwtUtil jwtUtil;

    private final AsyncLoginProvider asyncLoginProvider;
    private final JwtAuthProvider jwtAuthProvider;

    private final AsyncLoginSuccessHandler asyncLoginSuccessHandler;
    private final CommonFailHandler commonFailHandler;

    public SecurityConfig(ObjectMapper objectMapper, JwtUtil jwtUtil, AsyncLoginProvider asyncLoginProvider,
        JwtAuthProvider jwtAuthProvider, AsyncLoginSuccessHandler asyncLoginSuccessHandler, CommonFailHandler commonFailHandler) {
        this.objectMapper = objectMapper;
        this.jwtUtil = jwtUtil;
        this.asyncLoginProvider = asyncLoginProvider;
        this.jwtAuthProvider = jwtAuthProvider;
        this.asyncLoginSuccessHandler = asyncLoginSuccessHandler;
        this.commonFailHandler = commonFailHandler;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable().exceptionHandling().and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
            .authorizeRequests().antMatchers("/api/todo/**").hasRole(Role.USER.name()).antMatchers("/", "/list").permitAll().and()
            .addFilterBefore(getAsyncLoginFilter(), UsernamePasswordAuthenticationFilter.class)
            .addFilterBefore(getJwtAuthFilter(), UsernamePasswordAuthenticationFilter.class);
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(asyncLoginProvider);
        auth.authenticationProvider(jwtAuthProvider);
    }

    private AsyncLoginFilter getAsyncLoginFilter() throws Exception {
        AsyncLoginFilter filter = new AsyncLoginFilter(AUTHENTICATION_LOGIN_URL, objectMapper, asyncLoginSuccessHandler, commonFailHandler);
        filter.setAuthenticationManager(this.authenticationManager());
        return filter;
    }

    private JwtAuthFilter getJwtAuthFilter() throws Exception {
        JwtAuthFilter filter = new JwtAuthFilter(jwtUtil, commonFailHandler);
        filter.setAuthenticationManager(this.authenticationManager());
        return filter;
    }
}