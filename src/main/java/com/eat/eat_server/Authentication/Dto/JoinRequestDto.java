package com.eat.eat_server.Authentication.Dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class JoinRequestDto {
    private String email;
    private String nickname;
    private String password;
    private Long height;
    private Long weight;
    private String gender;
    private int age;
}
