package com.anu.capstone.dto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AdminUserJobDto {
    
    private String jobTitle;
    private String location;
    private String industry; 
    private String userEmail;   
    private long userId;
}
