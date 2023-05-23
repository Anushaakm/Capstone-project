package com.anu.capstone.service;

import org.springframework.stereotype.Service;

import com.anu.capstone.domain.User;
import com.anu.capstone.dto.LoginDto;
import com.anu.capstone.dto.LoginResponseDto;
import com.anu.capstone.dto.RegisterDto;
import com.anu.capstone.exception.DuplicateEmailFoundException;
import com.anu.capstone.exception.InvalidPasswordException;
import com.anu.capstone.exception.InvalidRoleException;
import com.anu.capstone.exception.UserNotFoundException;
import com.anu.capstone.repository.UserRepository;
import com.anu.capstone.util.DynamicMapper;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class LoginServiceImpl implements LoginService{
    private final UserRepository userRepository;
    private final DynamicMapper dynamicMapper;


    @Override
    public Integer registerUser(RegisterDto dto) {
        if (!"user".equals(dto.getRole()) && !"admin".equals(dto.getRole()))
            throw new InvalidRoleException("Invalid role! Enter admin/user");
        User user = dynamicMapper.convertor(dto, new User());
        if (userRepository.existsByEmail(user.getEmail())) {
            throw new DuplicateEmailFoundException("Email already used.");
        }
        isValidPassword(dto.getPassword());
        userRepository.save(user);
        return 1;
    }
    
    private void isValidPassword(String password) {
        boolean isValid = true;
        if (password.length() > 15 || password.length() < 8) {
            throw new InvalidPasswordException("Password must be more than 8 characters and less than 20 characters in length.");
        }
        String upperCaseChars = "(.*[A-Z].*)";
        if (!password.matches(upperCaseChars)) {
            throw new InvalidPasswordException("Password must have atleast one uppercase character");
        }
        String lowerCaseChars = "(.*[a-z].*)";
        if (!password.matches(lowerCaseChars)) {
            throw new InvalidPasswordException("Password must have atleast one lowercase character");
        }
        String numbers = "(.*[0-9].*)";
        if (!password.matches(numbers)) {
            throw new InvalidPasswordException("Password must have atleast one number");
        }
        String specialChars = "(.*[@,#,$,%].*$)";
        if (!password.matches(specialChars)) {
            throw new InvalidPasswordException("Password must have atleast one special character among @#$%");
        }
    }

    @Override
    public String loginUser(LoginDto dto) {
        User user = userRepository.findByEmailAndPassword(dto.getEmail(), dto.getPassword())
                .orElseThrow(() -> new UserNotFoundException("Email/Password is not valid"));
        return user.getRole();
    }

    @Override
    public LoginResponseDto loginUserForResponse(LoginDto dto) {
        User user = userRepository.findByEmailAndPassword(dto.getEmail(), dto.getPassword())
                .orElseThrow(() -> new UserNotFoundException("Email/Password is not valid"));
        return dynamicMapper.convertor(user, new LoginResponseDto());
    }
    

}
