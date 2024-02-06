package com.eat.eat_server.logs.dto;

import com.eat.eat_server.logs.domain.Log;
import lombok.Builder;
import lombok.Getter;

@Getter
public class LogResponseDto {
    private final Long id;
    private final String subCategory;
    private final String menu;

    @Builder
    private LogResponseDto(Long id,
                           String subCategory,
                           String menu) {
        this.id = id;
        this.subCategory = subCategory;
        this.menu = menu;
    }

    public static LogResponseDto from(Log log) {
        return LogResponseDto.builder()
                .id(log.getId())
                .subCategory(log.getSubCategory().getName())
                .menu(log.getMenu())
                .build();
    }
}
