package com.example.realtimestreaming.Domain;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor // 모든 필드에 대한 생성자
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @Column(name = "nickname")
    private String nickname;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @ColumnDefault("1")
    @Column(name = "balance")
    private int balance;

    @OneToMany(mappedBy = "owner", cascade = CascadeType.REMOVE)
    private final List<Stream> streams = new ArrayList<>();

    @OneToMany(mappedBy = "payer", cascade = CascadeType.REMOVE)
    private final List<PurchaseOrder> purchaseOrders = new ArrayList<>();
}
