package com.example.realtimestreaming.Service;

import com.example.realtimestreaming.Domain.Stream;
import com.example.realtimestreaming.Dto.Request.Stream.SendChatRequestDto;
import com.example.realtimestreaming.Repository.StreamRepository;
import com.example.realtimestreaming.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StreamService {

    @Autowired
    StreamRepository streamRepository;

    @Autowired
    UserRepository userRepository;

    public void sendMessage(SendChatRequestDto sendChatRequestDto, Long streamId) {
        // 유저 존재 확인
        userRepository.findByUserId(sendChatRequestDto.getUserId());

        System.out.println("어디서 오류가 나는거냐");

        // 스트리밍 존재 확인
        streamRepository.findByStreamId(streamId);
    }

    public List<Stream> getStreamList() {
        return streamRepository.findAll();
    }
}
