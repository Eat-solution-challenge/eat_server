package com.eat.eat_server.logs.conrtoller;

import com.eat.eat_server.logs.dto.LogRequestDto;
import com.eat.eat_server.logs.dto.LogResponseDto;
import com.eat.eat_server.logs.service.LogService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class LogController {

    private final LogService logService;

    @PostMapping("/categories/{categoryId}")
    public ResponseEntity<LogResponseDto> createLog(@PathVariable Long categoryId,
                                                    @RequestBody LogRequestDto logRequestDto) {
        LogResponseDto logResponseDto = logService.createLog(categoryId, logRequestDto);
        return ResponseEntity.ok(logResponseDto);
    }
}
