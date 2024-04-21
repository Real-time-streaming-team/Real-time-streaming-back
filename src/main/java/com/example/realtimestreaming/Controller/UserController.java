package com.example.realtimestreaming.Controller;

import com.example.realtimestreaming.Common.HTTP_INTERNAL_SERVER_ERROR;
import com.example.realtimestreaming.Domain.User;
import com.example.realtimestreaming.Dto.Request.User.LoginRequestDto;
import com.example.realtimestreaming.Dto.Request.User.SignupRequestDto;
import com.example.realtimestreaming.Dto.Response.User.LoginResponseDto;
import com.example.realtimestreaming.Dto.Response.User.SignupResponseDto;
import com.example.realtimestreaming.Service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/user", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;



    @Operation(summary = "회원가입")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "성공",
                    content = {@Content(schema = @Schema(implementation = SignupResponseDto.class))}),
            @ApiResponse(responseCode = "500", description = "서버 에러",
                    content = {@Content(schema = @Schema(implementation = HTTP_INTERNAL_SERVER_ERROR.class))}),
    })
    @PostMapping("/signup")
    public ResponseEntity<SignupResponseDto> signup (@RequestBody SignupRequestDto signUpRequestDto) {
        System.out.println(signUpRequestDto);
        User user = userService.signUp(signUpRequestDto);
        SignupResponseDto userResponseDto = new SignupResponseDto(user);
        return ResponseEntity.ok(userResponseDto);
    }

    @Operation(summary = "로그인")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "성공",
                    content = {@Content(schema = @Schema(implementation = LoginResponseDto.class))}),
            @ApiResponse(responseCode = "500", description = "서버 에러",
                    content = {@Content(schema = @Schema(implementation = HTTP_INTERNAL_SERVER_ERROR.class))}),
    })
    @PostMapping("/login")
    public ResponseEntity<LoginResponseDto> login (@RequestBody LoginRequestDto loginDto) throws Exception {
        LoginResponseDto loginResponse = userService.login(loginDto);
        return ResponseEntity.ok(loginResponse);
    }

}
