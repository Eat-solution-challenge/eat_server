package com.eat.eat_server.logs.domain;

import jakarta.persistence.*;
import com.eat.eat_server.logs.dto.LogRequestDto;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class Log {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "log_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "subcategory_id")
    private SubCategory subCategory;

    @Column
    private String menu;

    @Column
    private Double intake;

    @Column
    private String unit;

    @Column
    private String memo;

    @Builder
    private Log(
            SubCategory subCategory,
            String menu,
            Double intake,
            String unit,
            String memo) {
        this.subCategory = subCategory;
        this.menu = menu;
        this.intake = intake;
        this.unit = unit;
        this.memo = memo;
    }

    public static Log of(SubCategory subCategory, LogRequestDto logRequestDto) {
        return Log.builder()
                .subCategory(subCategory)
                .menu(logRequestDto.getMenu())
                .intake(logRequestDto.getIntake())
                .unit(logRequestDto.getUnit())
                .memo(logRequestDto.getMemo())
                .build();
    }
}
