package com.jobster.website.responses;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Builder
public class JWTResponse {
    private String token;
    private String login;
    private String role;


    public JWTResponse(String token, String login, String role) {
        this.token = token;
        this.login = login;
        this.role = role;
    }
}
