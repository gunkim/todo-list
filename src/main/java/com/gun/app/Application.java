package com.gun.app;

import com.gun.app.domain.Member;
import com.gun.app.domain.MemberRepository;
import com.gun.app.domain.Role;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.sql.SQLException;
import java.util.stream.IntStream;

@EnableJpaAuditing
@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
    @Bean
    public CommandLineRunner runner(MemberRepository memberRepository, PasswordEncoder passwordEncoder) throws SQLException {
        return (args) -> {
            memberRepository.save(
                    Member.builder()
                            .memberId("gunkim")
                            .password(passwordEncoder.encode("test"))
                            .name("gunkim")
                            .role(Role.USER)
                            .build()
            );
            memberRepository.save(
                    Member.builder()
                            .memberId("test")
                            .password(passwordEncoder.encode("test"))
                            .name("gunkim")
                            .role(Role.USER)
                            .build()
            );
        };
    }
}