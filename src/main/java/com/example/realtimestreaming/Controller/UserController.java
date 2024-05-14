package com.example.realtimestreaming.Controller;

import com.example.realtimestreaming.Common.HTTP_INTERNAL_SERVER_ERROR;
import com.example.realtimestreaming.Domain.User;
import com.example.realtimestreaming.Dto.Request.User.ChangeNicknameRequestDto;
import com.example.realtimestreaming.Dto.Request.User.LoginRequestDto;
import com.example.realtimestreaming.Dto.Request.User.SignupRequestDto;
import com.example.realtimestreaming.Dto.Response.User.ChangeNicknameResponseDto;
import com.example.realtimestreaming.Dto.Response.User.LoginResponseDto;
import com.example.realtimestreaming.Dto.Response.User.SignupResponseDto;
import com.example.realtimestreaming.Dto.Response.User.VerifyStreamKeyResponseDto;
import com.example.realtimestreaming.Provider.JwtTokenProvider;
import com.example.realtimestreaming.Service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/user", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
@Tag(name = "유저 API", description = "유저 API입니다")
public class UserController {

    private final UserService userService;

    private final JwtTokenProvider jwtTokenProvider;

    @Operation(summary = "회원가입")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "성공",
                    content = {@Content(schema = @Schema(implementation = SignupResponseDto.class))}),
            @ApiResponse(responseCode = "500", description = "서버 에러",
                    content = {@Content(schema = @Schema(implementation = HTTP_INTERNAL_SERVER_ERROR.class))}),
    })
    @PostMapping("/signup")
    public ResponseEntity<SignupResponseDto> signup (@RequestBody SignupRequestDto signUpRequestDto) throws Exception {
        SignupResponseDto response = userService.signUp(signUpRequestDto);
        return ResponseEntity.ok(response);
    }

    @GetMapping("verify")
    public ResponseEntity<?> verifyStreamKey (@RequestParam(name = "stream_key") String streamKey) {
        VerifyStreamKeyResponseDto verifyStreamKeyResponseDto = new VerifyStreamKeyResponseDto();
        verifyStreamKeyResponseDto.setFile_name("1");
        return ResponseEntity.ok(verifyStreamKeyResponseDto);
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

    @Operation(summary = "닉네임 변경")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "성공",
                    content = {@Content(schema = @Schema(implementation = ChangeNicknameResponseDto.class))}),
            @ApiResponse(responseCode = "500", description = "서버 에러",
                    content = {@Content(schema = @Schema(implementation = HTTP_INTERNAL_SERVER_ERROR.class))}),
    })
    @PostMapping("/changeNickname")
    public ResponseEntity<ChangeNicknameResponseDto> changeNickname (@RequestHeader("Authorization") String token, @RequestBody ChangeNicknameRequestDto changeNicknameRequestDto) throws Exception {
        User user = jwtTokenProvider.validateToken(token);
        User changedUser = userService.changeNickname(user.getUserId(), changeNicknameRequestDto);
        ChangeNicknameResponseDto response = new ChangeNicknameResponseDto();
        response.setNickname(changedUser.getNickname());
        response.setUserId(changedUser.getUserId());
        return ResponseEntity.ok(response);
    }
}
