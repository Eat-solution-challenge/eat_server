package com.eat.eat_server.domain.trashlog.service;

import com.eat.eat_server.domain.logs.dto.LogResponseDto;
import com.eat.eat_server.domain.trashlog.domain.TrashLog;
import com.eat.eat_server.domain.trashlog.dto.TrashLogRequestDto;
import com.eat.eat_server.domain.trashlog.dto.TrashLogResponseDto;
import com.eat.eat_server.domain.trashlog.repository.TrashLogRepository;
import com.eat.eat_server.domain.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoField;

@Service
@RequiredArgsConstructor
public class TrashLogService {
    private final TrashLogRepository trashLogRepository;

    public TrashLogResponseDto createTrashLog(User user, TrashLogRequestDto trashLogRequestDto){

        LocalDateTime today = LocalDateTime.now();
        int day = today.get(ChronoField.DAY_OF_WEEK); //월=1 화=2
        if (day == 7)
            day = 0;
        LocalDateTime startDay = today.minusDays(day); //이번주 월요일
        LocalDateTime endDay = startDay.plusDays(6);  //이번주 일요일

        TrashLog trashLog;
        if (!trashLogRepository.existsByCreatedTimeBetweenAndUser(startDay, endDay, user)){
            trashLog = trashLogRepository.save(TrashLog.of(user, trashLogRequestDto));

        }else{
            trashLog = trashLogRepository.findByCreatedTimeBetweenAndUser(startDay, endDay, user);
            trashLog.updateAmount(trashLog.getAmount() + trashLogRequestDto.getAmount());
            trashLogRepository.save(trashLog);
        }

        return TrashLogResponseDto.from(trashLog);
    }
}
