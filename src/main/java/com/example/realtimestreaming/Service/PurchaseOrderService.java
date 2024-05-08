package com.example.realtimestreaming.Service;

import com.example.realtimestreaming.Domain.PurchaseOrder;
import com.example.realtimestreaming.Domain.User;
import com.example.realtimestreaming.Dto.Request.PurchaseOrder.RechargeRequestDto;
import com.example.realtimestreaming.Dto.Response.PurchaseOrder.GetBalanceResponseDto;
import com.example.realtimestreaming.Dto.Response.PurchaseOrder.RechargeResponseDto;
import com.example.realtimestreaming.Repository.PurchaseOrderRepository;
import com.example.realtimestreaming.Repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

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

    public GetBalanceResponseDto getBalance (User user) {
        Optional<User> findUser = userRepository.findById(user.getUserId());
        User userEntity = findUser.orElseThrow(() -> new EntityNotFoundException("User not found"));
        GetBalanceResponseDto response = new GetBalanceResponseDto(userEntity);
        return response;
    }
}
