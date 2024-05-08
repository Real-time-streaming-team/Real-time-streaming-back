package com.example.realtimestreaming.Dto.Response.PurchaseOrder;

import com.example.realtimestreaming.Domain.User;
import lombok.Data;

@Data
public class GetBalanceResponseDto {

    private String nickname;
    private String email;
    private int balance;

    public GetBalanceResponseDto (User user) {
        this.nickname = user.getNickname();
        this.email = user.getEmail();
        this.balance = user.getBalance();
    }
}
