package com.example.realtimestreaming.Dto.Request.Stream;

import lombok.Data;

@Data
public class SendChatRequestDto {
    private Long userId;
    private String content;

}

