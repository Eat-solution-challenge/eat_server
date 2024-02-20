package com.eat.eat_server.domain.logs.domain;

import com.eat.eat_server.global.entity.BaseTimeEntity;
import jakarta.persistence.*;
import com.eat.eat_server.domain.logs.dto.LogRequestDto;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class Log extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "log_id")
    private long id;

    @ManyToOne
    @JoinColumn(name = "subcategory_id")
    private SubCategory subCategory;

    @Column(nullable = false)
    private String menu;

    @Column(nullable = false)
    private double intake;

    @Column(nullable = false)
    private String unit;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Level level;

    @Column
    private int calorie;

    @Column
    private double fat;

    @Column
    private double protein;

    @Column
    private double carbs;

    @Column
    private double sugar;

    @Column
    private String memo;

    private String timeslot;

    @Builder
    private Log(
            SubCategory subCategory,
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
            String timeslot
            ) {
        this.subCategory = subCategory;
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
        this.timeslot = timeslot;
    }

    public static Log of(SubCategory subCategory, LogRequestDto logRequestDto) {
        return Log.builder()
                .subCategory(subCategory)
                .menu(logRequestDto.getMenu())
                .intake(logRequestDto.getIntake())
                .unit(logRequestDto.getUnit())
                .level(logRequestDto.getLevel())
                .calorie(logRequestDto.getCalorie())
                .fat(logRequestDto.getFat())
                .protein(logRequestDto.getProtein())
                .carbs(logRequestDto.getCarbs())
                .sugar(logRequestDto.getSugar())
                .memo(logRequestDto.getMemo())
                .timeslot(logRequestDto.getTimeslot())
                .build();
    }
}
