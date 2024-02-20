package com.eat.eat_server.domain.trashlog.domain;

import com.eat.eat_server.domain.trashlog.dto.TrashLogRequestDto;
import com.eat.eat_server.domain.user.domain.User;
import com.eat.eat_server.global.entity.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class TrashLog extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "trashlogs_id")
    private Long id;

    private double amount;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Builder
    private TrashLog(User user, double amount){
        this.user = user;
        this.amount = amount;
    }

    public void updateAmount(double amount){
        this.amount = amount;
    }
    public static TrashLog of(User user, TrashLogRequestDto trashLogRequestDto){
        return TrashLog.builder()
                .user(user)
                .amount(trashLogRequestDto.getAmount())
                .build();
    }
}
