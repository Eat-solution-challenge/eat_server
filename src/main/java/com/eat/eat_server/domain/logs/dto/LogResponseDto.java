package com.eat.eat_server.domain.logs.dto;

import com.eat.eat_server.domain.logs.domain.Level;
import com.eat.eat_server.domain.logs.domain.Log;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class LogResponseDto {

    private final long id;
    @JsonFormat(pattern = "MM월 dd일")
    private final LocalDateTime createdTime;
    private final String menu;
    private final double intake;
    private final String unit;
    private final String level;
    private final int calorie;
    private final double fat;
    private final double protein;
    private final double carbs;
    private final double sugar;
    private final String memo;
    private final String timeslot;

    @Builder
    private LogResponseDto(long id,
                           LocalDateTime createdTime,
                           String menu,
                           double intake,
                           String unit,
                           Level level,
                           int calorie,
                           double fat,
                           double protein,
                           double carbs,
                           double sugar,
                           String memo,
                           String timeslot) {
        this.id = id;
        this.createdTime = createdTime;
        this.menu = menu;
        this.intake = intake;
        this.unit = unit;
        this.level = level.toString();
        this.calorie = calorie;
        this.fat = fat;
        this.protein = protein;
        this.carbs = carbs;
        this.sugar = sugar;
        this.memo = memo;
        this.timeslot = timeslot;
    }

    public static LogResponseDto from(Log log) {
        return LogResponseDto.builder()
                .id(log.getId())
                .createdTime(log.getCreatedTime())
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
                .timeslot(log.getTimeslot())
                .build();
    }
}
