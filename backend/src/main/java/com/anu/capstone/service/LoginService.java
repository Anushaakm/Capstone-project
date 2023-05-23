package com.anu.capstone.service;

import com.anu.capstone.dto.LoginDto;
import com.anu.capstone.dto.LoginResponseDto;
import com.anu.capstone.dto.RegisterDto;

public interface LoginService {
    Integer registerUser(RegisterDto dto);

    String loginUser(LoginDto dto);

    LoginResponseDto loginUserForResponse(LoginDto dto);
}
