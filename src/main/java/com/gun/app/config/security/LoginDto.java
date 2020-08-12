package com.gun.app.config.security;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.ToString;

@ToString
@Getter
public class LoginDto {
    private String username;
    private String password;

    public LoginDto(@JsonProperty("username") String username, @JsonProperty("password") String password){
        this.username = username;
        this.password = password;
    }
}