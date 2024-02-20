package com.eat.eat_server.domain.logs.service;

import com.eat.eat_server.domain.logs.domain.Level;
import com.eat.eat_server.domain.logs.domain.Log;
import com.eat.eat_server.domain.logs.domain.SubCategory;
import com.eat.eat_server.domain.logs.dto.ProperAmountDto;
import com.eat.eat_server.domain.logs.repository.LogRepository;
import com.eat.eat_server.domain.logs.repository.SubCategoryRepository;
import com.eat.eat_server.domain.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProperService {
    private final LogRepository logRepository;
    private final SubCategoryRepository subCategoryRepository;

    public ProperAmountDto getProperAmount(User user, String subCategoryName, String unit) {

        SubCategory subCategory = subCategoryRepository.findByUserAndName(user, subCategoryName);
        if (subCategory == null){
            ProperAmountDto.builder()
                    .properAmount(0)
                    .unit(unit)
                    .build();
        }
        List<Log> logs = logRepository.findBySubCategoryId(subCategory.getId());

        double sumOfIntake = 0;
        for(Log l:logs){
            if (l.getLevel() == Level.LEVEL_LIGHT)
                sumOfIntake += (l.getIntake() * 1.2);
            else if (l.getLevel() == Level.LEVEL_OVEREAT)
                sumOfIntake += (l.getIntake() * 0.8);
            else
                sumOfIntake += l.getIntake();
        }

        double properAmount = 0;
        if (logs.size()!=0) {
            properAmount = sumOfIntake / logs.size();
        }
        return ProperAmountDto.builder()
                .properAmount(properAmount)
                .unit(unit)
                .build();
    }
}
