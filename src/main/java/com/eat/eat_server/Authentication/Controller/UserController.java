package com.eat.eat_server.Authentication.Controller;

import com.eat.eat_server.Authentication.Dto.JoinRequestDto;
import com.eat.eat_server.Authentication.Dto.LoginRequestDto;
import com.eat.eat_server.Authentication.Service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

//    @GetMapping("/health")
//    public ResponseEntity<?> getJournal() {
//        return new ResponseEntity<>(HttpStatus.OK);
//    }

//    @PostMapping("/join")
//    public String join(@RequestBody JoinRequestDto requestDto){
//        return userService.join(requestDto);
//    }
//
//    @PostMapping("/api/login")
//    public String login(@RequestBody LoginRequestDto requestDto){
//        return userService.login(requestDto);
//    }
    @PostMapping("/join")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity join(@RequestBody JoinRequestDto requestDto){
        userService.join(requestDto);
        return new ResponseEntity(HttpStatus.OK);
    }

    @PostMapping("/login")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity login(@RequestBody LoginRequestDto requestDto){
        return new ResponseEntity(userService.login(requestDto), HttpStatus.OK);
    }
}
