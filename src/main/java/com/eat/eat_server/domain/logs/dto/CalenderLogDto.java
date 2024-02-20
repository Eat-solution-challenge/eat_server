package com.eat.eat_server.domain.logs.dto;

import com.eat.eat_server.domain.logs.domain.Level;
import com.eat.eat_server.domain.logs.domain.Log;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;


@Getter
@RequiredArgsConstructor
public class CalenderLogDto {

    private final long id;
    @JsonFormat(pattern = "yyyy년 MM월 dd일")
    private final LocalDateTime createdTime;
    private final String level;
    private final String menu;
    private final double intake;
    private final String unit;
    private final int calorie;

    @Builder
    private CalenderLogDto(long id,
                           LocalDateTime createdTime,
                           Level level,
                           String menu,
                           double intake,
                           String unit,
                           int calorie) {
        this.id = id;
        this.createdTime = createdTime;
        this.menu = menu;
        this.intake = intake;
        this.unit = unit;
        this.level = level.toString();
        this.calorie = calorie;
    }

    public static CalenderLogDto from(Log log) {
        return CalenderLogDto.builder()
                .id(log.getId())
                .createdTime(log.getCreatedTime())
                .menu(log.getMenu())
                .intake(log.getIntake())
                .unit(log.getUnit())
                .level(log.getLevel())
                .calorie(log.getCalorie())
                .build();
    }
}
