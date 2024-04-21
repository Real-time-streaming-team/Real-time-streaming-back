package com.example.realtimestreaming.Dto.Request.User;

import lombok.Data;

@Data
public class LoginRequestDto {
    private String email;
    private String password;
}
