package com.anu.capstone.controller;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.anu.capstone.dto.LoginDto;
import com.anu.capstone.dto.LoginResponseDto;
import com.anu.capstone.dto.RegisterDto;
import com.anu.capstone.service.LoginService;
import com.anu.capstone.util.AppResponse;

import lombok.AllArgsConstructor;

@CrossOrigin
@RestController
@AllArgsConstructor
@RequestMapping("/user")
public class LoginController {

    private final LoginService loginService;

    @PostMapping(value = "/", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AppResponse<Integer>> signUpUser(@Valid @RequestBody RegisterDto dto) {
        Integer registerUser = loginService.registerUser(dto);
        AppResponse<Integer> response = AppResponse.<Integer>builder()
                .sts("success")
                .msg("registration done successfully.")
                .bd(registerUser)
                .build();
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PostMapping(value = "/login", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AppResponse<String>> loginUser(@Valid @RequestBody LoginDto dto) {
        String loginUser = loginService.loginUser(dto);
        AppResponse<String> response = AppResponse.<String>builder()
                .sts("success")
                .msg("user login as...")
                .bd(loginUser)
                .build();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
    @PostMapping(value = "/loginv2", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AppResponse<LoginResponseDto>> loginResponseDetails(@Valid @RequestBody LoginDto dto) {
        LoginResponseDto loginUser = loginService.loginUserForResponse(dto);
        AppResponse<LoginResponseDto> response = AppResponse.<LoginResponseDto>builder()
                .sts("success")
                .msg("user login as...")
                .bd(loginUser)
                .build();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

}