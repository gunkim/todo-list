package dev.gunlog.application.spring.auth.provider;

import dev.gunlog.application.spring.member.service.MemberService;
import dev.gunlog.application.spring.todo.web.response.MemberResponse;
import dev.gunlog.domain.member.Member;
import java.nio.file.attribute.UserPrincipalNotFoundException;
import java.util.ArrayList;
import java.util.List;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

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

        MemberResponse dto = null;
        try {
            Member member = memberService.findLoginId(username);
            dto = new MemberResponse(
                member.loginId(),
                member.password(),
                member.role()
            );
        } catch (UserPrincipalNotFoundException e) {
            throw new BadCredentialsException("해당 회원 정보 조회 실패");
        }

        if (!passwordEncoder.matches(password, dto.password())) {
            throw new BadCredentialsException("비밀번호가 불일치합니다.");
        }
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(dto.role().getValue()));

        return new UsernamePasswordAuthenticationToken(dto.memberId(), null, authorities);
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return (UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication));
    }
}