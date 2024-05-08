package com.example.realtimestreaming.Controller;

import com.example.realtimestreaming.Common.HTTP_INTERNAL_SERVER_ERROR;
import com.example.realtimestreaming.Domain.User;
import com.example.realtimestreaming.Dto.Request.PurchaseOrder.RechargeRequestDto;
import com.example.realtimestreaming.Dto.Request.User.SignupRequestDto;
import com.example.realtimestreaming.Dto.Response.PurchaseOrder.GetBalanceResponseDto;
import com.example.realtimestreaming.Dto.Response.PurchaseOrder.RechargeResponseDto;
import com.example.realtimestreaming.Dto.Response.User.SignupResponseDto;
import com.example.realtimestreaming.Provider.JwtTokenProvider;
import com.example.realtimestreaming.Service.PurchaseOrderService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/purchaseOrder")
@Tag(name = "결제 API", description = "결제 API입니다")
public class PurchaseOrderController {

    @Autowired
    PurchaseOrderService purchaseOrderService;

    @Autowired
    JwtTokenProvider jwtTokenProvider;

    @Operation(summary = "포인트 충전(프론트에서 충전 성공시 데이터 전달해주세요)")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "성공",
                    content = {@Content(schema = @Schema(implementation = RechargeResponseDto.class))}),
            @ApiResponse(responseCode = "500", description = "서버 에러",
                    content = {@Content(schema = @Schema(implementation = HTTP_INTERNAL_SERVER_ERROR.class))}),
    })
    @PostMapping("/recharge")
    public ResponseEntity<?> recharge (@RequestHeader("Authorization") String token, @RequestBody RechargeRequestDto rechargeRequestDto) throws Exception {
        User user = jwtTokenProvider.validateToken(token);
        RechargeResponseDto response = purchaseOrderService.recharge(user, rechargeRequestDto);
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "포인트 조회")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "성공",
                    content = {@Content(schema = @Schema(implementation = GetBalanceResponseDto.class))}),
            @ApiResponse(responseCode = "500", description = "서버 에러",
                    content = {@Content(schema = @Schema(implementation = HTTP_INTERNAL_SERVER_ERROR.class))}),
    })
    @GetMapping("/myBalance")
    public ResponseEntity<?> getBalance (@RequestHeader("Authorization") String token) {
        User user = jwtTokenProvider.validateToken(token);
        GetBalanceResponseDto response = purchaseOrderService.getBalance(user);
        return ResponseEntity.ok(response);
    }
}
