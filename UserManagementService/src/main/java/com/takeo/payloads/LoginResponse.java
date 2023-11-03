package com.takeo.payloads;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class LoginResponse {
    private String accessToken;
    private String tokenType ="Bearer ";
    public LoginResponse(String accessToken){
        this.accessToken = accessToken;
    }
}
