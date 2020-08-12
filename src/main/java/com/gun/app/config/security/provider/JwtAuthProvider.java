package com.gun.app.config.security.provider;

import com.gun.app.config.security.model.JwtAuthToken;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Component
public class JwtAuthProvider implements AuthenticationProvider {
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        Jws<Claims> claimsJws = (Jws<Claims>) authentication.getCredentials();

        String subject = claimsJws.getBody().getSubject();
        List<String> roles = claimsJws.getBody().get("roles", List.class);

        List<GrantedAuthority> authorities = roles.stream()
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
        log.info(authorities.toString());
        return new JwtAuthToken(subject, authorities);
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return (JwtAuthToken.class.isAssignableFrom(authentication));
    }
}