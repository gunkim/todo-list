package dev.gunlog.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import dev.gunlog.security.filter.AsyncLoginFilter;
import dev.gunlog.security.filter.JwtAuthFilter;
import dev.gunlog.security.handler.AsyncLoginSuccessHandler;
import dev.gunlog.security.handler.CommonFailHandler;
import dev.gunlog.security.provider.AsyncLoginProvider;
import dev.gunlog.security.provider.JwtAuthProvider;
import dev.gunlog.security.util.JwtUtil;
import dev.gunlog.domain.enums.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@RequiredArgsConstructor
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

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .exceptionHandling()
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .antMatchers("/api/todo/**").hasRole(Role.USER.name())
                .antMatchers("/", "/list").permitAll()
                .and()
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