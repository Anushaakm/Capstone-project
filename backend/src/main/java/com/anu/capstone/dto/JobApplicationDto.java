package com.anu.capstone.dto;

import javax.validation.constraints.NotBlank;

import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class JobApplicationDto {

   

    private String applicantName;
   
    @NotNull(message = "applicantEmail cant be null")
    @NotBlank(message = "applicantEmail cant be blank")
    private String applicantEmail;
}
