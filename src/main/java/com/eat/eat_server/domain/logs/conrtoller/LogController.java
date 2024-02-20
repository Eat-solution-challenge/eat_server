package com.eat.eat_server.domain.logs.conrtoller;

import com.eat.eat_server.domain.logs.dto.*;
import com.eat.eat_server.domain.logs.service.CategoryService;
import com.eat.eat_server.domain.logs.service.LogService;
import com.eat.eat_server.domain.logs.service.ProperService;
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
    private final ProperService properService;

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
    public ResponseEntity<List<LogResponseDto>> findLogs(@AuthenticationPrincipal User user,
                                                         @RequestParam(required=false) Long subCategoryId) {
        List<LogResponseDto> logResponseDtos = logService.findLogs(user, subCategoryId);
        return ResponseEntity.ok(logResponseDtos);
    }

    @GetMapping("/logs/search")
    public ResponseEntity<List<LogResponseDto>> findLogsByMenu(@AuthenticationPrincipal User user,
                                                         @RequestParam(required=true) String menu) {
        List<LogResponseDto> logResponseDtos = logService.findLogsByMenu(user, menu);
        return ResponseEntity.ok(logResponseDtos);
    }

    @GetMapping("/calender")
    public ResponseEntity<List<CalenderLogDto>> findCalenderLogs(@AuthenticationPrincipal User user) {
        List<CalenderLogDto> calenderLogDtos = logService.findCalenderLogs(user);
        return ResponseEntity.ok(calenderLogDtos);
    }

    @GetMapping("/proper")
    public ResponseEntity<ProperAmountDto> getProperAmount(@AuthenticationPrincipal User user,
                                                           @RequestParam(required = true) String subCategoryName,
                                                           @RequestParam(required = true) String unit){
        ProperAmountDto properAmountDto = properService.getProperAmount(user, subCategoryName, unit);
        return ResponseEntity.ok(properAmountDto);
    }
}
