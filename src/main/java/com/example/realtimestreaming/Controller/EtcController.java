package com.example.realtimestreaming.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


// [프로젝트 완성 후 삭제 예정] - 실험용 컨트롤러입니다
@Controller
@RequestMapping("/etc")
public class EtcController {

    @GetMapping("/share")
    public String getStreamPage() {
        return "screenShare.html";
    }
}
