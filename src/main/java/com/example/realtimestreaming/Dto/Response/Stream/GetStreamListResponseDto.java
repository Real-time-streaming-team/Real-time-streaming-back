package com.example.realtimestreaming.Dto.Response.Stream;

import lombok.Data;
import lombok.Getter;

import java.util.List;

@Data
public class GetStreamListResponseDto {

    List<GetStreamResponseDto> getStreamResponseDtoList;

    public static class GetStreamResponseDto {
        private Long streamId;
        private String title;
        private UserDto streamer;
    }

    @Getter
    public static class UserDto {
        private Long userId;
        private String title;
        private String nickname;
    }
}
