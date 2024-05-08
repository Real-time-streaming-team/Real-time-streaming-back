package com.example.realtimestreaming.Domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor // 모든 필드에 대한 생성자
public class PurchaseOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long purchaseOrderId;

    private String impUserId;

    private int paidAmount;

    @ManyToOne
    private User payer;
}
