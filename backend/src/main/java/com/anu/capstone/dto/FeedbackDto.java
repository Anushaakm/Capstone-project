package com.anu.capstone.dto;
import java.time.LocalDate;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Setter
@Getter
public class FeedbackDto {
    private Long id;
    @NotEmpty(message = "Comment cant be empty")
    @NotNull(message = "Comment cant be null")
    @NotBlank(message = "Comment cant be blank")
    private String comment;

    @NotEmpty(message = "location cant be empty")
    @NotNull(message = "location cant be null")
    @NotBlank(message = "location cant be blank")
    private String location;

    @NotEmpty(message = "interviewDate cant be empty")
    @NotNull(message = "interviewDate cant be null")
    @NotBlank(message = "interviewDate cant be blank")
    private LocalDate interviewDate;
}
