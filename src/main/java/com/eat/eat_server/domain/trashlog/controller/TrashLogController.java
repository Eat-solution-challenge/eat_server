package com.eat.eat_server.domain.trashlog.controller;

import com.eat.eat_server.domain.logs.dto.LogRequestDto;
import com.eat.eat_server.domain.logs.dto.LogResponseDto;
import com.eat.eat_server.domain.trashlog.dto.TrashLogRequestDto;
import com.eat.eat_server.domain.trashlog.dto.TrashLogResponseDto;
import com.eat.eat_server.domain.trashlog.service.TrashLogService;
import com.eat.eat_server.domain.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class TrashLogController {
    private final TrashLogService trashLogService;

    @PostMapping("/trashlog")
    public ResponseEntity<TrashLogResponseDto> createTrashLog(@AuthenticationPrincipal User user,
                                                              @RequestBody TrashLogRequestDto trashLogRequestDto){
        TrashLogResponseDto trashLogResponseDto = trashLogService.createTrashLog(user, trashLogRequestDto);
        return ResponseEntity.ok(trashLogResponseDto);
    }
}
