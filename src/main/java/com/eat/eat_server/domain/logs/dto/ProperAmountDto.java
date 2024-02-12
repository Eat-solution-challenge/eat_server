package com.eat.eat_server.domain.logs.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
public class ProperAmountDto {
    private int properAmount;

    @Builder
    public ProperAmountDto(int properAmount){
        this.properAmount = properAmount;
    }
}
