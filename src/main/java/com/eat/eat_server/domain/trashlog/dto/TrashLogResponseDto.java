package com.eat.eat_server.domain.trashlog.dto;

import com.eat.eat_server.domain.trashlog.domain.TrashLog;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
public class TrashLogResponseDto {
    private final Long id;
    private final LocalDateTime createdTime;
    private final double amount;

    @Builder
    private TrashLogResponseDto(Long id, LocalDateTime createdTime, double amount){
        this.id = id;
        this.createdTime = createdTime;
        this.amount = amount;
    }


   public static TrashLogResponseDto from(TrashLog trashLog){
        return TrashLogResponseDto.builder()
                .id(trashLog.getId())
                .createdTime(trashLog.getCreatedTime())
                .amount(trashLog.getAmount())
                .build();
   }
}
