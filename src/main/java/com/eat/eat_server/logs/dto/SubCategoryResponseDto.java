package com.eat.eat_server.logs.dto;

import com.eat.eat_server.logs.domain.SubCategory;
import lombok.Builder;
import lombok.Getter;

@Getter
public class SubCategoryResponseDto {

    private long id;
    private String name;

    @Builder
    private SubCategoryResponseDto(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public static SubCategoryResponseDto from(SubCategory subCategory) {
        return SubCategoryResponseDto.builder()
                .id(subCategory.getId())
                .name(subCategory.getName())
                .build();
    }
}
