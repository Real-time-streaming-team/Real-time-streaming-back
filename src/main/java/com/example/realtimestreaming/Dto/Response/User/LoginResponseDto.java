package com.example.realtimestreaming.Dto.Response.User;

import com.example.realtimestreaming.Domain.User;
import lombok.Data;

@Data
public class LoginResponseDto {

    private Long userId;
    private String email;
    private String password;
    private String nickname;
    private String token;

    public LoginResponseDto (User user) {
        this.userId = user.getUserId();
        this.email = user.getEmail();
        this.password = user.getPassword();
        this.nickname = user.getNickname();
    }
}
