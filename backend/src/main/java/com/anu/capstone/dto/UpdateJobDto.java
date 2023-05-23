package com.anu.capstone.dto;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateJobDto {

   private Long id;
    private String jobTitle;
    private String jobDescription;

    private String location;

    private String industry;

    private String qualification;


    private String applicationRequirement;
   
    private LocalDate postedDate;

}
