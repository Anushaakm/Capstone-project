package com.anu.capstone.dto;

import java.time.LocalTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class UserJobPostDto {
   
        private Long id;
        private String jobTitle;
        private String location;
        private String industry;
        private LocalTime postedDate;
}
