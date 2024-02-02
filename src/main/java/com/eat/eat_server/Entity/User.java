package com.eat.eat_server.Entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class User extends BaseTimeEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    private String nickname;

    private String email;

    private String password;

    private int age;

    private String gender;

    private Long height;

    private Long weight;

    @Enumerated(EnumType.STRING)
    private Role roles;


    @Builder
    public User (String nickname, String email, String password,
                 int age, String gender, Long height, Long weight){
        this.nickname = nickname;
        this.email = email;
        this.password = password;
        this.age = age;
        this.gender = gender;
        this.height = height;
        this.weight = weight;
    }
}
