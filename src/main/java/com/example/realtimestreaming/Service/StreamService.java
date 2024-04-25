package com.example.realtimestreaming.Service;

import com.example.realtimestreaming.Domain.Stream;
import com.example.realtimestreaming.Dto.Request.Stream.SendChatRequestDto;
import com.example.realtimestreaming.Repository.StreamRepository;
import com.example.realtimestreaming.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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

    public Page<Stream> streamSearch (String keyword, Integer page) {
        Integer size = 10;
        Pageable pageable = PageRequest.of(page, size, Sort.by("title").ascending());
        return streamRepository.findByTitleOrOwnerNickname(keyword, pageable);
    }

}
