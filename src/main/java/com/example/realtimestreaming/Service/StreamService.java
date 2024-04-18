package com.example.realtimestreaming.Service;

import com.example.realtimestreaming.Domain.Stream;
import com.example.realtimestreaming.Domain.User;
import com.example.realtimestreaming.Dto.Request.Stream.SendChatReq;
import com.example.realtimestreaming.Repository.StreamRepository;
import com.example.realtimestreaming.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StreamService {

    @Autowired
    StreamRepository streamRepository;

    @Autowired
    UserRepository userRepository;

    public void sendMessage(SendChatReq sendChatReq, Long streamId) {
        // 유저 존재 확인
        userRepository.findByUserId(sendChatReq.getUserId());

        System.out.println("어디서 오류가 나는거냐");

        // 스트리밍 존재 확인
        streamRepository.findByStreamId(streamId);


    }
}
