package com.example.realtimestreaming.Controller;

import com.example.realtimestreaming.Common.HTTP_INTERNAL_SERVER_ERROR;
import com.example.realtimestreaming.Dto.Request.Stream.SendChatRequestDto;
import com.example.realtimestreaming.Service.StreamService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/stream")
@CrossOrigin(origins = "*", allowedHeaders = "*")
@Tag(name = "스트리밍 API", description = "스트리밍 API입니다")
public class StreamController {

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    @Autowired
    private StreamService streamService;



    @Operation(summary = "채팅 전달")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "성공",
                    content = {@Content(schema = @Schema(implementation = SendChatRequestDto.class))}),
            @ApiResponse(responseCode = "500", description = "서버 에러",
                    content = {@Content(schema = @Schema(implementation = HTTP_INTERNAL_SERVER_ERROR.class))}),
    })
    @PostMapping("/sendChat/{streamId}")
    public void swaggerSendChat(@RequestBody SendChatRequestDto sendChatRequestDto, @DestinationVariable("streamId") Long streamId) {
//        Long parsedStreamId = Long.parseLong(streamId);
        System.out.println("12321");
        streamService.sendMessage(sendChatRequestDto, streamId);
        String destination = "/stream/" + streamId;
        this.messagingTemplate.convertAndSend(destination, sendChatRequestDto);
    }

    @GetMapping("/{streamId}")
    public String getStreamDetail(@PathVariable Long streamId) {
        return "streamDetail"; // 이 경우에는 Thymeleaf나 Freemarker 등의 템플릿 엔진이 경로를 처리합니다.
    }

    @Operation(summary = "프로젝트 디테일 조회")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "성공",
                    content = {@Content(schema = @Schema(implementation = SendChatRequestDto.class))}),
            @ApiResponse(responseCode = "500", description = "서버 에러",
                    content = {@Content(schema = @Schema(implementation = HTTP_INTERNAL_SERVER_ERROR.class))}),
    })
    @MessageMapping("/sendChat/{streamId}")
    public void sendChat(SendChatRequestDto sendChatRequestDto, @DestinationVariable("streamId") Long streamId) {
//        Long parsedStreamId = Long.parseLong(streamId);
        System.out.println(sendChatRequestDto.getContent());
        streamService.sendMessage(sendChatRequestDto, streamId);
        String destination = "/stream/" + streamId;
        this.messagingTemplate.convertAndSend(destination, sendChatRequestDto);
    }
}