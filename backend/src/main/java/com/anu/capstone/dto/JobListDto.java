package com.anu.capstone.dto;

import java.time.LocalTime;
import lombok.AllArgsConstructor;
import lombok.Data;

import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class JobListDto{
    private Long id;
    private String jobTitle;
    private String location;
    private String industry;
    private LocalTime postedDate;
}
