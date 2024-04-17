package com.example.realtimestreaming.Dto.Request.User;

import lombok.Data;

@Data
public class UserSignupReq {
    private String email;
    private String password;
    private String nickname;
}
