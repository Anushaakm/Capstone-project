package com.anu.capstone.service;

import java.util.List;

import com.anu.capstone.dto.AdminUserJobDto;
import com.anu.capstone.dto.JobListDto;
import com.anu.capstone.dto.JobpostingDto;
import com.anu.capstone.dto.UpdateJobDto;

public interface AdminService {
    Integer createNewJob(JobpostingDto dto);
    List<JobListDto> getAllJob();
    Integer deleteJobPost(Long id) ;
    JobpostingDto getJob(Long id) ;
    Integer updateJobPost(UpdateJobDto dto);
    List<AdminUserJobDto> getAllUserJobs();
    List<AdminUserJobDto> searchUserJobsByEmail(String email);

}
