package com.eat.eat_server.domain.logs.service;

import com.eat.eat_server.domain.logs.domain.Log;
import com.eat.eat_server.domain.logs.dto.ProperAmountDto;
import com.eat.eat_server.domain.logs.repository.LogRepository;
import com.eat.eat_server.domain.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProperService {
    private final LogRepository logRepository;

    public ProperAmountDto getProperAmount(User user, Long subCategoryId) {
        List<Log> logs = logRepository.findBySubCategoryId(subCategoryId);
        int sumOfCalorie = 0;
        for(Log l:logs){
            if (l.getLevel().equals("LEVEL_LIGHT"))
                sumOfCalorie += (l.getCalorie() * 1.2);
            else if (l.getLevel().equals("LEVEL_OVEREAT"))
                sumOfCalorie += (l.getCalorie() * 0.8);
            else
                sumOfCalorie += l.getCalorie();
            System.out.println("eat cal = " + l.getCalorie());
        }
        int properAmount = sumOfCalorie / logs.size();
        System.out.println("log size = " + logs.size());
        return ProperAmountDto.builder()
                .properAmount(properAmount)
                .build();
    }
}
