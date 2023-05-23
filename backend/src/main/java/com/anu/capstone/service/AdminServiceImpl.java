package com.anu.capstone.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;

import com.anu.capstone.domain.JobPosting;
import com.anu.capstone.domain.User;
import com.anu.capstone.dto.AdminUserJobDto;
import com.anu.capstone.dto.JobListDto;
import com.anu.capstone.dto.JobpostingDto;
import com.anu.capstone.dto.UpdateJobDto;
import com.anu.capstone.exception.JobNotFoundException;
import com.anu.capstone.exception.UserNotFoundException;
import com.anu.capstone.repository.JobPostRepository;
import com.anu.capstone.repository.UserRepository;
import com.anu.capstone.util.DynamicMapper;

import lombok.AllArgsConstructor;
@Service
@AllArgsConstructor
public class AdminServiceImpl implements AdminService{
    private final UserRepository userRepository;
    private final JobPostRepository jobPostRepository;
    private final DynamicMapper dynamicMapper;
    @Override
    public Integer createNewJob(JobpostingDto dto) {
        JobPosting jobPosting=dynamicMapper.convertor(dto,new JobPosting());
        jobPostRepository.save(jobPosting);
        return 1;
    }

    @Override
    public List<JobListDto> getAllJob() {
        List<JobListDto> collect = jobPostRepository.findAll()
                .stream()
                .map(jobPosting -> dynamicMapper.convertor(jobPosting, new JobListDto()))
                .collect(Collectors.toList());
        if (collect.isEmpty())
            throw new JobNotFoundException("No event found create one.");

        return collect;
    }

    @Override
    public Integer deleteJobPost(Long id) {
        isJobPresent(id);
        jobPostRepository.deleteById(id);
        return 1;
    }
    private void isJobPresent(Long id) {
        jobPostRepository.findById(id).orElseThrow(() -> new JobNotFoundException("No Job found for " + id + " ID"));
    }
    @Override
    public JobpostingDto getJob(Long id) {
        isJobPresent(id);
        JobPosting job = jobPostRepository.getReferenceById(id);
        return dynamicMapper.convertor(job, new JobpostingDto());
    }

    @Override
    public Integer updateJobPost(UpdateJobDto dto) {
        isJobPresent(dto.getId());
        jobPostRepository.save(dynamicMapper.convertor(dto, new JobPosting()));
        return 1;
    }

    @Override
    public List<AdminUserJobDto> getAllUserJobs() {
        List<AdminUserJobDto> adminUserBookDtos = new ArrayList<>();
        List<User> users = userRepository.findAll();
        for (User user : users) {
            for (JobPosting bookingSlot : user.getJobPostings()) {
                AdminUserJobDto adminUserBookDto = new AdminUserJobDto();
                adminUserBookDto.setUserId(user.getId());
                adminUserBookDto.setUserEmail(user.getEmail());
                adminUserBookDto.setLocation(bookingSlot.getLocation());
                adminUserBookDto.setJobTitle(bookingSlot.getJobTitle());
                adminUserBookDto.setIndustry(bookingSlot.getIndustry());
                adminUserBookDtos.add(adminUserBookDto);
            }
        }
        return adminUserBookDtos;
    }

    @Override
    public List<AdminUserJobDto> searchUserJobsByEmail(String email) {
        List<AdminUserJobDto> adminUserBookDtos = new ArrayList<>();
        List<User> users = userRepository.findAllByEmail(email);
        for (User user : users) {
            for (JobPosting bookingSlot : user.getJobPostings()) {
                AdminUserJobDto adminUserBookDto = new AdminUserJobDto();
                adminUserBookDto.setUserId(user.getId());
                adminUserBookDto.setUserEmail(user.getEmail());
                adminUserBookDto.setLocation(bookingSlot.getLocation());
                adminUserBookDto.setJobTitle(bookingSlot.getJobTitle());
                adminUserBookDto.setIndustry(bookingSlot.getIndustry());
                adminUserBookDtos.add(adminUserBookDto);
            }
        }
        return adminUserBookDtos;
    }
    

    // @Override
    // public List<AdminUserJobDto> searchUserJobsByEmail(String email) {
    //     List<AdminUserJobDto> adminUserBookDtos = userRepository.findAllByEmail(email)
    //                       .stream()
    //                       .map(user-> dynamicMapper.convertor(user, new AdminUserJobDto()))
    //                       .collect(Collectors.toList());
    //     if(adminUserBookDtos.isEmpty()){
    //         throw new UserNotFoundException("no user present");
    //     }
      
    //     return adminUserBookDtos;

    // }

}
    
