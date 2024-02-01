package com.eat.eat_server.Authentication.Service;

import com.eat.eat_server.Authentication.Dto.JoinRequestDto;
import com.eat.eat_server.Entity.User;
import com.eat.eat_server.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final BCryptPasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    public String join(JoinRequestDto joinRequestDto){
        String email = joinRequestDto.getEmail();
        if (userRepository.existsByEmail(email))
            return "이미 존재하는 회원입니다.";
        String rawPassword = joinRequestDto.getPassword();
        String encodedPassword = passwordEncoder.encode(rawPassword);
        User u = new User(joinRequestDto.getNickname(), joinRequestDto.getEmail(), encodedPassword,
                joinRequestDto.getAge(), joinRequestDto.getGender(), joinRequestDto.getHeight(), joinRequestDto.getWeight());
        userRepository.save(u); //DB에 유저 저장

        return "회원가입";
    }
}
