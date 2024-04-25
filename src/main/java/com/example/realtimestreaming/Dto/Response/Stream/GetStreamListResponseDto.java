package com.example.realtimestreaming.Dto.Response.Stream;

import com.example.realtimestreaming.Domain.Stream;
import com.example.realtimestreaming.Domain.User;
import lombok.Data;
import lombok.Getter;

import java.util.List;

@Data
public class GetStreamListResponseDto {

    List<GetStreamResponseDto> streamList;

    @Getter
    public static class GetStreamResponseDto {
        private Long streamId;
        private String title;
        private UserDto streamer;

        public GetStreamResponseDto (Stream stream) {
            this.streamId = stream.getStreamId();
            this.title = stream.getTitle();
            this.streamer = new UserDto(stream.getOwner());
        }
    }

    @Getter
    public static class UserDto {
        private Long userId;
        private String nickname;

        public UserDto (User user) {
            this.userId = user.getUserId();
            this.nickname = user.getNickname();
        }
    }
}
