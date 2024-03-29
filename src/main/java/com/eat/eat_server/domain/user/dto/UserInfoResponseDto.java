package com.eat.eat_server.domain.user.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UserInfoResponseDto {
    private String userName;
    private String gender;
    private int age;
    private Long weight;
    private Long height;
    private Long overEat;
    private Long properEat;
    private Long lightEat;
    private String nickname;
    private double thisWeekTrashAmount;
    private double lastWeekTrashAmount;

}
