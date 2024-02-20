package com.eat.eat_server.domain.logs.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
public class ProperAmountDto {
    private String properAmount;
    private String unit;

    @Builder
    public ProperAmountDto(double properAmount, String unit){
        this.properAmount = String.valueOf(properAmount);
        this.unit = unit;
    }
}
