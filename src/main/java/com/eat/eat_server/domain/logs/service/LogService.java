package com.eat.eat_server.domain.logs.service;

import com.eat.eat_server.domain.logs.domain.Category;
import com.eat.eat_server.domain.logs.domain.Log;
import com.eat.eat_server.domain.logs.domain.SubCategory;
import com.eat.eat_server.domain.logs.dto.LogRequestDto;
import com.eat.eat_server.domain.logs.dto.LogResponseDto;
import com.eat.eat_server.domain.logs.repository.CategoryRepository;
import com.eat.eat_server.domain.logs.repository.LogRepository;
import com.eat.eat_server.domain.logs.repository.SubCategoryRepository;
import com.eat.eat_server.domain.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class LogService {

    private final LogRepository logRepository;
    private final SubCategoryRepository subCategoryRepository;
    private final CategoryRepository categoryRepository;

    private SubCategory getOrCreateSubCategory(User user, Long categoryId, String name) {
        if (subCategoryRepository.existsByUserAndName(user, name)) {
            return subCategoryRepository.findByUserAndName(user, name);
        }
        Category category = categoryRepository.findById(categoryId).orElseThrow();
        return subCategoryRepository.save(SubCategory.of(user, category, name));
    }

    public LogResponseDto createLog(User user, Long categoryId, LogRequestDto logRequestDto) {
        String subCategoryName = logRequestDto.getSubCategory();
        SubCategory subCategory = getOrCreateSubCategory(user, categoryId, subCategoryName);
        Log log = logRepository.save(Log.of(subCategory, logRequestDto));
        return LogResponseDto.from(log);
    }

    public List<LogResponseDto> findLogs(Long subCategoryId) {
        List<Log> logs;
        if (subCategoryId == null) {
            logs = logRepository.findAll();
        } else {
            logs = logRepository.findBySubCategoryId(subCategoryId);        }
        return logs.stream()
                .map(LogResponseDto::from)
                .collect(Collectors.toList());
    }
}
