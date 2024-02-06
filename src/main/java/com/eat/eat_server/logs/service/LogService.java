package com.eat.eat_server.logs.service;

import com.eat.eat_server.logs.domain.Category;
import com.eat.eat_server.logs.domain.Log;
import com.eat.eat_server.logs.domain.SubCategory;
import com.eat.eat_server.logs.dto.LogRequestDto;
import com.eat.eat_server.logs.dto.LogResponseDto;
import com.eat.eat_server.logs.repository.CategoryRepository;
import com.eat.eat_server.logs.repository.LogRepository;
import com.eat.eat_server.logs.repository.SubCategoryRepository;
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

    private SubCategory getOrCreateSubCategory(Long categoryId, String name) {
        if (subCategoryRepository.existsByName(name)) {
            return subCategoryRepository.findByName(name);
        }
        Category category = categoryRepository.findById(categoryId).orElseThrow();
        return subCategoryRepository.save(SubCategory.of(category, name));
    }

    public LogResponseDto createLog(Long categoryId, LogRequestDto logRequestDto) {
        String subCategoryName = logRequestDto.getSubCategory();
        SubCategory subCategory = getOrCreateSubCategory(categoryId, subCategoryName);
        Log log = logRepository.save(Log.of(subCategory, logRequestDto));
        return LogResponseDto.from(log);
    }

    public List<LogResponseDto> findLogs(Long subCategoryId) {
        List<Log> logs = logRepository.findBySubCategoryId(subCategoryId);
        return logs.stream()
                .map(LogResponseDto::from)
                .collect(Collectors.toList());
    }
}
