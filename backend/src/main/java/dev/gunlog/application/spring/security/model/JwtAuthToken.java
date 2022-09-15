package dev.gunlog.application.spring.security.model;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

public class JwtAuthToken extends AbstractAuthenticationToken {
    private final String username;
    private final Jws<Claims> claimsJws;

    public JwtAuthToken(Jws<Claims> claimsJws){
        super(null);
        this.claimsJws = claimsJws;
        this.setAuthenticated(false); //인증(Authentication)되지 않았음.
        this.username = null;
    }
    public JwtAuthToken(String username, Collection<? extends GrantedAuthority> authorities) {
        super(authorities);
        this.eraseCredentials();
        this.username = username;
        super.setAuthenticated(true); //인증(Authentication)되었음.
        this.claimsJws = null;
    }

    @Override
    public Object getPrincipal() {
        return this.username;
    }

    @Override
    public Object getCredentials() {
        return this.claimsJws;
    }
}