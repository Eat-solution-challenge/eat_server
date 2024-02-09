package com.eat.eat_server.user.service;

import com.eat.eat_server.user.dto.JoinRequestDto;
import com.eat.eat_server.user.dto.LoginRequestDto;
import com.eat.eat_server.user.dto.TokenResponseDto;
import com.eat.eat_server.user.jwt.JwtTokenProvider;
import com.eat.eat_server.user.domain.User;
import com.eat.eat_server.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

@Service
@Transactional
@Slf4j
@RequiredArgsConstructor
public class UserService {
//    private final BCryptPasswordEncoder passwordEncoder;
//    private final UserRepository userRepository;
//    private final AuthenticationManagerBuilder authenticationManagerBuilder;
//    private final JwtProvider jwtProvider;

//    public String join(JoinRequestDto joinRequestDto){
//        String email = joinRequestDto.getEmail();
//        if (userRepository.existsByEmail(email))
//            return "이미 존재하는 회원입니다.";
//        String rawPassword = joinRequestDto.getPassword();
//        String encodedPassword = passwordEncoder.encode(rawPassword);
//        User u = new User(joinRequestDto.getNickname(), joinRequestDto.getEmail(), encodedPassword,
//                joinRequestDto.getAge(), joinRequestDto.getGender(), joinRequestDto.getHeight(), joinRequestDto.getWeight());
//        userRepository.save(u); //DB에 유저 저장
//
//        return "회원가입";
//    }
//
//    public String login(LoginRequestDto requestDto){
//        String email = requestDto.getEmail();
//        String password = requestDto.getPassword();
//
//        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(email, password);
//        System.out.println("auth token =" + authenticationToken);
//
////        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
//        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
//        System.out.println("222222");
//        System.out.println("auth  =" + authentication);
//
//        if (authentication.isAuthenticated()){ //인증이 왼료된 객체인 경우
//            PrincipalDetails principalDetails = (PrincipalDetails) authentication.getPrincipal();
//
//
//            Long authenticatedId = principalDetails.getUser().getId();
//            String authenticatedEmail = principalDetails.getUser().getEmail();
//            String authenticatedUsername = principalDetails.getUser().getNickname();
//
//            return jwtProvider.generateJwtToken(authenticatedId, authenticatedEmail, authenticatedUsername);
//
//        }
//        return "로그인 실패";
//    }

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;
    public Long join(JoinRequestDto requestDto) {
        //이메일 중복 검사
        if (userRepository.findByEmail(requestDto.getEmail()).isPresent()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "중복된 이메일입니다");
        }
        User user = userRepository.save(requestDto.toEntity());
        user.encodePassword(passwordEncoder); //비밀번호 암호화

        return user.getId();
    }

    public TokenResponseDto login(LoginRequestDto requestDto){
        User user = userRepository.findByEmail(requestDto.getEmail())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST,"잘못된 이메일입니다"));

        if (!passwordEncoder.matches(requestDto.getPassword(), user.getPassword())){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "잘못된 비밀번호입니다");
        }

        String accessToken = jwtTokenProvider.createAccessToken(user.getUsername(), user.getRole().name()); //여기가 문제인가?
        String refreshToken = jwtTokenProvider.createRefreshToken();

        user.updateRefreshToken(refreshToken); //refresh token 저장
        userRepository.save(user);

        return TokenResponseDto.builder()
                .grantType("Bearer")
                .jwtAccessToken(accessToken)
                .jwtRefreshToken(refreshToken)
                .build();

    }


}
