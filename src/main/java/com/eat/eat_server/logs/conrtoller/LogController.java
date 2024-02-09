package com.eat.eat_server.logs.conrtoller;

import com.eat.eat_server.logs.dto.LogRequestDto;
import com.eat.eat_server.logs.dto.LogResponseDto;
import com.eat.eat_server.logs.dto.SubCategoryResponseDto;
import com.eat.eat_server.logs.service.CategoryService;
import com.eat.eat_server.logs.service.LogService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class LogController {

    private final LogService logService;
    private final CategoryService categoryService;

    @PostMapping("/categories/{categoryId}")
    public ResponseEntity<LogResponseDto> createLog(@PathVariable Long categoryId,
                                                    @RequestBody LogRequestDto logRequestDto) {
        LogResponseDto logResponseDto = logService.createLog(categoryId, logRequestDto);
        return ResponseEntity.ok(logResponseDto);
    }

    @GetMapping("/categories/{categoryId}")
    public ResponseEntity<List<SubCategoryResponseDto>> findSubCategories(@PathVariable Long categoryId) {
        List<SubCategoryResponseDto> subCategoryResponseDtos = categoryService.findSubCategories(categoryId);
        return ResponseEntity.ok(subCategoryResponseDtos);
    }
}
