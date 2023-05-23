package com.anu.capstone.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterDto {
   

    @NotBlank(message = "Email is mandatory")
    @NotNull(message = "Email should not be null")
    @Email(message = "Invalid Email")
    private String email;

    @NotNull(message = "Password should not be null")
    @NotBlank(message = "Password is mandatory")
    private String password;

    @NotNull(message = "Role should not be null")
    @NotBlank(message = "Role is mandatory")
    private String role;
}
