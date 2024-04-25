package com.example.realtimestreaming.Domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter @Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor // 모든 필드에 대한 생성자
public class Stream {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long streamId;

    private String streamKey;

    private String title;

    @ManyToOne
    private User owner;
}
