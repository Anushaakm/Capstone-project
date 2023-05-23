package com.anu.capstone.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginDto {
    
   
    @NotNull(message = "email cant be null")
    @NotBlank(message = "email cant be blank")
    private String email;
    
    @NotNull(message = "password cant be null")
    @NotBlank(message = "password cant be blank")
    private String password;
}
