package com.example.realtimestreaming.Controller;

import com.example.realtimestreaming.Domain.User;
import com.example.realtimestreaming.Dto.Request.User.UserSignupReq;
import com.example.realtimestreaming.Service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/user", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<User> signup(@RequestBody UserSignupReq request) {
        User user = userService.signup(request);
        return ResponseEntity.ok(user);
    }

}
