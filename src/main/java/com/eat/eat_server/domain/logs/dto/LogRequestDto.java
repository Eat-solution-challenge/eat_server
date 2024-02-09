package com.eat.eat_server.domain.logs.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class LogRequestDto {

    private final String subCategory;
    private final String menu;
    private final double intake;
    private final String unit;
    private final int level;
    private final int calorie;
    private final double fat;
    private final double protein;
    private final double carbs;
    private final double sugar;
    private final String memo;
}
