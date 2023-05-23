package com.anu.capstone.service;

import java.util.List;

import com.anu.capstone.dto.JobListDto;
import com.anu.capstone.dto.UserJobPostDto;

public interface UserService {
    Integer applyJob(Long userId,Long jobPostId);
    
    List<UserJobPostDto> getAllJobs(Long userId);

    List<JobListDto> getJobsByLocation(String location);
   // List<JobListDto> getJobsByIndusry(String industry);

    UserJobPostDto getJob(Long userId,Long jobPostId);
}
