package com.eat.eat_server.logs.dto;

import com.eat.eat_server.logs.domain.Log;
import lombok.Builder;
import lombok.Getter;

@Getter
public class LogResponseDto {

    private final long id;
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

    @Builder
    private LogResponseDto(long id,
                           String menu,
                           double intake,
                           String unit,
                           int level,
                           int calorie,
                           double fat,
                           double protein,
                           double carbs,
                           double sugar,
                           String memo) {
        this.id = id;
        this.menu = menu;
        this.intake = intake;
        this.unit = unit;
        this.level = level;
        this.calorie = calorie;
        this.fat = fat;
        this.protein = protein;
        this.carbs = carbs;
        this.sugar = sugar;
        this.memo = memo;
    }

    public static LogResponseDto from(Log log) {
        return LogResponseDto.builder()
                .id(log.getId())
                .menu(log.getMenu())
                .intake(log.getIntake())
                .unit(log.getUnit())
                .level(log.getLevel())
                .calorie(log.getCalorie())
                .fat(log.getFat())
                .protein(log.getProtein())
                .carbs(log.getCarbs())
                .sugar(log.getSugar())
                .memo(log.getMemo())
                .build();
    }
}
