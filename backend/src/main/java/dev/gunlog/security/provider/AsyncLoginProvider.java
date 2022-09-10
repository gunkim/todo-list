package dev.gunlog.security.provider;

import dev.gunlog.dto.MemberResponseDto;
import dev.gunlog.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.nio.file.attribute.UserPrincipalNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Component
public class AsyncLoginProvider implements AuthenticationProvider {
    private final MemberService memberService;
    private final PasswordEncoder passwordEncoder;

    public AsyncLoginProvider(MemberService memberService, PasswordEncoder passwordEncoder) {
        this.memberService = memberService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Authentication authenticate(Authentication auth) throws AuthenticationException {
        String username = (String) auth.getPrincipal();
        String password = (String) auth.getCredentials();

        MemberResponseDto dto = null;
        try{
            dto = memberService.getMember(username);
        }catch(UserPrincipalNotFoundException e){
            throw new BadCredentialsException("해당 회원 정보 조회 실패");
        }

        if(!passwordEncoder.matches(password, dto.getPassword())){
            throw new BadCredentialsException("비밀번호가 불일치합니다.");
        }
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(dto.getRole().getValue()));

        return new UsernamePasswordAuthenticationToken(dto.getMemberId(), null, authorities);
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return (UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication));
    }
}