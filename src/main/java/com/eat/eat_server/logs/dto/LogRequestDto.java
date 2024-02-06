package com.eat.eat_server.logs.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class LogRequestDto {

    private final String subCategory;
    private final String menu;
    private final Double intake;
    private final String unit;
    private final String memo;
}
