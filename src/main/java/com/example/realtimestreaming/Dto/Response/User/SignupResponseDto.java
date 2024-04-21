package com.example.realtimestreaming.Dto.Response.User;

import com.example.realtimestreaming.Domain.User;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignupResponseDto {
    private Long userId;
    private String email;
    private String password;
    private String nickname;

    public SignupResponseDto (User user) {
        this.userId = user.getUserId();
        this.email = user.getEmail();
        this.password = user.getPassword();
        this.nickname = user.getNickname();
    }
}
