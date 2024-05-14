package com.example.realtimestreaming.Dto.Request.PurchaseOrder;

import com.example.realtimestreaming.Domain.User;
import lombok.Data;
import lombok.Getter;

@Data
public class RechargeRequestDto {

    private String impUserId;

    private int paidAmount;


}
