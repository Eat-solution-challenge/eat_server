package com.eat.eat_server.domain.user.service;

import com.eat.eat_server.domain.logs.domain.Level;
import com.eat.eat_server.domain.logs.domain.Log;
import com.eat.eat_server.domain.logs.domain.SubCategory;
import com.eat.eat_server.domain.logs.repository.LogRepository;
import com.eat.eat_server.domain.logs.repository.SubCategoryRepository;
import com.eat.eat_server.domain.trashlog.domain.TrashLog;
import com.eat.eat_server.domain.trashlog.repository.TrashLogRepository;
import com.eat.eat_server.domain.user.dto.UserInfoResponseDto;
import com.eat.eat_server.domain.user.repository.UserRepository;
import com.eat.eat_server.domain.user.dto.JoinRequestDto;
import com.eat.eat_server.domain.user.dto.LoginRequestDto;
import com.eat.eat_server.domain.user.dto.TokenResponseDto;
import com.eat.eat_server.domain.user.jwt.JwtTokenProvider;
import com.eat.eat_server.domain.user.domain.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.time.temporal.ChronoField;
import java.util.List;

@Service
@Transactional
@Slf4j
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;
    private final SubCategoryRepository subCategoryRepository;
    private final LogRepository logRepository;
    private final TrashLogRepository trashLogRepository;

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

    public UserInfoResponseDto getUserInfo(User user){
        List<SubCategory> subCategoryList = subCategoryRepository.findByUser(user);
        long properEat = 0;
        long overEat = 0;
        long lightEat = 0;

        for(SubCategory s : subCategoryList){
            List<Log> logList = logRepository.findBySubCategoryId(s.getId());
            for(Log l : logList){
                if (l.getLevel() == Level.LEVEL_PROPER)
                    ++properEat;
                else if (l.getLevel() == Level.LEVEL_OVEREAT)
                    ++overEat;
                else
                    ++lightEat;
            }
        }

        long total = properEat + overEat + lightEat;
        if (total!=0) {
            lightEat = lightEat * 100 / total;
            properEat = properEat * 100 / total;
            overEat = overEat * 100 / total;
        }

        //유저의 이번주 쓰레기양
        LocalDateTime today = LocalDateTime.now();
        int day = today.get(ChronoField.DAY_OF_WEEK); //월=1 화=2
        if (day == 7)
            day = 0;
        LocalDateTime startDay = today.minusDays(day); //이번주 월요일
        LocalDateTime endDay = startDay.plusDays(6);  //이번주 일요일
        TrashLog trashLog = trashLogRepository.findByCreatedTimeBetweenAndUser(startDay, endDay, user);


        return UserInfoResponseDto.builder()
                .userName(user.getUsername())
                .age(user.getAge())
                .gender(user.getGender())
                .nickname(user.getNickname())
                .weight(user.getWeight())
                .height(user.getHeight())
                .lightEat(lightEat)
                .overEat(overEat)
                .properEat(properEat)
                .trashAmount(trashLog.getAmount())
                .build();
    }


}
