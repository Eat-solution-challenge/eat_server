package com.eat.eat_server.Authentication.Dto;

import com.eat.eat_server.Entity.Role;
import com.eat.eat_server.Entity.User;
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
    private Role role;

    @Builder
    public User toEntity(){
        return User.builder()
                .email(email)
                .password(password)
                .nickname(nickname)
                .height(height)
                .weight(weight)
                .gender(gender)
                .role(Role.ROLE_USER)
                .age(age)
                .build();
    }

}
