package com.eat.eat_server.Authentication.Service;

import com.eat.eat_server.Authentication.Dto.JoinRequestDto;
import com.eat.eat_server.Authentication.Dto.LoginRequestDto;
import com.eat.eat_server.Authentication.Jwt.JwtProvider;
import com.eat.eat_server.Authentication.PrincipalDetails;
import com.eat.eat_server.Entity.User;
import com.eat.eat_server.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final BCryptPasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final JwtProvider jwtProvider;

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

    public String login(LoginRequestDto requestDto){
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(requestDto.getEmail(), requestDto.getPassword());
        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);

        if (authentication.isAuthenticated()){ //인증이 왼료된 객체인 경우
            PrincipalDetails principalDetails = (PrincipalDetails) authentication.getPrincipal();


            Long authenticatedId = principalDetails.getUser().getId();
            String authenticatedEmail = principalDetails.getUser().getEmail();
            String authenticatedUsername = principalDetails.getUser().getNickname();

            return jwtProvider.generateJwtToken(authenticatedId, authenticatedEmail, authenticatedUsername);

        }
        return "로그인 실패";
    }
}
