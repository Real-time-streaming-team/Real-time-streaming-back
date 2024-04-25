package com.example.realtimestreaming.Controller;

import com.example.realtimestreaming.Common.HTTP_INTERNAL_SERVER_ERROR;
import com.example.realtimestreaming.Domain.Stream;
import com.example.realtimestreaming.Dto.Request.Stream.SendChatRequestDto;
import com.example.realtimestreaming.Dto.Response.Stream.GetStreamListResponseDto;
import com.example.realtimestreaming.Dto.Response.Stream.StreamSearchListResponseDto;
import com.example.realtimestreaming.Service.StreamService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/stream")
@CrossOrigin(origins = "*", allowedHeaders = "*")
@Tag(name = "스트리밍 API", description = "스트리밍 API입니다")
public class StreamController {

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    @Autowired
    private StreamService streamService;

    @Operation(summary = "스트리밍 리스트 전달")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "성공",
                    content = {@Content(schema = @Schema(implementation = GetStreamListResponseDto.class))}),
            @ApiResponse(responseCode = "500", description = "서버 에러",
                    content = {@Content(schema = @Schema(implementation = HTTP_INTERNAL_SERVER_ERROR.class))}),
    })
    @GetMapping("/{streamId}")
    public ResponseEntity<?> getStreamList(@PathVariable(name = "streamId") Long streamId) {
//        List<Stream> streams = streamService.getStreamList();
        return ResponseEntity.ok("success");
    }

    @Operation(summary = "스트리밍 검색")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "성공",
                    content = {@Content(schema = @Schema(implementation = StreamSearchListResponseDto.class))}),
            @ApiResponse(responseCode = "500", description = "서버 에러",
                    content = {@Content(schema = @Schema(implementation = HTTP_INTERNAL_SERVER_ERROR.class))}),
    })
    @GetMapping("search/{keyword}")
    public ResponseEntity<StreamSearchListResponseDto> streamSearch(@PathVariable(name = "keyword") String title) {
        List<Stream> streams = streamService.streamSearch(title);

        StreamSearchListResponseDto streamSearchListRes = new StreamSearchListResponseDto();
        List<StreamSearchListResponseDto.StreamSearchResponseDto> streamSearchList = new ArrayList<>();

        for (Stream stream : streams) {
            StreamSearchListResponseDto.StreamSearchResponseDto streamSearchResponseDto = new StreamSearchListResponseDto.StreamSearchResponseDto(stream);
            streamSearchList.add(streamSearchResponseDto);
        }
        streamSearchListRes.setStreamList(streamSearchList);

        return ResponseEntity.ok(streamSearchListRes);
    }

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

    public void sendChat(SendChatRequestDto sendChatRequestDto, @DestinationVariable("streamId") Long streamId) {
//        Long parsedStreamId = Long.parseLong(streamId);
        System.out.println(sendChatRequestDto.getContent());
        streamService.sendMessage(sendChatRequestDto, streamId);
        String destination = "/stream/" + streamId;
        this.messagingTemplate.convertAndSend(destination, sendChatRequestDto);
    }

//    // 백앤드 디테일 페이지 로컬 실험
//    @GetMapping("/{streamId}")
//    public String getStreamDetail(@PathVariable Long streamId) {
//        return "streamDetail"; // 이 경우에는 Thymeleaf나 Freemarker 등의 템플릿 엔진이 경로를 처리합니다.
//    }
}