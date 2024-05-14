package com.example.realtimestreaming.Dto.Response.User;

import lombok.Data;

@Data
public class ChangeNicknameResponseDto {
    private Long userId;

    private String nickname;
}