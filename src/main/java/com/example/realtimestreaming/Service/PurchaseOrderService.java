package com.example.realtimestreaming.Service;

import com.example.realtimestreaming.Domain.PurchaseOrder;
import com.example.realtimestreaming.Domain.User;
import com.example.realtimestreaming.Dto.Request.PurchaseOrder.RechargeRequestDto;
import com.example.realtimestreaming.Dto.Response.PurchaseOrder.RechargeResponseDto;
import com.example.realtimestreaming.Repository.PurchaseOrderRepository;
import com.example.realtimestreaming.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PurchaseOrderService {

    @Autowired
    PurchaseOrderRepository purchaseOrderRepository;

    @Autowired
    UserRepository userRepository;

    @Transactional
    public RechargeResponseDto recharge (User user, RechargeRequestDto rechargeRequestDto) {
        PurchaseOrder createdPurchaseOrder = purchaseOrderRepository.save(
                PurchaseOrder.builder().
                        impUserId(rechargeRequestDto.getImpUserId())
                        .paidAmount(rechargeRequestDto.getPaidAmount())
                        .purchaseOrderId(rechargeRequestDto.getPurchaseOrderId())
                        .payer(user)
                        .build()
        );
        System.out.println("확인용123" + rechargeRequestDto.getPaidAmount());
        int totalBalance = user.getBalance() + rechargeRequestDto.getPaidAmount();
        user.setBalance(totalBalance);
        userRepository.save(user);

        RechargeResponseDto response = new RechargeResponseDto();
        response.setResult("success");
        response.setBalance(totalBalance);
        return response;
    }
}
