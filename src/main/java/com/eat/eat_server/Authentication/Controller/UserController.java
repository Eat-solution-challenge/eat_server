package com.eat.eat_server.Authentication.Controller;

import com.eat.eat_server.Authentication.Dto.JoinRequestDto;
import com.eat.eat_server.Authentication.Service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/health")
    public ResponseEntity<?> getJournal() {
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/join")
    public String join(@RequestBody JoinRequestDto requestDto){
        return userService.join(requestDto);
    }

}
