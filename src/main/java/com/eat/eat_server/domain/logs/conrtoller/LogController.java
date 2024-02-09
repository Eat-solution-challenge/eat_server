package com.eat.eat_server.domain.logs.conrtoller;

import com.eat.eat_server.domain.logs.dto.LogRequestDto;
import com.eat.eat_server.domain.logs.dto.LogResponseDto;
import com.eat.eat_server.domain.logs.dto.SubCategoryResponseDto;
import com.eat.eat_server.domain.logs.service.CategoryService;
import com.eat.eat_server.domain.logs.service.LogService;
import com.eat.eat_server.domain.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class LogController {

    private final LogService logService;
    private final CategoryService categoryService;

    @PostMapping("/categories/{categoryId}")
    public ResponseEntity<LogResponseDto> createLog(@AuthenticationPrincipal User user,
                                                    @PathVariable Long categoryId,
                                                    @RequestBody LogRequestDto logRequestDto) {
        LogResponseDto logResponseDto = logService.createLog(user, categoryId, logRequestDto);
        return ResponseEntity.ok(logResponseDto);
    }

    @GetMapping("/categories/{categoryId}")
    public ResponseEntity<List<SubCategoryResponseDto>> findSubCategories(@AuthenticationPrincipal User user,
                                                                          @PathVariable Long categoryId) {
        List<SubCategoryResponseDto> subCategoryResponseDtos = categoryService.findSubCategories(user, categoryId);
        return ResponseEntity.ok(subCategoryResponseDtos);
    }

    @GetMapping("/logs")
    public ResponseEntity<List<LogResponseDto>> findLogs(@RequestParam(required=false) Long subCategoryId) {
        List<LogResponseDto> logResponseDtos = logService.findLogs(subCategoryId);
        return ResponseEntity.ok(logResponseDtos);
    }
}
