package com.example.realtimestreaming.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/chat")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ChatController {
    @GetMapping("/info")
    public String handleInfoRequest(@RequestParam("t") String timestamp) {
        // t 매개변수를 사용하여 필요한 처리를 수행
        return "Received timestamp: " + timestamp;
    }
}