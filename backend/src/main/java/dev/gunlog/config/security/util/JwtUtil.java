package dev.gunlog.config.security.util;

import dev.gunlog.config.security.exception.JwtExpTokenException;
import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class JwtUtil {
    @Value("${jwt.token.issuer}")
    private String issuer;
    @Value("${jwt.token.expTime}")
    private long expTime;
    @Value("${jwt.token.secretKey}")
    private String secretKey;

    /**
     * JWT 토큰 생성
     * @param username
     * @param authorities
     * @return
     */
    public String createToken(String username, List<GrantedAuthority> authorities){
        Claims claims = Jwts.claims().setSubject(username);
        claims.put("roles", authorities.stream().map(role -> role.toString()).collect(Collectors.toList()));

        LocalDateTime currentTime = LocalDateTime.now();

        return Jwts.builder()
                .setClaims(claims)
                .setIssuer(issuer)
                .setIssuedAt(Date.from(currentTime.atZone(ZoneId.systemDefault()).toInstant()))
                .setExpiration(Date.from(currentTime.plusMinutes(expTime)
                        .atZone(ZoneId.systemDefault()).toInstant()))
                .signWith(SignatureAlgorithm.HS512, secretKey)
                .compact();
    }

    /**
     * JWT 토큰 파싱
     * @param token
     * @return
     * @throws BadCredentialsException
     * @throws JwtExpTokenException
     */
    public Jws<Claims> parserToken(String token) throws BadCredentialsException, JwtExpTokenException{
        Jws<Claims> claimsJws = null;
        try {
            claimsJws = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);
        } catch (UnsupportedJwtException | MalformedJwtException | IllegalArgumentException ex) {
            throw new BadCredentialsException("Invalid JWT token: ", ex);
        } catch (ExpiredJwtException expiredEx) {
            throw new JwtExpTokenException("JWT 토큰 만료 ::"+token);
        }
        return claimsJws;
    }
}