package com.example.realtimestreaming.Dto.Request.User;

import lombok.Data;

@Data
public class SignupRequestDto {
    private String email;
    private String password;
    private String nickname;
}
